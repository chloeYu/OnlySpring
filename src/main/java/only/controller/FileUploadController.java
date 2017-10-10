package only.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import only.model.Comments;
import only.model.Post;
import only.model.Post_Files;
import only.model.Post_Location;
import only.service.CommentService;
import only.service.PostService;
import only.utils.PostType;
import only.utils.WebConstants;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private PostService ps;
	
	@Autowired
	private CommentService cs;

	
	@RequestMapping(value = "/postWrite", method = RequestMethod.POST)
	public String postWrite(Post post, HttpServletRequest request, Model model) {
		return "forward:/postWriteAction";
	}
	@RequestMapping(value = "/postWriteAction", method = RequestMethod.POST)
	public String postWriteAction(Post post, HttpServletRequest request) {
		String rootPath =  request.getSession().getServletContext().getRealPath("/WEB-INF/img_timeline");
		char[] type = { 'n', 'n', 'n', 'n', 'n', 'n', 'n' };
		String text = post.getText();
		if (text != null && !text.equals("")) {
			type[PostType.TEXT.ordinal()] = 'y';
		}
		List<MultipartFile> files = post.getFiles();
		if (files.size() > 0) { // upload된 image파일이 있을 경우
			for (MultipartFile file : files) {
				if(file.getOriginalFilename().equals("")) continue;
				else type[PostType.PHOTO_VIDEO.ordinal()] = 'y';
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					/*File dir = new File(rootPath + File.separator + "uploadFiles");
					if (!dir.exists())
						dir.mkdirs();*/

					// Create the file on server
					File serverFile = new File(rootPath + File.separator + file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					logger.info("Server File Location=" + serverFile.getAbsolutePath());
					System.out.println("Server File Location=" + serverFile.getAbsolutePath());

				} catch (Exception e) {
					return "You failed to upload " + file.getName() + " => " + e.getMessage();
				}
			}
		}
		if(post.getPlace() != null && !post.getPlace().equals("")) {
			type[PostType.LOCATION.ordinal()] = 'y';
			System.out.println(post.getTaggedFriend().size()+"명의 친구 Tagged");
		}
		if(post.getTaggedFriend() != null && post.getTaggedFriend().size()>0) {
			type[PostType.TAG_FRIENDS.ordinal()]='y';
		}
		
		System.out.println(String.copyValueOf(type) + String.copyValueOf(type).length());
		post.setType(String.copyValueOf(type));
		int pid = ps.nextPid();
		System.out.println("포스트 현재 아이디 pid: " + pid);
		post.setPid(pid);
		int result = ps.insertPost(post);
		if (result > 0) {
			System.out.println("작성성공 from PostWrite");
		} else {
			System.out.println("작성실패");
		}
		for (int i = 0; i < type.length; i++) {
			if (type[i] == 'y') {
				if (i == PostType.TEXT.ordinal()) { // Add text
					if (ps.insertText(pid, text) > 0) {
						System.out.println("Text 입력성공");
					} else {
						System.out.println("Text 입력실패");
					}
				}
				if (i == PostType.PHOTO_VIDEO.ordinal()) { // Add Image/Video
					for (int j = 0; j < files.size(); j++) {
						System.out.println(files.get(j).getOriginalFilename());
						Post_Files postImage = new Post_Files();
						postImage.setPid(post.getPid());
						postImage.setUrl(files.get(j).getOriginalFilename());
						postImage.setSeq(j);
						if (ps.insertImage(pid, postImage) > 0) {
							System.out.println("Image 입력성공");
						} else {
							System.out.println("Image 입력실패");
						}
					}
				}
				if (i == PostType.LOCATION.ordinal()) { // Add location
					Post_Location location = new Post_Location();
					location.setPid(pid);
					location.setPlace(post.getPlace());
					location.setLat(post.getLat());
					location.setLng(post.getLng());
						
					System.out.println(location.getPlace() + location.getLat());
					if (ps.insertLocation(pid, location) > 0) {
						System.out.println("location 입력성공");
					} else {
						System.out.println("location 입력실패");
					}
				}
				if(i == PostType.TAG_FRIENDS.ordinal()) {
					for(String member : post.getTaggedFriend()) {
						int r = ps.insertMemberTag(pid, member);
					}
				}
			}
		}
		// return "timeline";
		return "redirect:/timeline";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@RequestMapping(value = "/writeComment", method = RequestMethod.POST)
	public String writeComment(int ref_id, String text, int ref_type, HttpSession session, Model model) {
		Comments comment = new Comments();
		comment.setRef_id(ref_id);
		comment.setText(text);
		comment.setUserid((String) session.getAttribute(WebConstants.USER_ID));
		if(ref_type==0) comment.setRe_level(0);
		else comment.setRe_level(1);
		
		int result = cs.insert(comment);
		model.addAttribute("ref_id", ref_id);
		model.addAttribute("ref_type", ref_type);
		model.addAttribute("pageNum", 1);
		
		/*List<Comments> clist = cs.getComments(ref_id, ref_type, 1);
		model.addAttribute("clist", clist);
		System.out.println("comment Size: "+ clist.size());
		*/
		return "redirect:/loadComment";
	}
}
