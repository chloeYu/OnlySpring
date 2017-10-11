package only.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Friendlist;
@Repository
public class FriendListDaoImpl implements FriendListDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Friendlist> FriendListLoad(String member_id) {
		return sst.selectList("friendlistns.select", member_id);
	}

	@Override
	public int update(Friendlist Friendlist) {
		return sst.update("friendlistns.update", Friendlist);
	}

}
