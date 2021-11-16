package com.douzone.mysite.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserVo {
	@NotEmpty
	@Email
	private String id;

	// validation
	// 바인딩 될 때의 조건들
	@NotEmpty
	@Length(min = 2, max = 8)
	private String name;

	@NotEmpty
	@Length(min = 4, max = 16)
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