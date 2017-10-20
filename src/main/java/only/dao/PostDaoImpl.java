package only.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import only.model.Post;
import only.model.Post_Files;
import only.model.Post_Location;
import only.utils.PostType;

@Repository
public class PostDaoImpl implements PostDao {
	final int POSTPERPAGE = 5;
	final int IMAGEPERPAGE = 12;

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Post> getTimelinePost(String userid, String pageNum) {
		int startRow = (Integer.parseInt(pageNum) - 1) * POSTPERPAGE; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + POSTPERPAGE ; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("postns.getTimelineTotal", userid);
		System.out.println("startRow: " + startRow + ", endRow: " + endRow + ", total: " + total);
		if (endRow > total)
			endRow = total;
		List<Post> plist = sst.selectList("postns.timelinelist", userid, new RowBounds(startRow, POSTPERPAGE));
		System.out.println("Post Size: " + plist.size());
		for(Post post : plist) {
			System.out.println(post.getPid());
			char[] type = post.getType().toCharArray();
			for(int i=0; i< type.length; i++) {
				if(type[i]=='y') {
					if(i==PostType.TEXT.ordinal()) post.setText((String) sst.selectOne("postns.getPostText", post.getPid()));
					if(i==PostType.PHOTO_VIDEO.ordinal()) {
						List<MultipartFile> files = sst.selectList("postns.getFilePath", post.getPid());
						post.setFiles(files);
					}
					if(i==PostType.LOCATION.ordinal()) {
						System.out.println("get location : " + post.getPid());
						Post_Location location = sst.selectOne("postns.getLocation", post.getPid());
						post.setPlace(location.getPlace());
						post.setLat(location.getLat());
						post.setLng(location.getLng());
					}
					if(i==PostType.TAG_FRIENDS.ordinal()) {
						System.out.println("get tagged friends : + " + post.getPid());
						List<String> taggedFriendList = sst.selectList("postns.getTaggedFriends", post.getPid());
						post.setTaggedFriend(taggedFriendList);
					}
				}
			}
		}
		return plist;
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
					System.out.println(pid);
					List<MultipartFile> filePaths = post.getFiles();
					String[] postFilePaths = new String[filePaths.size()];
					Iterator<MultipartFile> it = filePaths.iterator();
					int seq=0;
					while(it.hasNext()) {
						postFilePaths[seq++] = it.next().getOriginalFilename();
					}
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

	@Override
	public int insertText(int pid, String text) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", Integer.toString(pid));
		map.put("text", text);
		return sst.insert("postns.insertText", map);
	}

	@Override
	public int insertImage(int pid, Post_Files postFiles) {
		return sst.insert("postns.insertFiles", postFiles);
	}

	@Override
	public int getLikesCount(int lid) {
		return sst.selectOne("postns.getLikesCount", lid);
	}

	@Override
	public int insertLocation(int pid, Post_Location location) {
		return sst.insert("postns.insertLocation", location);
	}

	@Override
	public int insertMemberTag(int pid, String member) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", Integer.toString(pid));
		map.put("member", member);
		return sst.insert("postns.insertMemberTag", map);
	}

	@Override
	public List<Post> getBlogPost(String ownerid, String pageNum) {
		int startRow = (Integer.parseInt(pageNum) - 1) * POSTPERPAGE; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + POSTPERPAGE ; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("postns.getBlogTotal", ownerid);
		System.out.println("startRow: " + startRow + ", endRow: " + endRow + ", total: " + total);
		if (endRow > total)
			endRow = total;
		List<Post> plist = sst.selectList("postns.bloglist", ownerid, new RowBounds(startRow, POSTPERPAGE));
		System.out.println("Post Size: " + plist.size());
		for(Post post : plist) {
			System.out.println(post.getPid());
			char[] type = post.getType().toCharArray();
			for(int i=0; i< type.length; i++) {
				if(type[i]=='y') {
					if(i==PostType.TEXT.ordinal()) post.setText((String) sst.selectOne("postns.getPostText", post.getPid()));
					if(i==PostType.PHOTO_VIDEO.ordinal()) {
						List<MultipartFile> files = sst.selectList("postns.getFilePath", post.getPid());
						post.setFiles(files);
					}
					if(i==PostType.LOCATION.ordinal()) {
						System.out.println("get location : " + post.getPid());
						Post_Location location = sst.selectOne("postns.getLocation", post.getPid());
						post.setPlace(location.getPlace());
						post.setLat(location.getLat());
						post.setLng(location.getLng());
					}
					if(i==PostType.TAG_FRIENDS.ordinal()) {
						System.out.println("get tagged friends : + " + post.getPid());
						List<String> taggedFriendList = sst.selectList("postns.getTaggedFriends", post.getPid());
						post.setTaggedFriend(taggedFriendList);
					}
				}
			}
		}
		return plist;
	}

	@Override
	public List<String> getImagesByUserid(String userid, int pageNum) {
		int startRow = (pageNum- 1) * IMAGEPERPAGE; // 1페이지:1 2페이지: 11 3페이지:21 ...
		int endRow = startRow + IMAGEPERPAGE ; // 1페이지: 10 2페이지: 20 3페이지: 30
		int total = sst.selectOne("postns.getImageTotal", userid);
		if (endRow > total)
			endRow = total;
		List<String> imageList = sst.selectList("postns.getImagesByUserid", userid, new RowBounds(startRow, IMAGEPERPAGE));
		
		return imageList;
	}

	@Override
	public int getImageTotal(String userid) {
		return sst.selectOne("postns.getImageTotal", userid);
	}

	@Override
	public int insertHashTag(int pid, String hashTag) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", Integer.toString(pid));
		map.put("member", hashTag);
		return sst.insert("postns.insertHashTag", map);
	}
}
