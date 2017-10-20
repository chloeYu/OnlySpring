package only.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.PageDao;
import only.model.Page;

@Service
public class PageServiceImpl implements PageService{
	@Autowired
	private PageDao pd;

	@Override
	public Page getPageById(String userid) {
		return pd.getPageById(userid);
	}

	@Override
	public int insert(Page page) {
		return pd.insert(page);
	}

	@Override
	public int count(Page page) {
		return pd.count(page);
	}

	@Override
	public int pcount(Page page) {
		return pd.pcount(page);
	}

	@Override
	public int selet_pid(Page page) {
		return pd.selet_pid(page);
	}

}
