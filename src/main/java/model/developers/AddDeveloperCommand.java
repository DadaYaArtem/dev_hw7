package model.developers;

import model.Command;
import model.developerProject.HibernateDevProjDaoService;
import model.developerSkill.HibernateDevSkillDaoService;
import model.projects.HibernateProjectDaoService;
import model.projects.IProjectDaoService;
import model.projects.Project;
import model.skills.HibernateSkillDaoService;
import model.skills.ISkillDaoService;
import model.skills.Skill;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddDeveloperCommand implements Command {
    IDeveloperDaoService developerDaoService;
    IProjectDaoService projectDaoService;
    ISkillDaoService skillDaoService;
    HibernateDevProjDaoService developerProjectDaoService;
    HibernateDevSkillDaoService developerSkillDaoService;

    public AddDeveloperCommand() throws SQLException {
        developerDaoService = new HibernateDeveloperDaoService();
        projectDaoService = new HibernateProjectDaoService();
        developerProjectDaoService = new HibernateDevProjDaoService();
        skillDaoService = new HibernateSkillDaoService();
        developerSkillDaoService = new HibernateDevSkillDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Developer developer = new Developer();
        developer.setFirst_name(req.getParameter("developerFirstName"));
        developer.setLast_name(req.getParameter("developerLastName"));
        developer.setAge(Integer.parseInt(req.getParameter("developerAge")));
        developer.setSalary(Integer.parseInt(req.getParameter("developerSalary")));

        long devId = developerDaoService.maxId() + 1;

        List<String> projectNames = projectDaoService.getAll().stream().map(Project::getName).toList();
        List<String> branches = skillDaoService.getAll().stream().map(Skill::getBranch).toList();
        List<String> skills = skillDaoService.getAll().stream().map(it -> it.getSkill().name()).toList();


        if (projectNames.contains(req.getParameter("developerProject"))) {
            List<Long> projId = projectDaoService.getAll()
                    .stream()
                    .filter(it -> it.getName().equals(req.getParameter("developerProject")))
                    .map(Project::getId)
                    .toList();

            developer.getProjects().add(projectDaoService.getById(projId.get(0)));
        }

        if (branches.contains(req.getParameter("developerLanguage")) && skills.contains(req.getParameter("developerLanguageLevel"))) {
            List<Long> skillId = skillDaoService.getAll()
                    .stream()
                    .filter(it -> it.getBranch().equals(req.getParameter("developerLanguage")))
                    .filter(it -> it.getSkill().name().equals(req.getParameter("developerLanguageLevel")))
                    .map(Skill::getId)
                    .toList();

            developer.getSkills().add(skillDaoService.getById(skillId.get(0)));
            System.out.println(skillDaoService.getAll());
        }

        developerDaoService.create(developer);
    }
}
