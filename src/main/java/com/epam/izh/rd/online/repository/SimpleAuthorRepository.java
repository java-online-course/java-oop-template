package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null){
            Author[] authors = new Author[this.count() + 1];
            for (int i = 0; i < this.count() ; i++) {
                authors[i] = this.authors[i];
            }
            authors[this.count()] = author;
            this.authors = authors;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author:authors
        ) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null){
            Author[] authors = new Author[this.count() - 1];
            int j = 0;
            for (int i = 0; i < this.count()-1; i++) {
                if( !(author.getName().equals(this.authors[i].getName()) && author.getLastName().equals(this.authors[i].getLastName())) ) {
                    authors[j++] = this.authors[i];
                }
            }
            this.authors = authors;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
