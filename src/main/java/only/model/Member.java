package only.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String userid;
	private String username;
	private String password;
	private String email;
	private String tel;
	private Date birth;
	private String profile_image;
	private int status;
	private Date created;
	private Date modified;
	private String uid1;
	private String uid2;
	private int relation;
	
	
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", username=" + username + ", uid1=" + uid1 + ", uid2=" + uid2
				+ ", relation=" + relation + "]";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getUid1() {
		return uid1;
	}

	public void setUid1(String uid1) {
		this.uid1 = uid1;
	}

	public String getUid2() {
		return uid2;
	}

	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}

	public int getRelation() {
		return relation;
	}
	public void setRelation(int relation) {
		this.relation = relation;
	}

}
