package only.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import only.model.Member;
import only.service.MemberService;
import only.utils.WebConstants;

@Controller
public class OnlyController {
	@Autowired
	private MemberService ms;
	
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}

	@RequestMapping("/joinus/signUpForm")
	public String signUpForm() {
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
				return "redirect:/timeline";
			}
		}
	}
	
	@RequestMapping("/search")
	public @ResponseBody List<Member> search(String searchTerm) {
		System.out.println("Search 실행.. " + searchTerm);		
		List<Member> result = ms.searchMember(searchTerm);
		System.out.println(result.size());
		return result;
	}
	@RequestMapping("/timeline")
	public String timeline() {
		return "timeline";
	}
}
