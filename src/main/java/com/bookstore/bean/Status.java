package com.bookstore.bean;

import java.io.Serializable;

public class Status implements Serializable {
	private static final long serialVersionUID = -1450252897638356990L;
	
	private String message;
	private boolean flag;
	
	public Status() {
		
	}
	
	public Status(String message, boolean flag) {
		 this.message = message;
		 this.flag = flag;
	}

}
