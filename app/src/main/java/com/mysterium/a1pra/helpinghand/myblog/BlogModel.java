package com.mysterium.a1pra.helpinghand.myblog;

public class BlogModel {
	private String blogTitle;
	private String blogImageURL;
	private String key;

	public BlogModel(String key, String blogTitle, String blogImageURL) {
		this.blogTitle = blogTitle;
		this.key = key;
		this.blogImageURL = blogImageURL;
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