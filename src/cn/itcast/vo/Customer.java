package cn.itcast.vo;

/**
 * @author Administrator
 */
public class Customer {
	
	private String id;
	private String username;
	private String gender;
	private String birthday;
	private String cellphone;
	private String email;
	private String love;
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", gender="
				+ gender + ", birthday=" + birthday + ", cellphone="
				+ cellphone + ", email=" + email + ", love=" + love + ", type="
				+ type + "]";
	}
	
}
