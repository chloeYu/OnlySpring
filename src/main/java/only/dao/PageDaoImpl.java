package only.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Page;

@Repository
public class PageDaoImpl implements PageDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public Page getPageById(String userid) {
		return sst.selectOne("pagens.select", userid);
	}

	@Override
	public int insert(Page page) {
		return sst.insert("pagens.insert", page);
	}

	@Override
	public int count(Page page) {
		return sst.selectOne("pagens.count", page);
	}

	@Override
	public int pcount(Page page) {
		return sst.selectOne("pagens.pcount", page);
	}

	@Override
	public int selet_pid(Page page) {
		return sst.selectOne("pagens.selet_pid", page);
	}

}
