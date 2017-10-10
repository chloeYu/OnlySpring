package only.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.BootstrapWith;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import only.model.Comments;
import only.model.Likes;
import only.model.Member;
import only.model.Post;
import only.service.CommentService;
import only.service.LikesService;
import only.service.MemberService;
import only.service.PostService;
import only.utils.WebConstants;
import org.json.JSONObject;

@Controller
public class OnlyController {
	static int currentPage = 1;

	@Autowired
	private MemberService ms;
	@Autowired
	private PostService ps;
	@Autowired
	private CommentService cs;
	@Autowired
	private LikesService ls;

	@RequestMapping("/chat")
	public String chat(String userID, HttpSession session) {
		Member member = ms.getMemberById(userID);
		if (member != null) {
			session.setAttribute("userID", member.getUserid());
			session.setAttribute("nickname", member.getUsername());
		}
		return "chat";
	}

	@RequestMapping("/joinus/id_check")
	public @ResponseBody int joinusIdChk(String id) {
		Member member = ms.getMemberById(id);
		if (member == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@RequestMapping("/joinus/signUpForm")
	public String signUpForm() {
		return "joinus/signUpForm";
	}

	@RequestMapping("/joinus/signUp")
	public String signUp(Member member, Model model) {
		System.out.println(member.getUserid() + ", " + member.getUsername() + ", " + member.getPassword() + ", "
				+ member.getEmail());
		int result = ms.insert(member);
		if (result > 0) {
			System.out.println("SignUp 성공");
			model.addAttribute("signUpResult", "가입성공! 로그인하세요");

		} else {
			System.out.println("SignUp 실패");
			model.addAttribute("signUpResult", "가입실패<br>다시 시도하세요");
		}
		return "joinus/signUpForm";
	}

	@RequestMapping("/joinus/login")
	public String login(String member_id, String password, Model model, HttpSession session) {
		System.out.println("login : " + member_id);
		Member member = ms.getMemberById(member_id);
		if (member == null) {
			System.out.println("ID가 존재하지 않습니다");
			model.addAttribute("message", "ID가 존재하지 않습니다");
			return "joinus/signUpForm";
		} else {
			if (!member.getPassword().equals(password)) {
				System.out.println("비밀번호가 일치하지 않습니다");
				model.addAttribute("message", "비밀번호가 일치하지 않습니다");
				return "joinus/signUpForm";
			} else {
				System.out.println("로그인 성공");
				session.setAttribute(WebConstants.USER_ID, member_id);
				session.setAttribute("member", member);
				return "redirect:/timeline";
			}
		}
	}

	@RequestMapping("/search")
	public @ResponseBody List<Member> search(String searchTerm) {
		System.out.println("Search 실행.. " + searchTerm);
		List<Member> result = ms.searchMember(searchTerm);
		System.out.println(result.size() + " returned");
		return result;
	}

	@RequestMapping(value= {"/searchResult", "/blog/searchResult"})
	public String searchResult(String searchTerm, HttpServletRequest request, Model model) {
		List<Member> result = ms.searchMember(searchTerm);
		model.addAttribute("searchResult", result);
		System.out.println(request.getServerName());
		return "searchResult";
	}

	@RequestMapping("/timeline")
	public String timeline() {
		return "timeline";
	}

	@RequestMapping("/loadPost")
	public String loadPost(String userid, String pageNum, Model model, HttpSession session) {
		if (userid == null || userid.equals("")) {
			userid = (String) session.getAttribute(WebConstants.USER_ID);
		}
		// System.out.println("loadPost().." + userid + "," + pageNum);
		List<Post> plist = ps.getTimelinePost(userid, pageNum);
		for (Post post : plist) {
			post.setCommentCount(cs.getCommentCount(post.getPid(), 0));
			post.setIsLiked(isLiked(userid, post.getPid(), WebConstants.POST));
		}
		model.addAttribute("plist", plist);

		return "postBuild";
	}

	@RequestMapping("/loadBlog")
	public String loadBlog(String ownerid, String userid, String pageNum, Model model, HttpSession session) {
		System.out.println("loadBlog called");
		if (userid == null || userid.equals("")) {
			userid = (String) session.getAttribute(WebConstants.USER_ID);
		}
		// System.out.println("loadPost().." + userid + "," + pageNum);
		List<Post> plist = ps.getBlogPost(ownerid, pageNum);
		for (Post post : plist) {
			post.setCommentCount(cs.getCommentCount(post.getPid(), 0));
			post.setIsLiked(isLiked(userid, post.getPid(), WebConstants.POST));
		}
		model.addAttribute("plist", plist);

		return "postBuild";
	}	
	
	@RequestMapping("/loadComment")
	public String loadComment(int ref_id, int ref_type, int pageNum, Model model, HttpSession session) {
		// System.out.println("loacomment().." + ref_id + "," + ref_type + ", " + pageNum);
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Comments> clist = cs.getComments(ref_id, ref_type, pageNum);
		for (Comments comment : clist) {
			comment.setCommentCount(cs.getCommentCount(comment.getCid(), 1));
			comment.setIsLiked(isLiked(userid, comment.getCid(), WebConstants.COMMENT));
		}
		model.addAttribute("ref_type", ref_type);
		model.addAttribute("ref_id", ref_id);
		model.addAttribute("ref_c_num", cs.getCommentCount(ref_id, ref_type));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("COMMENTS_PER_PAGE", WebConstants.COMMENTS_PER_PAGE);
		model.addAttribute("clist", clist);
		return "commentBuild";
	}
	
	@RequestMapping("/getEachPost")
	public @ResponseBody Post getEachPost(String pid) {
		Post post = ps.getPost(pid);
		// System.out.println("loadPost: " + result.size());
		return post;
	}

	@RequestMapping("/changeProfile")
	public String changeProfile(Model model, String member_id) {
		Member member = ms.getMemberById(member_id);
		model.addAttribute("member", member);
		return "changeProfile";
	}

	@RequestMapping(value = "/profileDone")
	public String profileDone(Member member, String birth1, Model model, MultipartFile profile_image1,
			HttpServletRequest request, HttpSession session, String existingImage) {
		if (profile_image1.getOriginalFilename() != null && !profile_image1.getOriginalFilename().equals("")) {
			System.out.println("cheak in");
			System.out.println(profile_image1.getOriginalFilename());
			if (profile_image1.getOriginalFilename() != null && !profile_image1.getOriginalFilename().equals("")) {
				try {
					byte[] bytes = profile_image1.getBytes();
					String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/img_timeline");
					File serverFile = new File(rootPath + File.separator + profile_image1.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				member.setProfile_image(profile_image1.getOriginalFilename());
			}
		} else
			member.setProfile_image(existingImage);

		if (birth1 != null && !birth1.equals("")) {
			member.setBirth(Date.valueOf(birth1));
		}
		int result = ms.update(member);
		if (result > 0) { // session에 저장된 member를 update
			session.setAttribute("member", member);
		}
		return "profileDone";
	}

	@RequestMapping(value = "/blog/{owner}")
	public String blog(@PathVariable String owner, Model model) {
		System.out.println("owner: " + owner);
		Member blogOwner = ms.getMemberById(owner);
		model.addAttribute("owner", blogOwner);
		return "blog/blog";
	}

	// User가 해당 포스트 좋아요를 눌렀는지 체크
	// @RequestMapping(value = "/isLiked")
	private boolean isLiked(String userid, int lid, int type) {
		// type { 0: post / 1: comment}
		Likes l = new Likes();
		l.setUserid(userid);
		l.setLid(lid);
		l.setType(type); // 0: post 1: comment
		int result = ls.isLiked(l);
		// System.out.println(userid + ", " + lid + ", " + type + "=> "+result);
		if(result>0)
			return true;
		else 
			return false;
	}

	// 좋아요 Toggle
	@RequestMapping(value = "/toggleLikes")
	public @ResponseBody int toggleHeart(String userid, int lid, int type) {
		// type { 0: post / 1: comment}
		int count = 0;
		// System.out.println(userid + ", " + lid + ", " + type);
		Likes l = new Likes();
		l.setUserid(userid);
		l.setLid(lid);
		l.setType(type); // 0: post 1: comment
		ls.toggleLikes(l);
		if(type ==0) {
			count = ps.getLikesCount(lid);
		}
		else{
			count = cs.getLikesCount(lid);
		}
		System.out.println("toggleLikes: " + count);
		return count;
	}
	
	// 이미지 파일 불러오기
	@RequestMapping(value="/getImages")
	public @ResponseBody List<String> getImages(String userid, int pageNum){
		List<String> imageFiles = ps.getImagesByUserid(userid, pageNum);
		return imageFiles;
	}
	// 총 이미지 수 리턴
	@RequestMapping(value="/getImageTotal")
	public @ResponseBody int getImageTotal(String userid){
		int total = ps.getImageTotal(userid);
		return total;
	}
	
	// ThumbProfile Image Update
	@RequestMapping(value="/updateProfileImage", method=RequestMethod.GET)
	public String updateProfileImage(@RequestParam("url") String url, HttpSession session){
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		int result = ms.updateThumbProfile(userid, url);
		Member member = (Member) session.getAttribute("member");
		member.setProfile_image(url);
		return "redirect:blog/"+userid;
	}
	
}
