package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.HashTagDao;
import only.model.HashTag;

@Service
public class HashTagServiceImpl implements HashTagService {
	@Autowired
	private HashTagDao hashD;

	@Override
	public List<HashTag> getHashTag(String hName, int hid) {
		return null;
	}

	@Override
	public HashTag getTag(int hid) {
		return null;
	}

	@Override
	public int insertHashTag(HashTag tag) {
		return hashD.insertHashTag(tag);
	}

	@Override
	public List<HashTag> getTopNHashTags(int n) {
		return hashD.getTopNHashTags(n);
	}
}
