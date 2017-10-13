package only.dao;

import java.util.List;

import only.model.Friendlist;
import only.model.Member;

public interface FriendListDao {

	public List<Member> friendListLoad(String member_id);
	public List<Member> friendListLoad(String ownerid, int pageNum);
	
	public List<Member> pendingListLoad(String member_id);

	public List<Member> rquestList(String member_id);

	int update(Friendlist Friendlist);


}
