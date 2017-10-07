package only.service;

import only.model.Likes;

public interface LikesService {

	int toggleLikes(Likes l);

	int isLiked(Likes l);

}
