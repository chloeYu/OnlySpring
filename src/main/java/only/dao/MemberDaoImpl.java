package only.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public Member getMemberById(String member_id) {
		return sst.selectOne("memberns.select", member_id);
	}

	@Override
	public List<Member> searchMember(String searchTerm, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("searchTerm", searchTerm);
		return sst.selectList("memberns.search", map);
	}

	@Override
	public int insert(Member member) {
		return sst.insert("memberns.insert", member);
	}

	@Override
	public int update(Member member) {
		return sst.update("memberns.update", member);
	}

	@Override
	public int updateThumbProfile(String userid, String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("url", url);
		return sst.update("memberns.updateThumbProfile", map);
		
				
	}

	@Override
	public int updateCoverProfile(String userid, String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("url", url);
		return sst.update("memberns.updateCoverProfile", map);
	}
}
