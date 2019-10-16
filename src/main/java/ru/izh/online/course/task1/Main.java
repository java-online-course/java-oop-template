package ru.izh.online.course.task1;

import ru.izh.online.course.task1.entity.Developer;
import ru.izh.online.course.task1.entity.project.Project;
import ru.izh.online.course.task1.entity.project.SimpleProject;

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

    /**
     * @return команду разработки (1 тестировщик, 2 бекендера и 3 фронтендера)
     */
    static Developer[] getDevTeam() {
        return null; //TODO
    }

    /**
     * @return техлида. Имеет уровень 5
     */
    static Developer getTechLead() {
        return null; //TODO
    }

    /**
     * @return предзаполненный {@link SimpleProject}
     */
    static Project getProject() {
        return null; //TODO
    }
}
