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
/*
	@Override
	public Member getMemberByIdAndPw(String member_id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", member_id);
		map.put("password", password);
		return sst.selectOne("memberns.select", map);
	}*/

	@Override
	public List<Member> searchMember(String searchTerm) {
		return sst.selectList("memberns.search", searchTerm);
	}

}
