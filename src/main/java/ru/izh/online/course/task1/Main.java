package ru.izh.online.course.task1;

import ru.izh.online.course.task1.entity.Developer;
import ru.izh.online.course.task1.entity.Project;

public class Main {

    public static void main(String[] args) {
        Project project = getProject();

        Developer techLead = getTechLead();
        project.assignTechLead(techLead);

        Developer[] team = getDevTeam();
        project.addTeam(team);

        printProjectTeamSkills(project);
    }

    /**
     * Должен выводить в консоль таблицу
     * Имя | Фамилия | Уровень | Опыт работы | Список скиллов
     * @param project - проект с командой и тилидом
     */
    static void printProjectTeamSkills(Project project) {
        //TODO
    }

    static Developer[] getDevTeam() {
        //TODO
        return null;
    }

    static Developer getTechLead() {
        //TODO
        return null;
    }

    static Project getProject() {
        //TODO
        return null;
    }
}
