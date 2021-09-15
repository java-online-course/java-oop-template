package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
	private Author[] authors = new Author[0];

	@Override
	public boolean save(Author author) {
		int numOfAuthors = this.authors.length;
		if (numOfAuthors > 0) {
			if (findByFullNameGetIndex(author.getName(), author.getLastName()) >= 0) {
				return false;
			}
		}
		Author[] updateAuthors = new Author[numOfAuthors + 1];
		System.arraycopy(this.authors, 0, updateAuthors, 0, numOfAuthors);
		updateAuthors[numOfAuthors] = author;
		this.authors = updateAuthors;
		return true;
	}

	@Override
	public Author findByFullName(String name, String lastname) {
		int i = findByFullNameGetIndex(name, lastname);
		if (i >= 0) {
			return this.authors[i];
		}
		return null;
	}

	private int findByFullNameGetIndex(String name, String lastname) {
		for (int i = 0; i < this.authors.length; i++) {
			if (this.authors[i].getName().equals(name) && this.authors[i].getLastName().equals(lastname)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean remove(Author author) {
		int numOfAuthors = this.authors.length;
		if (numOfAuthors > 0) {
			int j0 = findByFullNameGetIndex(author.getName(), author.getLastName());
			if (j0 >= 0) {
				Author[] updateAuthors = new Author[numOfAuthors - 1];
				for (int i = 0, j = 0; i < numOfAuthors; i++) {
					if (i != j0) {
						updateAuthors[j] = this.authors[i];
						j++;
					}
				}
				this.authors = updateAuthors;
				return true;
			}
		}
		return false;
	}

	@Override
	public int count() {
		return this.authors.length;
	}
}
