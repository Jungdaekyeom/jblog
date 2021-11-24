package com.douzone.jblog.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	@NotEmpty
	@Email
	private String id;

	@NotEmpty(message="해당 항목을 채워주세요.")
	@Length(min = 2, max = 5)
	private String name;

	@NotEmpty(message="해당 항목을 채워주세요.")
	@Length(min = 4, max = 16)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,}$")
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