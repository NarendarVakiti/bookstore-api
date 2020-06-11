package com.bookstore.bean;


import java.util.Set;

import javax.persistence.*;


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
	@GeneratedValue
	private int authorId;
	private String authorName;
	private String authorEmail;
	/*@OneToMany(mappedBy="author", targetEntity = Book.class, cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<Book> books;*/

	@OneToOne
	@JoinColumn(name = "address_ID")
	private Address address;

}
