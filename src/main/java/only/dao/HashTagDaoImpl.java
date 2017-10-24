package only.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.HashTag;

@Repository
public class HashTagDaoImpl implements HashTagDao {

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<HashTag> getHashTag(String hName, int hid) {

		return null;
	}

	@Override
	public HashTag getTag(int hid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertHashTag(HashTag tag) {
		return sst.insert("tagns.insertHashTag", tag);
	}

	@Override
	public List<HashTag> getTopNHashTags(int n) {
		return sst.selectList("tagns.selectpopulartag", new RowBounds(0, n));
	}
}
