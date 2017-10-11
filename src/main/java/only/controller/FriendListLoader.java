package only.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import only.model.Friendlist;
import only.model.Member;
import only.service.*;
import only.utils.WebConstants;

@Controller
public class FriendListLoader {
	@Autowired
	private FriendListService fs;
	
	@RequestMapping("/friendList")
	public String friendList(Model model, String member_id) {
		List<Member> friendList =fs.friendListLoad(member_id);
		List<Member> pendingList = fs.pendingListLoad(member_id);
		List<Member> requestList = fs.rquestList(member_id);
		model.addAttribute("friendList",friendList); // 친구 리스트
		model.addAttribute("pendingList",pendingList); // 대기 리스트 (uid1: 사용자)
		model.addAttribute("requestList",requestList); // 요청 리스트 (uid2: 사용자)
		return "friendList";	
	}
	@RequestMapping("/friendupdate/{status}/{uid1}/{uid2}")
	public String Friendaccpet(Friendlist Friendlist, Model model, @PathVariable int status, @PathVariable String uid1, @PathVariable String uid2) {
		Friendlist.setStatus(status);
		Friendlist.setUid1(uid1);
		Friendlist.setUid2(uid2);
		fs.update(Friendlist);
		return "friendList";
	}
	
	@RequestMapping("/getFriendListByUserid")
	public String getFriendListByUserid(String userid, HttpSession session, Model model) {
		List<Member> friendList =fs.friendListLoad(userid);
		List<Member> pendingList = fs.pendingListLoad(userid);
		List<Member> requestList = fs.rquestList(userid);
		model.addAttribute("friendList",friendList);
		model.addAttribute("pendingList",pendingList);
		model.addAttribute("requestList",requestList);
		System.out.println("getFriendByUserId" + friendList.size() + ", " + pendingList.size() + ", " +requestList.size());
		return "blog/blogFriendList";	
	}
}