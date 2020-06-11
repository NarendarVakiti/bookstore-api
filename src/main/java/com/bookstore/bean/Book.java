package com.bookstore.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookstore")
public class Book {
	
	@Id
	@GeneratedValue
	private int bookId;
	private String bookName;
	private double bookPrice;
	private boolean availability;
	private String level;
	private String rating;
	private LocalDate publishedDate;
	
	@ManyToOne
	@JoinColumn(name = "author_ID")
	private Author author;

}
