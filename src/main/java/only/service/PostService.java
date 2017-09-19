package only.service;

import java.util.List;

import only.model.Post;

public interface PostService {

	List<Post> getTimelinePost(String userid, String pageNum);

	Post getPost(String pid);

	int insertPost(Post post);

	int nextPid();

}
