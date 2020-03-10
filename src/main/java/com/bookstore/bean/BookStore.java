package com.bookstore.bean;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookStore {
	
	private int bookId;
	private String bookName;
	private double bookPrice;
	private boolean active;
	private Author author;

}
