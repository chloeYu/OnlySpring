package only.service;

import java.util.List;

import only.model.HashTag;

public interface HashTagService {

	List<HashTag> getHashTag(String hName, int hid);
	
	HashTag getTag(int hid);
	
	int insertHashTag(HashTag tag);

	List<HashTag> getTopNHashTags(int i);
}
