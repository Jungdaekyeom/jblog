package com.douzone.jblog.vo;

public class CategoryVo {

	private Long no;
	private Long count;
	private String name;
	private String description;
	private String blogId;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", count=" + count + ", name=" + name + ", description=" + description
				+ ", blogId=" + blogId + "]";
	}

}
