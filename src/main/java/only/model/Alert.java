package only.model;

import java.sql.Date;

public class Alert {
	private int aid;	//  `aid` int(11) NOT NULL AUTO_INCREMENT,
	private int type;	//  `type` int(10) NOT NULL,
	private String status; 	//  `status` char(1) NOT NULL,
	private String uid1;	//  `uid1` varchar(20) NOT NULL,
	private String uid2;	//  `uid2` varchar(20) NOT NULL,
	private String url;		//  `url` varchar(50) NOT NULL,
	private Date created; 	//  `created` date NOT NULL,
	
	private String userid;
	private String username;
	private String profile_image;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
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
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}	
	
	
}
