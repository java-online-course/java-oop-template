package ru.izh.online.course.task1;

import ru.izh.online.course.task1.entity.Developer;
import ru.izh.online.course.task1.entity.Project;
import ru.izh.online.course.task1.entity.SimpleProject;
import ru.izh.online.course.task1.entity.dev.AutomatedTester;
import ru.izh.online.course.task1.entity.dev.BackendDeveloper;
import ru.izh.online.course.task1.entity.dev.FrontendDeveloper;

public class Main {

    public static void main(String[] args) {
        Project project = new SimpleProject();

        Developer techLead = new BackendDeveloper();
        project.assignTechLead(techLead);

        Developer[] team = {
                new FrontendDeveloper(),
                new FrontendDeveloper(),
                new BackendDeveloper(),
                new BackendDeveloper(),
                new AutomatedTester()
        };
        project.addTeam(team);
    }
}
