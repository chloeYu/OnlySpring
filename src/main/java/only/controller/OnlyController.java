package only.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import only.model.Alert;
import only.model.Chat;
import only.model.Comments;
import only.model.HashTag;
import only.model.Likes;
import only.model.Member;
import only.model.Page;
import only.model.Post;
import only.model.Post_Files;
import only.model.User;
import only.service.AlertService;
import only.service.CommentService;
import only.service.FriendListService;
import only.service.HashTagService;
import only.service.LikesService;
import only.service.MemberService;
import only.service.PageService;
import only.service.PostService;
import only.service.TextMessageListService;
import only.utils.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class OnlyController {
	private static final Logger logger = LoggerFactory.getLogger(OnlyController.class);
	static int currentPage = 1;

	@Autowired
	private MemberService ms;
	@Autowired
	private PostService ps;
	@Autowired
	private CommentService cs;
	@Autowired
	private LikesService ls;
	@Autowired
	private FriendListService fs;
	@Autowired
	private AlertService as;
	@Autowired
	private TextMessageListService tmls;
	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private HashTagService hs;
	@Autowired
	private PageService pages;
	
	// 채팅 컨트롤러
	@RequestMapping("/chat")
	public String chat(String userID, HttpSession session) {
		Member member = ms.getMemberById(userID);
		if (member != null) {
			session.setAttribute("userID", member.getUserid());
			session.setAttribute("nickname", member.getUsername());
		}
		return "chat";
	}

	@RequestMapping(value = "/messageList", method = RequestMethod.POST)
	public @ResponseBody List<Chat> messageList(Model model, HttpSession session) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Chat> messageList = tmls.getChatMessageList(userid);
		model.addAttribute("messageList", messageList);
		System.out.println(messageList);

		return messageList;
	}

	@RequestMapping(value = "/messageCount", method = RequestMethod.POST)
	public @ResponseBody int messageCount(Model model, HttpSession session, String userid) {
		userid = (String) session.getAttribute(WebConstants.USER_ID);
		int messageCount = tmls.getUnreadMessageCount(userid);
		model.addAttribute("messageCount", messageCount);
		System.out.println("읽지않은 메시지 총= " + messageCount);
		return messageCount;
	}

	@RequestMapping(value = "/messageRoomCount", method = RequestMethod.POST)
	public @ResponseBody List<Chat> messageRoomCount(Model model, HttpSession session, String userid) {
		userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Chat> messageRoomCount = tmls.UnreadRoomMessageCount(userid);
		model.addAttribute("messageCount", messageRoomCount);
		System.out.println("chatRoom 별로 데이터값 = " + messageRoomCount);
		return messageRoomCount;
	}
	// 채팅 컨트롤러 끝

	// Alarm List
	@RequestMapping("/alarmList")
	public String alarmList(String userid, Model model) {
		System.out.println("Alert List called");
		List<Alert> alerts = as.getAlertList(userid);
		model.addAttribute("alerts", alerts);
		return "alertList";
	}

	@RequestMapping("/checkReadNotification")
	public int checkReadNotification(int aid) {
		int result = as.checkNotification(aid);
		return result;
	}

	@RequestMapping("/getPostFiles")
	public @ResponseBody List<Post_Files> postFiles(int pid, Model model) {
		List<Post_Files> files = ps.getImagesByPid(pid);
		return files;
	}

	@RequestMapping("/id_check")
	public @ResponseBody int joinusIdChk(String id) {
		Member member = ms.getMemberById(id);
		if (member == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@RequestMapping(value = "/joinus/signUpForm", method = RequestMethod.GET)
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

	/*
	 * @RequestMapping("/joinus/login") public String login(String member_id, String
	 * password, Model model, HttpSession session) { System.out.println("login : " +
	 * member_id); Member member = ms.getMemberById(member_id); if (member == null)
	 * { System.out.println("ID가 존재하지 않습니다"); model.addAttribute("message",
	 * "ID가 존재하지 않습니다"); return "joinus/signUpForm"; } else { if
	 * (!member.getPassword().equals(password)) {
	 * System.out.println("비밀번호가 일치하지 않습니다"); model.addAttribute("message",
	 * "비밀번호가 일치하지 않습니다"); return "joinus/signUpForm"; } else {
	 * System.out.println("로그인 성공"); session.setAttribute(WebConstants.USER_ID,
	 * member_id); session.setAttribute("member", member); return
	 * "redirect:/timeline"; } } }
	 */

	@RequestMapping("/search")
	public @ResponseBody List<Member> search(String searchTerm, HttpSession session) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		System.out.println("Search 실행.. " + searchTerm);
		List<Member> result = ms.searchMember(searchTerm, userid);
		System.out.println(result.size() + " returned");
		return result;
	}

	@RequestMapping(value = { "/searchResult" })
	public String searchResult(String searchTerm, HttpServletRequest request, HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Member> result = ms.searchMember(searchTerm, userid);
		model.addAttribute("searchResult", result);
		System.out.println(request.getServerName());
		for (Member m : result) {
			System.out.println(m);
		}
		return "searchResult";
	}

	@RequestMapping("/login")
	public String login(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println("session registered for " + name);
		Member member = ms.getMemberById(name);
		session.setAttribute("member", member);
		session.setAttribute(WebConstants.USER_ID, name);
		System.out.println(member);
		return "redirect:/timeline";
	}

	@RequestMapping("/contactUpdate")
	public String contactUpdate(String userid, HttpSession session, Model model) {
		String id = (String) session.getAttribute(WebConstants.USER_ID);
		List<Member> contacts = ms.getLoggedInMembers(id);
		model.addAttribute("contacts", contacts);
		return "contactUpdate";
	}

	@RequestMapping("/timeline")
	public String timeline(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		List<Member> contacts = ms.getLoggedInMembers(name);
		List<HashTag> hashtags = hs.getTopNHashTags(10);
		System.out.println(hashtags.size() + "개의 hashtag");
		model.addAttribute("contacts", contacts);
		model.addAttribute("hashtags", hashtags);
		for (Object p : sessionRegistry.getAllPrincipals()) {
			if (p instanceof User) {
				User user = (User) p;
				System.out.println(user.getUsername());
			} else if (p instanceof Member) {
				Member m = (Member) p;
				System.out.println(m.getUsername());
			} else if (p instanceof String) {
				System.out.println(p);
			} else {
				System.out.println("무슨 타입인지 모름");
			}
		}
		return "timeline";
	}

	@RequestMapping("/loadPost")
	public String loadPost(String userid, String pageNum, Model model, HttpSession session) {
		if (userid == null || userid.equals("")) {
			userid = (String) session.getAttribute(WebConstants.USER_ID);
		}
		System.out.println("loadPost().." + userid + "," + pageNum);
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
		// System.out.println("loacomment().." + ref_id + "," + ref_type + ", " +
		// pageNum);
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

	@RequestMapping(value = "/logout")
	public void logout(HttpSession session) {
		System.out.println("Welcome logout!" + session.getId());
		session.invalidate();
	}

	@RequestMapping(value = "/login_duplicate")
	public String login_duplicate(RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response, Model model) throws IOException {
		System.out.println("forward to relogin");
		redirectAttributes.addFlashAttribute("error", "다른 기기에서 로그인 하였습니다");
		return "redirect:/timeline";
	}

	@RequestMapping(value = "/blog/{owner}")
	public String blog(@PathVariable String owner, HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		System.out.println("owner: " + owner);
		Member blogOwner = ms.getMemberById(owner);
		List<String> photoList = ps.getImagesByUserid(owner, 1);
		List<Member> friendList = fs.friendListLoad(owner, userid, 1);
		model.addAttribute("owner", blogOwner);
		model.addAttribute("photoList", photoList);
		model.addAttribute("friendList", friendList);
		return "blog/blog";
	}

	// 블로그 About 페이지
	@RequestMapping(value = "/blog/{owner}/about")
	public String about(@PathVariable String owner, HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		System.out.println("owner: " + owner);
		Member blogOwner = ms.getMemberById(owner);
		List<Member> friendList = fs.friendListLoad(owner, userid, 1);
		List<String> photoList = ps.getImagesByUserid(userid, 1);
		model.addAttribute("owner", blogOwner);
		model.addAttribute("friendList", friendList);
		model.addAttribute("photoList", photoList);
		return "blog/about";
	}

	// 블로그 Photos 페이지
	@RequestMapping(value = "/blog/{owner}/photos")
	public String photos(@PathVariable String owner, HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		System.out.println("owner: " + owner);
		Member blogOwner = ms.getMemberById(owner);

		model.addAttribute("owner", blogOwner);
		return "blog/blogPictureList";
	}

	// 블로그 Photos 불러오기
	@RequestMapping(value = "/appendPictureList")
	public String appendPhotos(String owner, int pageNum, HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<String> photoList = ps.getImagesByUserid(userid, pageNum);
		model.addAttribute("photoList", photoList);
		return "blog/photoList";
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
		if (result > 0)
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
		if (type == 0) {
			count = ps.getLikesCount(lid);
		} else {
			count = cs.getLikesCount(lid);
		}
		System.out.println("toggleLikes: " + count);
		return count;
	}

	// 이미지 파일 불러오기
	@RequestMapping(value = "/getImages")
	public @ResponseBody List<String> getImages(String userid, int pageNum) {
		List<String> imageFiles = ps.getImagesByUserid(userid, pageNum);
		return imageFiles;
	}

	// 총 이미지 수 리턴
	@RequestMapping(value = "/getImageTotal")
	public @ResponseBody int getImageTotal(String userid) {
		int total = ps.getImageTotal(userid);
		return total;
	}

	// ThumbProfile Image Update - existing image
	@RequestMapping(value = "/updateProfileImage", method = RequestMethod.GET)
	public String updateProfileImage(@RequestParam("url") String url, @RequestParam("type") int updateType,
			HttpSession session) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		Member member = (Member) session.getAttribute("member");
		int result = 0;
		if (updateType == 0) {
			result = ms.updateCoverProfile(userid, url);
			member.setCover_image(url);
		} else if (updateType == 1) {
			result = ms.updateThumbProfile(userid, url);
			member.setProfile_image(url);
		}
		return "redirect:blog/" + userid;
	}

	// ThumbProfile Image Update - new image
	@RequestMapping(value = "/updateProfileImage", method = RequestMethod.POST)
	public String updateProfileImage2(MultipartFile file, int updateType, HttpServletRequest request,
			HttpSession session) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		System.out.println("Uploaded File: " + file.getOriginalFilename() + ", " + updateType);
		Member member = (Member) session.getAttribute("member");
		if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/img_timeline");
				File serverFile = new File(rootPath + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (updateType == 0) {
				ms.updateCoverProfile(userid, file.getOriginalFilename());
				member.setCover_image(file.getOriginalFilename());

			} else if (updateType == 1) {
				ms.updateThumbProfile(userid, file.getOriginalFilename());
				member.setProfile_image(file.getOriginalFilename());
			}

		}
		return "redirect:blog/" + userid;
	}

	// Alert
	@RequestMapping("/updateNotification")
	public @ResponseBody int notificationUpdate(String type, HttpSession session) {
		int unchecked = 0;
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		if (type.equals("chat")) {
			// ChatDao cdo = ChatDao.getInstance();
			// unchecked = cdo.checkUnreadMessage((String)
			// session.getAttribute("sessionId"));
			// System.out.println("unread message: " + unchecked);
		} else if (type.equals("post")) {
			unchecked = as.uncheckedAlert(userid);
			System.out.println("unchecked alert: " + unchecked);
		}
		return unchecked;
	}

	// 페이지
	@RequestMapping("/page")
	public String page(Page page, HttpSession session) throws Exception {
		page.setUserid((String) session.getAttribute(WebConstants.USER_ID));
		System.out.println(page.getDel());
		page.setDel(pages.count(page));
		int i = pages.pcount(page);
		if (i > 0) {
			page.setPid(pages.selet_pid(page));
		}
		return "page/page";
	}

	@RequestMapping("/pagemain/{pp}")
	public String pagemain(@PathVariable String pp, Member member, Model model, HttpSession session) throws Exception {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		Page pagepp = pages.getPageById(pp);
		System.out.println(pagepp.getDel());
		model.addAttribute("pp", pagepp);
		int i = pages.pcount(pagepp);
		if (i < 1) {
			return "page/page";
		}
		return "page/pagemain";
	}

	@RequestMapping(value = "/pageCreate", method = RequestMethod.GET)
	public String pageCreate(Page page, HttpSession session) throws Exception {
		page.setUserid((String) session.getAttribute(WebConstants.USER_ID));
		page.setDel(pages.count(page));
		page.setPid(pages.count(page));
		int i = pages.pcount(page);
		if (i > 0) {
			page.setPid(pages.selet_pid(page));
			return "page/page";
		}
		return "page/pageCreate";
	}

	@RequestMapping(value = "/pageCreate", method = RequestMethod.POST)
	public String pageCreate(Page page, Model model, HttpSession session) throws Exception {
		page.setUserid((String) session.getAttribute(WebConstants.USER_ID));
		model.addAttribute("page", page);
		page.setDel(pages.count(page));
		pages.insert(page);
		return "page/page";
	}
}
