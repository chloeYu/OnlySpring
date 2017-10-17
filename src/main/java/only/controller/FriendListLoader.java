package only.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import only.model.Friendlist;
import only.model.Member;
import only.service.*;
import only.utils.WebConstants;

@Controller
public class FriendListLoader {
	@Autowired
	private MemberService ms;
	@Autowired
	private FriendListService fs;

	@RequestMapping("/friendList")
	public String friendList(Model model, String member_id, HttpSession session) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Member> friendList = fs.friendListLoad(member_id, userid);
		List<Member> pendingList = fs.pendingListLoad(member_id);
		List<Member> requestList = fs.rquestList(member_id);
		model.addAttribute("friendList", friendList); // 친구 리스트
		model.addAttribute("pendingList", pendingList); // 대기 리스트 (uid1: 사용자)
		model.addAttribute("requestList", requestList); // 요청 리스트 (uid2: 사용자)
		return "friendList";
	}

	@RequestMapping("/friendupdate/{status}/{uid1}/{uid2}")
	public void Friendaccpet(Friendlist Friendlist, Model model, @PathVariable int status, @PathVariable String uid1,
			@PathVariable String uid2, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Friendlist.setStatus(status);
		Friendlist.setUid1(uid1);
		Friendlist.setUid2(uid2);
		fs.update(Friendlist);
//		String referer = request.getHeader("Referer");
//		System.out.println(referer);
//		response.sendRedirect(referer);
//		 return "friendList";
	}

	@RequestMapping(value="/friendupdate", method = RequestMethod.POST)
	public @ResponseBody int updateFriendStatus(String uid1, String uid2, int status) {
		Friendlist f = new Friendlist();
		f.setUid1(uid1);
		f.setUid2(uid2);
		f.setStatus(status);
		
		if(status == 2) { // 친구 요청일 경우
				if(fs.getFriendList(uid1, uid2)==null && fs.getFriendList(uid2, uid1)==null) { // 해당 친구관계가 없으면 추가
					System.out.println("새 요청");
					return fs.insertRequest(f);
				}
				
				if(fs.getFriendList(uid2, uid1)!=null) {
					System.out.println("사용자 바꿔서 수정");
					return fs.insertUserSwitchedRequest(f);
				}
			}
		System.out.println("friendUpdate called " + uid1 + ", " + uid2 + ", " + status);
		return fs.update(f);
	}
	
	@RequestMapping("/blog/{owner}/friendList")
	public String friendListByUser(@PathVariable String owner, HttpSession session, Model model) {
		Member blogOwner = ms.getMemberById(owner);
		String userid = (String) session.getAttribute(WebConstants.USER_ID);		
		model.addAttribute("owner", blogOwner);
		return "blog/blogFriendList";
	}

	
	@RequestMapping("/appendFriendList")
	public String friendList(String owner, int pageNum, HttpSession session, Model model) {
		Member blogOwner = ms.getMemberById(owner);
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Member> friendList = fs.friendListLoad(owner, userid, pageNum);
		
		System.out.println("returned size" + friendList.size());
		model.addAttribute("owner", blogOwner);
		model.addAttribute("friendList", friendList);

		return "blog/friendList";
	}

	/*
	 * @RequestMapping("/getFriendListByUserid") public String
	 * getFriendListByUserid(String userid, HttpSession session, Model model) {
	 * List<Member> friendList =fs.friendListLoad(userid); List<Member> pendingList
	 * = fs.pendingListLoad(userid); List<Member> requestList =
	 * fs.rquestList(userid); model.addAttribute("friendList",friendList);
	 * model.addAttribute("pendingList",pendingList);
	 * model.addAttribute("requestList",requestList);
	 * System.out.println("getFriendByUserId" + friendList.size() + ", " +
	 * pendingList.size() + ", " +requestList.size()); return "blog/blogFriendList";
	 * }
	 */
}