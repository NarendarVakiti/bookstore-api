package com.bookstore.bean;

public class Author {
	
	
	private int authorId;
	private String authorName;
	private String address;
	
	public Author() {
		
	}

	public Author(int authorId, String authorName, String address) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.address = address;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
