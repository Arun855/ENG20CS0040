package com.mysterium.a1pra.helpinghand.myblog;

public class BlogModel {
	private String blogUser;
	private String blogDate;
	private String blogDesc;
	private String blogTitle;
	private String blogImageURL;
	private int blogImageIsFav;
	private String key;

	public BlogModel(String key, String blogUser, String blogDate, String blogTitle, String blogImageURL, int blogImageIsFav, String blogDesc) {
		this.blogDate=blogDate;
		this.blogUser=blogUser;
		this.blogDesc=blogDesc;
		this.blogTitle = blogTitle;
		this.key = key;
		this.blogImageIsFav = blogImageIsFav;
		this.blogImageURL = blogImageURL;
	}

	public String getBlogDate() {
		return blogDate;
	}

	public String getBlogDesc() {
		return blogDesc;
	}

	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}

	public String getBlogUser() {
		return blogUser;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public void setBlogUser(String blogUser) {
		this.blogUser = blogUser;
	}

	public int getBlogImageIsFav() {
		return blogImageIsFav;
	}

	public void setBlogImageIsFav(int blogImageIsFav) {
		this.blogImageIsFav = blogImageIsFav;
	}

	public String getBlogImageURL() {
		return blogImageURL;
	}

	public void setBlogImageURL(String blogImageURL) {
		this.blogImageURL = blogImageURL;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}