package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors =new Author[0];
    @Override
    public boolean save(Author author){
        boolean result;
        if(author==this.findByFullName(author.getName(),author.getLastName())){
            result = false;
        }else{
            Author[] fieldForCopy = new Author[authors.length+1];
            System.arraycopy(authors,0,fieldForCopy,0,authors.length);
            authors= new Author[fieldForCopy.length];
            System.arraycopy(fieldForCopy,0,authors,0,authors.length);

            authors[authors.length-1] = author;
            result= true;
        }
            return result;
    }
    @Override
    public Author findByFullName(String name, String lastname){

        Author result=null;
        for(int i = 0;i<authors.length;i++){
           if ((authors[i].getName()==name) && (authors[i].getLastName()==lastname)){
                result = authors[i];
            }


        }
        return result;
    }
    @Override
    public boolean remove(Author author){
        boolean result = false;
        for (int i=0;i<authors.length;i++){
            if(this.findByFullName(author.getName(),author.getLastName())!=null){
                Author[] fieldForCopy = new Author[authors.length];
                System.arraycopy(authors,0,fieldForCopy,0,authors.length);
                authors= new Author[fieldForCopy.length-1];
                System.arraycopy(fieldForCopy,i+1,authors,i,authors.length);
               result = true;
            }
        }
        return result;
    }
    @Override
    public int count(){
        return authors.length;
    };
}
