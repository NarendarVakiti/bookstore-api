package com.bookstore.bean;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString 
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;
	private String authorName;
	private String address;
	
	@OneToMany(mappedBy="author", targetEntity = BookStore.class, cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<BookStore> books;

}
