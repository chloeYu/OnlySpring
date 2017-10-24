package only.model;

import java.sql.Date;

public class Page {
	private int pid;
	private String userid;
	private int category;
	private String pname;
	private String ptext;
	private String logo_image;
	private String cover_image;
	private Date created;
	private Date modified;
	private int del;
	
	@Override
	public String toString() {
		return "Page [pid=" + pid + ", userid=" + userid + ", category=" + category + ", pname=" + pname + ", ptext="
				+ ptext + ", logo_image=" + logo_image + ", cover_image=" + cover_image + ", created=" + created
				+ ", modified=" + modified + ", del=" + del + "]";
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	public String getLogo_image() {
		return logo_image;
	}
	public void setLogo_image(String logo_image) {
		this.logo_image = logo_image;
	}
	public String getCover_image() {
		return cover_image;
	}
	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
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
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	
}
