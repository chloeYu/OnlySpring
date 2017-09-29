package only.service;

import java.util.List;

import only.model.Friendlist;

public interface FriendListService {
	public List<Friendlist> FriendListLoad(String member_id);
}
