package only.dao;

import java.util.List;

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
	public List<Member> friendListLoad(String member_id) {
		return sst.selectList("friendlistns.friendList", member_id);
	}

	@Override
	public List<Member> friendListLoad(String ownerid, int pageNum) {
		int startRow = (pageNum - 1) * FRIENDPERPAGE; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + FRIENDPERPAGE ; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("friendlistns.getTotalFriend", ownerid);
		System.out.println("startRow: " + startRow + ", endRow: " + endRow + ", total: " + total);
		if (endRow > total)
			endRow = total;
		
		return sst.selectList("friendlistns.friendList", ownerid, new RowBounds(startRow, FRIENDPERPAGE));
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
}
