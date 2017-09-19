package only.dao;

import java.util.List;

import only.model.Post;

public interface PostDao {

	List<Post> getTimelinePost(String userid, String pageNum);

	Post getPost(String pid);

	int insertPost(Post post);

	int nextPid();
}
