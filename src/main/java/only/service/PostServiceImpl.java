package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.PostDao;
import only.model.Post;
import only.model.Post_Files;
import only.model.Post_Location;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDao pd;

	@Override
	public List<Post> getTimelinePost(String userid, String pageNum) {
		return pd.getTimelinePost(userid, pageNum);
	}

	@Override
	public Post getPost(String pid) {
		return pd.getPost(pid);
	}

	@Override
	public int insertPost(Post post) {
		return pd.insertPost(post);
	}

	@Override
	public int nextPid() {
		return pd.nextPid();
	}

	@Override
	public int insertText(int pid, String text) {
		return pd.insertText(pid, text);
	}

	@Override
	public int insertImage(int pid, Post_Files postImage) {
		return pd.insertImage(pid, postImage);
	}

	@Override
	public int getLikesCount(int lid) {
		return pd.getLikesCount(lid);
	}

	@Override
	public int insertLocation(int pid, Post_Location location) {
		return pd.insertLocation(pid, location);
	}

	@Override
	public int insertMemberTag(int pid, String member) {
		return pd.insertMemberTag(pid, member);
	}

	@Override
	public List<Post> getBlogPost(String ownerid, String pageNum) {
		return pd.getBlogPost(ownerid, pageNum);
	}
}
