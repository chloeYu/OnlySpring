package only.dao;

import only.model.Likes;

public interface LikesDao {

	int toggleLikes(Likes l);

	int isLiked(Likes l);

}
