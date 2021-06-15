package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;


public class SimpleSchoolBookRepository implements BookRepository <SchoolBook> {

    SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        int saveCount = count() ;
        SchoolBook [] saveBook = new SchoolBook[saveCount + 1] ;
        System.arraycopy(schoolBooks, 0 ,saveBook, 0 ,saveCount);
        saveBook[saveCount] = book ;
        schoolBooks = saveBook ;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[]{};
        int bookNum = 0 ;
        for (SchoolBook book: schoolBooks ) {
            if(book.getName().equals(name)){
                SchoolBook[]tempBook = new SchoolBook[bookNum +1] ;
                System.arraycopy(books, 0, tempBook, 0 ,bookNum);
                tempBook[bookNum] = book ;
                books = tempBook ;
                bookNum++ ;
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        int removeCount = 0 ;
        int num = count();
        for (SchoolBook book : schoolBooks) {
            if( book.getName().equals(name)){
                removeCount++ ;
                SchoolBook[] removeBook = new SchoolBook[num-removeCount] ;
                //System.arraycopy(schoolBooks,0,removeBook,0,removeBook.length);
                schoolBooks = removeBook ;
            }


        }
        return removeCount!=0;//return removeCount!=cont() тоже отработало, почему?
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}