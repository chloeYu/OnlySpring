package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.PostDao;
import only.model.Post;
import only.model.Post_Files;

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
}
