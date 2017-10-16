package only.dao;

import java.util.List;

import only.model.Friendlist;
import only.model.Member;

public interface FriendListDao {

	public List<Member> friendListLoad(String owner, String userid);
	public List<Member> friendListLoad(String owner, String userid, int pageNUm);
	
	public List<Member> pendingListLoad(String member_id);

	public List<Member> rquestList(String member_id);

	int update(Friendlist Friendlist);
	public Friendlist getFriendList(String uid1, String uid2);
	public int insertRequest(Friendlist f);
	public int insertUserSwitchedRequest(Friendlist f);
}
