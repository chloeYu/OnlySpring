package only.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import only.model.Member;
import only.model.Post;
import only.service.MemberService;
import only.service.PostService;
import only.utils.WebConstants;

@Controller
public class OnlyController {
	static int currentPage = 1;
	
	@Autowired
	private MemberService ms;
	@Autowired
	private PostService ps;
	
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	
	@RequestMapping("/joinus/id_check")
	public @ResponseBody int joinusIdChk(String id) {
		Member member = ms.getMemberById(id);
		if(member==null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	@RequestMapping("/joinus/signUpForm")
	public String signUpForm() {
		return "joinus/signUpForm";
	}
	
	
	@RequestMapping("/joinus/signUp")
	public String signUp(Member member, Model model) {
		System.out.println(member.getUserid()+", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail());
		int result = ms.insert(member);
		if(result>0){
			System.out.println("SignUp 성공");
			model.addAttribute("signUpResult", "가입성공! 로그인하세요");
			
		} else{
			System.out.println("SignUp 실패");
			model.addAttribute("signUpResult", "가입실패<br>다시 시도하세요");
		}
		return "joinus/signUpForm";
	}
	
	@RequestMapping("/joinus/login")
	public String login(String member_id, String password, Model model, HttpSession session) {
		System.out.println("login : " + member_id);
		Member member = ms.getMemberById(member_id);
		if(member == null) {
			System.out.println("ID가 존재하지 않습니다");
			model.addAttribute("message", "ID가 존재하지 않습니다");
			return "joinus/signUpForm";
		} else {
			if(!member.getPassword().equals(password)) {
				System.out.println("ㅂ밀번호가 일치하지 않습니다");
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
	
	@RequestMapping("/loadPost")
	public @ResponseBody List<Post> loadPost(String userid, String pageNum, Model model){
		List<Post> result = ps.getTimelinePost(userid, pageNum);
		System.out.println("loadPost: " + result.size());

/*		String[] sh = {"제목", "작성자", "내용","제목+내용"};
		model.addAttribute("sh", sh);
		model.addAttribute("list", list);
		model.addAttribute("pageNum", currentPage);
*/		return result;
	}
	
	@RequestMapping("/getEachPost")
	public @ResponseBody Post getEachPost(String pid){
		Post post = ps.getPost(pid);
		//System.out.println("loadPost: " + result.size());
		return post;
	}
	
	@RequestMapping("/postWrite")
	public String postWrite(HttpServletRequest request) {
		return "";
	}
}
