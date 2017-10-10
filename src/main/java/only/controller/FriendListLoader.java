package only.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import only.model.Friendlist;
import only.service.*;

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
	@RequestMapping("/friendaccpet")
	public String Friendaccpet(Model model, String member_id, String member_id2) {
		
		return "friendList";
	}
	@RequestMapping("/friendrefuse")
	public String Friendrefuse(Model model, String member_id, String member_id2) {
		
		return "friendList";
	}
}