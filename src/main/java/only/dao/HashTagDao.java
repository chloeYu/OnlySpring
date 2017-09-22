package only.dao;

import java.util.List;

import only.model.HashTag;

public interface HashTagDao {
	List<HashTag> getHashTag(String hName, int hid);
	
	HashTag getTag(int hid);
	
	int insertHashTag(HashTag tag);
}
