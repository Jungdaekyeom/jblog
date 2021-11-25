package com.douzone.jblog.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	@NotEmpty
	@Email
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+$")
	private String id;

	@NotEmpty
	@Length(min = 2, max = 5)
	private String name;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$")
	private String password;
	private String joinDate;
	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", joinDate=" + joinDate + ", role="
				+ role + "]";
	}

}