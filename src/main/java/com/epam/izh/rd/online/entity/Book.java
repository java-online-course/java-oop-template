package com.epam.izh.rd.online.entity;

import java.util.Objects;

/**
 * Базовая сущность для книги. Содержит базовые поля.
 * <p>
 * Необходимо:
 * 1) Создать список полей с указанными типами ровно в этом порядке:
 * - numberOfPages с типом int и приватным модификатором доступа
 * - name с типом String и приватным модификатором доступа
 * 2) Создать дефолтный конструктор (без параметров) (не забывайте alt+inset)
 * 3) Создать конструктор со всеми параметрами (в том порядке в котором перечислены) (не забывайте alt+inset)
 * 4) Создать геттеры и сеттеры (не забывайте alt+inset)
 * 5) Переопределить методы equals и hashCode - используйте генерацию (не забывайте alt+inset)
 * 6) Переопределить метод toString с выводом всех полей (не забывайте alt+inset)
 */
public abstract class Book {

    private int numberOfPages;
    private String name;

    public Book() {
    }

    public Book(int numberOfPages, String name) {
        this.numberOfPages = numberOfPages;
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean areEqual = false;
        if (obj instanceof Book) {
            Book objBook = (Book) obj;
            if (objBook.getName().equals(this.getName()) && ((Book) obj).getNumberOfPages() == this.getNumberOfPages()) {
                areEqual = true;
            }
        }
        return areEqual;
    }

    @Override
    public String toString() {
        return numberOfPages + " " + name;
    }
}
