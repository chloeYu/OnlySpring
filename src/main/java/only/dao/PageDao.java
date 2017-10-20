package only.dao;

import only.model.Page;

public interface PageDao {
	Page getPageById(String userid);
	
	int insert(Page page);
	
	int count(Page page);
	
	int pcount(Page page);
	
	int selet_pid(Page page);
}
