package only.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import only.model.Friendlist;
import only.service.*;
import only.utils.WebConstants;

@Controller
public class FriendListLoader {
	@Autowired
	private FriendListService fs;
	
	@RequestMapping("/friendList")
	public String FriendList(Model model, String member_id) {
		List<Friendlist> listFriend =fs.FriendListLoad(member_id);
		model.addAttribute("listFriend",listFriend);
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
	
	@RequestMapping("/friendsPage")
	public String friendsPage(HttpSession session, Model model) {
		String userid = (String) session.getAttribute(WebConstants.USER_ID);
		List<Friendlist> listFriend =fs.FriendListLoad(userid);
		model.addAttribute("listFriend",listFriend);
		return "friendsPage";	
	}
}