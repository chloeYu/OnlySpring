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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import only.model.Member;
import only.model.Post;
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
		return result;
	}

	@RequestMapping("/searchResult")
	public String searchResult(String searchTerm, Model model) {
		List<Member> result = ms.searchMember(searchTerm);
		model.addAttribute("searchResult", result);
		return "searchResult";
	}

	@RequestMapping("/timeline")
	public String timeline() {
		return "timeline";
	}

	@RequestMapping("/postBuild")
	public String postBuild(HttpServletRequest request, Model model) {
		System.out.println("postBuild()..: ");
		/*List<Object> list = request.getParameter("post");
		System.out.println(jObj.keySet());*/
		/*Post post = new Post();
		// [created, modified, files,]
		post.setUserid(jObj.getString("userid"));
		post.setUsername(jObj.getString("username"));
		post.setPid(Integer.valueOf(jObj.getInt("pid")));
		post.setText(jObj.getString("text"));
		post.setType(jObj.getString("type"));
		//post.setFiles();
		post.setCreated(Date.valueOf(jObj.getString("created")));
		post.setModified(Date.valueOf(jObj.getString("modified")));
		post.setDel(jObj.getString("del"));
		System.out.println("post: " + post);
		model.addAttribute("post", post);*/
		return "postBuild";
		/*System.out.println("loadPost().." + userid + "," + pageNum);
		List<Post> plist = ps.getTimelinePost(userid, pageNum);
		StringBuilder htmlBuilder = new StringBuilder();
		// Build Posts
		if (plist.size() == 0) {
			htmlBuilder.append("No Post");
		} else if (plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				// li Tag 추가
				htmlBuilder.append("<li class='infinite_scroll'>");
				// Post 작성자 이름 추가
				htmlBuilder.append("<div class='postUid'><span>" + plist.get(i).getUsername() + "</span></div><hr>");
				// 텍스트 추가
				if (plist.get(i).getText() != null && !plist.get(i).getText().equals("")) {
					htmlBuilder.append("<h3>" + plist.get(i).getText() + "</h3>");
				}
				// 이미지 추가
				List<MultipartFile> fileList = plist.get(i).getFiles();
				System.out.println("Returned Image # : " + plist.get(i).getFiles().size());
				for (int j = 0; j < fileList.size(); j++) {
					System.out.println(fileList.get(0));
					if (fileList.size() == 1) {
						htmlBuilder.append("<div class='postImg1'><img class='postInner1' src='img_timeline/"
								+ fileList.get(j).getOriginalFilename() + "'></div>");
					} else if (fileList.size() == 2) {
						htmlBuilder.append("<div class='postImg1'><img class='postInner1' src='img_timeline/"
								+ fileList.get(j).getOriginalFilename() + "'></div>");
					} else if (fileList.size() == 3) {
						htmlBuilder.append("<div class='postImg1'><img class='postInner1' src='img_timeline/"
								+ fileList.get(j).getOriginalFilename() + "'></div>");
					} else if (fileList.size() == 4) {
						htmlBuilder.append("<div class='postImg2'><img class='postInner2' src='img_timeline/"
								+ fileList.get(j).getOriginalFilename() + "'></div>");
					} else if (fileList.size() > 4) {
						if (fileList.size() == 3) {
							htmlBuilder.append("<div class='postImg3'><img class='postInner3' src='img_timeline/"
									+ fileList.get(j).getOriginalFilename() + "'><span>+More</span></div>");
						} else {
							htmlBuilder.append("<div class='postImg3'><img class='postInner3' src='img_timeline/"
									+ fileList.get(j).getOriginalFilename() + "'></div>");
						}
					}
				}
				// React div open
				htmlBuilder.append("<div class='reactBtn'>");
				// 좋아요 아이콘 추가
				htmlBuilder.append("<div class='heart' id='heart-" + plist.get(i).getUserid() + "'></div>");
				// Repost 아이콘 추가
				htmlBuilder.append("<div class='share_out' onclick='openLayer('layerPop',200,18)'></div>");
				// 댓글 추가
				htmlBuilder.append("<form action='commentWrite'><div class='commentForm'><input type='hidden' value='"
						+ userid + "' name='userid'>");
				htmlBuilder.append(
						"<textarea row='1' cols='1' name='commentText' placeholder='Comments' class='comment_textarea'></textarea>");
				htmlBuilder.append("<button class='commentBtn'>입력</button>");
				htmlBuilder.append("<input type='hidden' value='" + plist.get(i).getPid() + "' name='commentPid'></div>");
				htmlBuilder.append("<div class='postLayoutClear'></div></form>");
				// React div close
				htmlBuilder.append("</li>");
			}
		}
		System.out.println(htmlBuilder);
		return htmlBuilder;*/
	}

	@RequestMapping("/loadPost")
	public @ResponseBody List<Post> loadPost(String userid, String pageNum, Model model) {
		System.out.println("loadPost().." + userid + "," + pageNum);
		List<Post> plist = ps.getTimelinePost(userid, pageNum);

		return plist;
	}

	@RequestMapping("/getEachPost")
	public @ResponseBody Post getEachPost(String pid) {
		Post post = ps.getPost(pid);
		// System.out.println("loadPost: " + result.size());
		return post;
	}

	@RequestMapping("/postWrite")
	public String postWrite(HttpServletRequest request) {
		return "";
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
}
