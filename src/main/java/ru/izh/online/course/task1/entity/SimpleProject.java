package ru.izh.online.course.task1.entity;

/**
 * Сущность проект. У проекта есть id, техлид (разработчик уровня 5), команда разработки(бек-девелоперы, тестеры,
 * фронт-девелоперы), заказчик, дата старта, предположительная дата окончания, список необходимых скиллов
 */
public class SimpleProject implements Project{

    public boolean assignTechLead(Developer developer) {
        //TODO
        return false;
    }

    public boolean isDeveloperFits(Developer developer) {
        //TODO
        return false;
    }

    public boolean addTeam(Developer[] developers) {
        //TODO
        return false;
    }

    public boolean addDeveloper(Developer developer) {
        //TODO
        return false;
    }

    public String toString() {
        //TODO
        return "";
    }
}
