package only.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Post;
import only.utils.PostType;

@Repository
public class PostDaoImpl implements PostDao {
	final int POSTPERPAGE = 10;

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Post> getTimelinePost(String userid, String pageNum) {
		int startRow = (Integer.parseInt(pageNum) - 1) * POSTPERPAGE + 1; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + POSTPERPAGE - 1; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("postns.getTimelineTotal", userid);
		System.out.println("startRow: " + startRow + ", endRow: " + endRow + ", total: " + total);
		if (endRow > total)
			endRow = total;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("startRow", Integer.toString(startRow));
		map.put("endRow", Integer.toString(endRow));
		return sst.selectList("postns.timelinelist", map);
	}

	@Override
	public Post getPost(String pid) {
		Post post = sst.selectOne("postns.getPost", pid);
/*		System.out.println(pid + "'s Post Type: " + post.getType());*/
		char[] typeCheck = post.getType().toCharArray();
		for (int i = 0; i < typeCheck.length; i++) {
			if (typeCheck[i] == 'y') {
				if (i == PostType.TEXT.ordinal()) {
					String text = sst.selectOne("postns.getPostText", pid);
					post.setText(text);
				} 
				if (i== PostType.PHOTO_VIDEO.ordinal()) {
					System.out.println("Photo Video Post");
				}
			}
		}
		return post;
	}

	@Override
	public int insertPost(Post post) {
		return sst.insert("postns.insert", post);
	}

	@Override
	public int nextPid() {
		return sst.selectOne("postns.nextPid");
	}
}
