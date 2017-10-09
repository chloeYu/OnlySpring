package only.model;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Post {
	private int pid;
	private Date created;
	private String del; 
	private String userid;
	private String username;
	private Date modified; 
	private String type;
	// Text
	private String text;
	// Audio & Video
	private List<MultipartFile> files;
	// Comments
	private List<Comments> comments;
	private int commentCount;
	// Like
	private int likes;
	private boolean isLiked;
	// Map Location
	private String place;
	private float lat;
	private float lng;
	// Tagged Friends
	private List<String> taggedFriend;
	
	
	@Override
	public String toString() {
		return "Post [pid=" + pid + ", created=" + created + ", del=" + del + ", userid=" + userid + ", username="
				+ username + ", modified=" + modified + ", type=" + type + ", text=" + text + ", files=" + files + "]";
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile>  files) {
		this.files = files;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public List<String> getTaggedFriend() {
		return taggedFriend;
	}
	public void setTaggedFriend(List<String> taggedFriend) {
		this.taggedFriend = taggedFriend;
	}	
}
