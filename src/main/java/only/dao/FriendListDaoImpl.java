package only.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Friendlist;
import only.model.Member;
import only.model.Post;
@Repository
public class FriendListDaoImpl implements FriendListDao {
	final static int FRIENDPERPAGE = 10;
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Member> friendListLoad(String owner, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("owner", owner);
		map.put("userid", userid);
		
		return sst.selectList("friendlistns.friendList", map);
	}

	@Override
	public List<Member> friendListLoad(String owner, String userid, int pageNum) {
		int startRow = (pageNum - 1) * FRIENDPERPAGE; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + FRIENDPERPAGE ; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("friendlistns.getTotalFriend", owner);
		System.out.println("startRow: " + startRow + ", endRow: " + endRow + ", total: " + total);
		if (endRow > total)
			endRow = total;
		Map<String, String> map = new HashMap<String, String>();
		map.put("owner", owner);
		map.put("userid", userid);
		return sst.selectList("friendlistns.friendList", map, new RowBounds(startRow, FRIENDPERPAGE));
	}
	
	public List<Member> pendingListLoad(String member_id) {
		return sst.selectList("friendlistns.pendingList", member_id);
	}

	@Override
	public List<Member> rquestList(String member_id) {
		return sst.selectList("friendlistns.requestList", member_id);
	}

	@Override
	public int update(Friendlist Friendlist) {
		return sst.update("friendlistns.update", Friendlist);
	}

	@Override
	public Friendlist getFriendList(String uid1, String uid2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid1", uid1);
		map.put("uid2", uid2);
		return sst.selectOne("friendlistns.getFriendList", map);
	}

	@Override
	public int insertRequest(Friendlist f) {
		return sst.insert("friendlistns.insert", f);
	}

	@Override
	public int insertUserSwitchedRequest(Friendlist f) {
		return sst.insert("friendlistns.insertUserSwitchedRequest", f);
	}
}
