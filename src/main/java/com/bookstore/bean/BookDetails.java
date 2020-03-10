package com.bookstore.bean;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450252897638356990L;
	
	private int bookId;
	private String bookName;
	private double bookPrice;
	private boolean active;
	private int authorId;
	private String authorName;
	private String address;

}
