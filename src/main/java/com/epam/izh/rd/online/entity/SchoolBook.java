package com.epam.izh.rd.online.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Сущность учебника. Он должен быть унаследован от сущности Book
 * <p>
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

    private String authorName;
    private String authorLastName;
    private LocalDate publishDate;

    public SchoolBook() {
    }

    public SchoolBook(int numberOfPages, String authorName, String authorLastName, String name, LocalDate publishDate) {
        super(numberOfPages, name);
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.publishDate = publishDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SchoolBook that = (SchoolBook) object;
        return java.util.Objects.equals(authorName, that.authorName) &&
                java.util.Objects.equals(authorLastName, that.authorLastName) &&
                java.util.Objects.equals(publishDate, that.publishDate);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), authorName, authorLastName, publishDate);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SchoolBook{" +
                "authorName='" + authorName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
