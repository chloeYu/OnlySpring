package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.FriendListDao;
import only.model.Friendlist;

@Service
public class FriendListServiceImpl implements FriendListService {
	@Autowired
	private FriendListDao fd;

	@Override
	public List<Friendlist> FriendListLoad(String member_id) {
		return fd.FriendListLoad(member_id);
	}
}