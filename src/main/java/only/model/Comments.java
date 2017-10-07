package only.model;

import java.sql.Date;

public class Comments {
	private int cid; 
	private int ref_id;
	private int re_level;
	private String userid;
	private String text;
	private int likes;
	private boolean isLiked;
	private Date created;
	private Date modified; 
	private String del;
	private int commentCount;
	
	
	
	@Override
	public String toString() {
		return "Comments [cid=" + cid + ", ref_id=" + ref_id + ", re_level=" + re_level + ", userid=" + userid
				+ ", text=" + text + ", likes=" + likes + ", created=" + created + ", modified=" + modified + ", del="
				+ del + ", commentCount=" + commentCount + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
}
