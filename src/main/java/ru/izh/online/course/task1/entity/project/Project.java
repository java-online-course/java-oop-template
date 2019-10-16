package ru.izh.online.course.task1.entity.project;

import ru.izh.online.course.task1.entity.Developer;

/**
 * Проект
 */
public interface Project {

    boolean assignTechLead(Developer developer);

    boolean addTeam(Developer[] developers);

    boolean addDeveloper(Developer developer);

    boolean isDeveloperFits(Developer developer);

}
