package com.bookstoreapi.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 9845573287961L;

	private Long id;

	@NotNull(message = "Title cannot be null")
	@Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
	private String title;

	@NotNull(message = "Author cannot be null")
	@Size(min = 1, max = 100, message = "Author must be between 1 and 100 characters")
	private String author;

	@NotNull(message = "Price cannot be null")
	@Min(value = 0, message = "Price must be a positive number")
	private Double price;

	@NotNull(message = "ISBN cannot be null")
	@Size(min = 1, max = 13, message = "ISBN must be between 1 and 13 characters")
	private String isbn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
