package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
	private SchoolBook[] schoolBooks = new SchoolBook[0];
	private int numOfBooks = 0;

	@Override
	public boolean save(SchoolBook book) {
		SchoolBook[] updateBooks = new SchoolBook[this.numOfBooks + 1];
		System.arraycopy(this.schoolBooks, 0, updateBooks, 0, this.numOfBooks);
		updateBooks[this.numOfBooks] = book;
		this.numOfBooks = this.numOfBooks + 1;
		this.schoolBooks = updateBooks;
		return true;
	}

	@Override
	public SchoolBook[] findByName(String name) {
		int[] indexes = findByNameGetIndexes(name);
		int indexesLen = indexes.length;
		if (indexesLen > 0) {
			SchoolBook[] arrayOfBooks = new SchoolBook[indexesLen];
			for (int i = 0; i < indexesLen; i++) {
				arrayOfBooks[i] = this.schoolBooks[indexes[i]];
			}
			return arrayOfBooks;
		}
		return new SchoolBook[0];
	}

	private int[] findByNameGetIndexes(String name) {
		int[] indexes = new int[this.numOfBooks];
		int j = 0;
		for (int i = 0; i < this.numOfBooks; i++) {
			if (this.schoolBooks[i].getName().equals(name)) {
				indexes[j] = i;
				j++;
			}
		}
		if (j > 0) {
			int[] indexes2 = new int[j];
			System.arraycopy(indexes, 0, indexes2, 0, j);
			return indexes2;
		}
		return new int[0];
	}

	@Override
	public boolean removeByName(String name) {
		if (this.numOfBooks > 0) {
			int[] indexes = findByNameGetIndexes(name);
			int indexesLen = indexes.length;
			if (indexesLen > 0) {
				SchoolBook[] arrayOfBooks = new SchoolBook[this.numOfBooks - indexesLen];
				boolean flag;
				for (int i = 0, j = 0, k = 0; i < this.numOfBooks; i++) {
					flag = true;
					if (k < indexesLen)
						if (i == indexes[k]) {
							k++;
							flag = false;
						}
					if (flag) {
						arrayOfBooks[j] = this.schoolBooks[i];
						j++;
					}
				}
				this.numOfBooks = this.numOfBooks - indexesLen;
				this.schoolBooks = arrayOfBooks;
				return true;
			}
		}
		return false;
	}

	@Override
	public int count() {
		return this.numOfBooks;
	}
}
