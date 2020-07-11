package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooksAfterSave = new SchoolBook[schoolBooks.length + 1];
        for (int i = 0; i <= schoolBooks.length; i++) {
            if (i == schoolBooks.length) {
                schoolBooksAfterSave[i] = book;
            } else {
                schoolBooksAfterSave[i] = schoolBooks[i];
            }
        }
        schoolBooks = schoolBooksAfterSave;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] result = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                if (result.length == 0) {
                    result = new SchoolBook[result.length + 1];
                    result[0] = schoolBook;
                } else {
                    SchoolBook[] tmpResult = new SchoolBook[result.length + 1];
                    System.arraycopy(result, 0, tmpResult, 0, result.length);
                    tmpResult[tmpResult.length - 1] = schoolBook;
                    result = tmpResult;
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        boolean result = false;
        for (int i = 0; i < schoolBooks.length;) {
            if (schoolBooks[i].getName().equals(name)) {
                SchoolBook[] schoolBooksAfterRemove = new SchoolBook[schoolBooks.length-1];
                if (i == 0) {
                    System.arraycopy(schoolBooks, 1, schoolBooksAfterRemove, 0, schoolBooksAfterRemove.length);
                    schoolBooks = schoolBooksAfterRemove;
                }
                else if (i == schoolBooks.length-1) {
                    System.arraycopy(schoolBooks, 0, schoolBooksAfterRemove, 0, schoolBooks.length - 1);
                    schoolBooks = schoolBooksAfterRemove;
                }
                else {
                    for (int y = 0; y < schoolBooks.length; y++) {
                        if (y > i) {
                            schoolBooksAfterRemove[y-1] = schoolBooks[y];
                        }
                        if (y == i) {
                            continue;
                        }
                        if (y < i) {
                            schoolBooksAfterRemove[y] = schoolBooks[y];
                        }
                    }
                    schoolBooks = schoolBooksAfterRemove;
                }
                result = true;
            } else {
                i++;
            }
        }
        return result;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
