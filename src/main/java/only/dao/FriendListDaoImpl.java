package only.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Friendlist;
import only.model.Member;
@Repository
public class FriendListDaoImpl implements FriendListDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Member> friendListLoad(String member_id) {
		return sst.selectList("friendlistns.friendList", member_id);
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
