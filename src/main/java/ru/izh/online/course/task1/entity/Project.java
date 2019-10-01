package ru.izh.online.course.task1.entity;

/**
 * Проект
 */
public interface Project {

    /**
     * Метод, назначающий техлида на текущий проект
     * @param developer - должен быть уровня L5
     * @return удалось назначить техлида или нет
     */
    boolean assignTechLead(Developer developer);

    /**
     * Назначает всю команду в проект. Метод ОБЯЗАН вызывать метод addDeveloper
     * @param developers
     */
    boolean addTeam(Developer[] developers);

    /**
     * Назначает разработчика на проект. Должен вызывать isDeveloperFits
     * @param developer
     * @return
     */
    boolean addDeveloper(Developer developer);

    /**
     * Проверяет, удовлетворяет ли разработчик проекту: количество совпадающих скиллов должно быть больше 50%
     * @param developer
     * @return
     */
    boolean isDeveloperFits(Developer developer);

}
