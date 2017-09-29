package only.model;

import java.sql.Date;

public class Comments {
	private int ref_id;
	private String userid; 
	private int cid; 
	private String text;
	private Date created;
	private Date modified; 
	private String del;
	

	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
}
