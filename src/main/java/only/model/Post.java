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
	private String text;
	private List<MultipartFile> files;
	
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
	
}
