package only.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import only.model.Post;
import only.service.PostService;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired
	private PostService ps;
	
	@RequestMapping(value = "/postWrite", method = RequestMethod.POST)
	public String postWrite(
			@RequestParam("member_id") String member_id,
			@RequestParam("path") String path,
			@RequestParam("text") String text,
			@RequestParam("imageUpload") MultipartFile imageUpload,
			@RequestParam("videoUpload") MultipartFile videoUpload) {
		
		char[] type = {'n','n','n','n','n','n','n'};
		
		System.out.println("fileUpload : " + path + videoUpload.getOriginalFilename());
		if (text!=null && !text.equals("")) {
			type[0] = 'y';
		}
		if (!imageUpload.isEmpty()) { // upload된 image파일이 있을 경우
			type[1] = 'y';
			try {
				byte[] bytes = imageUpload.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");

				File dir = new File(rootPath + File.separator + "uploadFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + imageUpload.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
			} catch (Exception e) {
				return "You failed to upload " + imageUpload.getName() + " => " + e.getMessage();
			}
		} 
		if (!videoUpload.isEmpty()) { //upload된 image파일이 있을 경우
			type[2] = 'y';
			try {
				byte[] bytes = videoUpload.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "uploadFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + videoUpload.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
			} catch (Exception e) {
				
			}
		}
		
		// Add Post
		Post post = new Post();
		post.setUserid(member_id);
		post.setText(text);
		System.out.println(String.copyValueOf(type) + String.copyValueOf(type).length());
		post.setType(String.copyValueOf(type));
		int pid = ps.nextPid();
		System.out.println("pid: " + pid);
		post.setPid(pid);
		int result = ps.insertPost(post);
		if (result > 0) {
			System.out.println("작성성공 from PostWrite");
		} else {
			System.out.println("작성실패");
		}
		System.out.println( "포스트 현재 아이디: "+ pid);
		for(int i=0; i<type.length; i++) {
			if(type[i]=='y') {
				if(i==0) { // Add text 
					ps.insertText(pid, text);
				}
				if(i==1) { // Add Image/Video
					String filePath = System.getProperty("catalina.home") + File.separator + "uploadFiles";
					ps.insertImage(pid, filePath+ File.separator + imageUpload.getOriginalFilename());
				}
			}
		}
		return "timeline";
	}
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("name") String name,
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
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
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
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
