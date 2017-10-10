package only.service;

import java.util.List;

import only.model.Post;
import only.model.Post_Files;
import only.model.Post_Location;

public interface PostService {

	List<Post> getTimelinePost(String userid, String pageNum);

	Post getPost(String pid);

	int insertPost(Post post);

	int nextPid();

	int insertText(int pid, String text);

	int insertImage(int pid, Post_Files postImage);

	int getLikesCount(int lid);

	int insertLocation(int pid, Post_Location location);

	int insertMemberTag(int pid, String member);

	List<Post> getBlogPost(String ownerid, String pageNum);

	List<String> getImagesByUserid(String userid, int pageNum);

	int getImageTotal(String userid);

}
