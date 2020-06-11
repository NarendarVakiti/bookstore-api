package com.bookstore.bean;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450252897638356990L;
	
	private int bookId;
	private String bookName;
	private double bookPrice;
	private boolean availability;
	private String level;
	private String rating;
	private LocalDate publishedDate;
	private String authorName;

}
