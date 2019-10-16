package ru.izh.online.course.task1.entity.project;

import ru.izh.online.course.task1.entity.Developer;

/**
 * Сущность проект. У проекта есть id, техлид (разработчик уровня 5), команда разработки(бек-девелоперы, тестеры,
 * фронт-девелоперы), заказчик, дата старта, предположительная дата окончания, список необходимых скиллов
 */
public class SimpleProject implements Project{

    //TODO поля

    /**
     * Метод, назначающий техлида на текущий проект
     * @param developer - должен быть уровня L5
     * @return удалось назначить техлида или нет
     */
    public boolean assignTechLead(Developer developer) {
        return false; //TODO
    }

    /**
     * Проверяет, удовлетворяет ли разработчик проекту: количество совпадающих скиллов должно быть больше 50%
     * @param developer
     * @return
     */
    public boolean isDeveloperFits(Developer developer) {
        return false; //TODO
    }

    /**
     * Назначает всю команду в проект. Должен вызывать метод addDeveloper
     * @param developers
     */
    public boolean addTeam(Developer[] developers) {
        return false; //TODO
    }

    /**
     * Назначает разработчика на проект. Должен вызывать метод isDeveloperFits
     * @param developer
     * @return
     */
    public boolean addDeveloper(Developer developer) {
        return false; //TODO
    }

    public String toString() {
        return ""; //TODO
    }
}
