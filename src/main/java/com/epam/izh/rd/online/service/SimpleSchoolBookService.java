package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<Book> {
	private BookRepository<SchoolBook> schoolBookBookRepository;
	private AuthorService authorService;

	public SimpleSchoolBookService() {
	}

	public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
		this.schoolBookBookRepository = schoolBookBookRepository;
		this.authorService = authorService;
	}

	@Override
	public boolean save(Book book) {
		SchoolBook bookLikeSB = ((SchoolBook) book);
		if (this.authorService.findByFullName(bookLikeSB.getAuthorName(), bookLikeSB.getAuthorLastName()) != null) {
			this.schoolBookBookRepository.save(bookLikeSB);
			return true;
		}
		return false;
	}

	@Override
	public Book[] findByName(String name) {
		return this.schoolBookBookRepository.findByName(name);
	}

	@Override
	public int getNumberOfBooksByName(String name) {
		return this.schoolBookBookRepository.findByName(name).length;
	}

	@Override
	public boolean removeByName(String name) {
		return this.schoolBookBookRepository.removeByName(name);
	}

	@Override
	public int count() {
		return this.schoolBookBookRepository.count();
	}

	@Override
	public Author findAuthorByBookName(String name) {
		SchoolBook[] books = this.schoolBookBookRepository.findByName(name);
		if (books.length > 0)
			return this.authorService.findByFullName(books[0].getAuthorName(), books[0].getAuthorLastName());
		return null;
	}

}
