package com.epam.izh.rd.online.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Сущность учебника. Он должен быть унаследован от сущности Book
 *
 * Необходимо:
 * 1) Унаследовать данный класс от класса Book
 * 2) Создать список полей с указанными типами ровно в этом порядке:
 * - authorName с типом String и приватным модификатором доступа
 * - authorLastName с типом String и приватным модификатором доступа
 * - publishDate с типом LocalDate и приватным модификатором доступа
 * 2) Создать дефолтный конструктор (без параметров) (не забывайте alt+inset)
 * 3) Создать конструктор со всеми параметрами (важно - не только с полями данного класса, но и с полями родителя Book)
 * (создавать в том порядке в котором перечислены). Сначала нужно создать аналогичный конструтор для Book. Используйте alt+inset.
 * 4) Создать геттеры и сеттеры (не забывайте alt+inset)
 * 5) Переопределить методы equals и hashCode - используйте генерацию (не забывайте alt+inset)
 * 6) Переопределить метод toString с выводом всех полей (не забывайте alt+inset)
 */
public class SchoolBook extends Book {

    private String name;
    private String authorLastName;
    private LocalDate publishDate;

    public SchoolBook() {
    }

    public SchoolBook(int numberOfPages, String bookName, String authorName, String authorLastName, LocalDate publishDate) {
        super(numberOfPages, bookName);
        this.name = authorName;
        this.authorLastName = authorLastName;
        this.publishDate = publishDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolBook that = (SchoolBook) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(authorLastName, that.authorLastName) &&
                Objects.equals(publishDate, that.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, authorLastName, publishDate);
    }

    @Override
    public String toString() {
        return "SchoolBook{" +
                "name='" + name + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
