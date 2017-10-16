package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.FriendListDao;
import only.model.Friendlist;
import only.model.Member;

@Service
public class FriendListServiceImpl implements FriendListService {
	@Autowired
	private FriendListDao fd;

	@Override
	public List<Member> friendListLoad(String owner, String userid) {
		return fd.friendListLoad(owner, userid);
	}

	@Override
	public List<Member> pendingListLoad(String member_id) {
		return fd.pendingListLoad(member_id);
	}

	@Override
	public List<Member> rquestList(String member_id) {
		return fd.rquestList(member_id);
	}

	@Override
	public int update(Friendlist Friendlist) {
		return fd.update(Friendlist);
	}

	@Override
	public Friendlist getFriendList(String uid1, String uid2) {
		return fd.getFriendList(uid1, uid2);
	}

	@Override
	public int insertRequest(Friendlist f) {
		return fd.insertRequest(f);
	}

	@Override
	public int insertUserSwitchedRequest(Friendlist f) {
		return fd.insertUserSwitchedRequest(f);
	}
}