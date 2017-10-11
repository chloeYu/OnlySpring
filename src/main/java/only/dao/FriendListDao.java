package only.dao;

import java.util.List;

import only.model.Friendlist;

public interface FriendListDao {
	public List<Friendlist> FriendListLoad(String member_id);

	int update(Friendlist Friendlist);
}
