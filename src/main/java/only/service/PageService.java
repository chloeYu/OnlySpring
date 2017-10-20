package only.service;

import only.model.Page;

public interface PageService {
	Page getPageById(String userid);
	int insert(Page page);
	int count(Page page);
	int pcount(Page page);
	int selet_pid(Page page);
}
