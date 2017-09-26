package only.dao;

import java.util.List;

import only.model.Post;
import only.model.Post_Files;

public interface PostDao {

	List<Post> getTimelinePost(String userid, String pageNum);

	Post getPost(String pid);

	int insertPost(Post post);

	int nextPid();

	int insertText(int pid, String text);

	int insertImage(int pid, Post_Files postImage);
}
