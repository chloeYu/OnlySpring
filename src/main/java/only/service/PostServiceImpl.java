package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.PostDao;
import only.model.Post;

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
}
