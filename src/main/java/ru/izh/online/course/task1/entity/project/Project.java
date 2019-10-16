package ru.izh.online.course.task1.entity;

/**
 * Проект
 */
public interface Project {

    boolean assignTechLead(Developer developer);

    boolean addTeam(Developer[] developers);

    boolean addDeveloper(Developer developer);

    boolean isDeveloperFits(Developer developer);

}
