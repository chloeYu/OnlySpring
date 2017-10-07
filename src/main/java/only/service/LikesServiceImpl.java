package only.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.LikesDao;
import only.model.Likes;

@Service
public class LikesServiceImpl implements LikesService {
	@Autowired
	private LikesDao ld;

	@Override
	public int toggleLikes(Likes l) {
		return ld.toggleLikes(l);
	}

	@Override
	public int isLiked(Likes l) {
		return ld.isLiked(l);
	}
}
