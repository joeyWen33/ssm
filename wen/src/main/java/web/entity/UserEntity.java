package web.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer u_id;
	private String u_name;
	private String u_passwd;
	private String u_gender;
	private String u_email;
	private Integer u_age;
	private String u_contact;
	private String u_address;
	public UserEntity() {
		super();
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_passwd() {
		return u_passwd;
	}
	public void setU_passwd(String u_passwd) {
		this.u_passwd = u_passwd;
	}
	public String getU_gender() {
		return u_gender;
	}
	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public String getU_contact() {
		return u_contact;
	}
	public void setU_contact(String u_contact) {
		this.u_contact = u_contact;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		this.u_address = u_address;
	}
	
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	
	public Integer getU_age() {
		return u_age;
	}
	public void setU_age(Integer u_age) {
		this.u_age = u_age;
	}
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_name=" + u_name + ", u_passwd=" + u_passwd + ", u_gender=" + u_gender
				+ ", u_email=" + u_email + ", u_age=" + u_age + ", u_contact=" + u_contact + ", u_address=" + u_address
				+ "]";
	}
	
	
	
}
