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
	public List<Member> friendListLoad(String member_id) {
		return fd.friendListLoad(member_id);
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
}