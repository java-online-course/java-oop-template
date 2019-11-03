package ru.izh.online.course.entity;

/**
 * Базовая сущность для кинг, журналов и тп.
 */
public abstract class BasicEntity {

    protected long id;
    protected int pages;
    protected String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
