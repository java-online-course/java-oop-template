package ru.izh.online.course.task1.entity;

/**
 * Сущность проект. У проекта есть id, техлид (разработчик уровня 5), команда разработки(бек-девелоперы, тестеры,
 * фронт-девелоперы), заказчик, дата старта, предположительная дата окончания, список необходимых скиллов
 */
public class SimpleProject implements Project{

    public boolean assignTechLead(Developer developer) {
        return false;
    }

    public boolean isDeveloperFits(Developer developer) {
        return false;
    }

    public boolean addTeam(Developer[] developers) {
        return false;
    }

    public boolean addDeveloper(Developer developer) {
        return false;
    }
}
