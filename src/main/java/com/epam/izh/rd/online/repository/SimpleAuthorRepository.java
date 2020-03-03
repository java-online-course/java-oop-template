package com.epam.izh.rd.online.repository;


import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    public SimpleAuthorRepository() {
    }

    @Override
    public boolean save(Author author) {
        if (this.authors.length == 0) {
            this.authors = new Author[]{author};
            return true;
        }

        if (this.findByFullName(author) != null) {
            return false;
        }

        Author[] result = new Author[this.authors.length + 1];
        System.arraycopy(this.authors, 0, result, 0, this.authors.length);
        result[this.authors.length] = author;
        this.authors = result;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        for (Author author : this.authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    public Author findByFullName(Author author) {
        return this.findByFullName(author.getName(), author.getLastName());
    }

    @Override
    public boolean remove(Author author) {

        if (this.findByFullName(author) == null) {
            return false;
        }

        // We know that result arrays will be less only by 1 element.
        Author[] result = new Author[this.authors.length - 1];

        for (int j = 0, i = 0; i < this.authors.length; i++) {
            if (!(this.authors[i].getLastName() == author.getLastName() && author.getName() == author.getName())) {
                result[j++] = this.authors[i];
            }
        }

        this.authors = result;
        return true;
    }

    @Override
    public int count() {
        return this.authors.length;
    }
}