package model.developers;

import model.Command;
import model.developerProject.DeveloperProjectDaoService;
import model.developerSkill.DeveloperSkillDaoService;
import model.projects.HibernateProjectDaoService;
import model.projects.IProjectDaoService;
import model.skills.HibernateSkillDaoService;
import model.skills.ISkillDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDeveloperCommand  implements Command {
    IDeveloperDaoService developerDaoService;
    IProjectDaoService projectDaoService;
    DeveloperProjectDaoService developerProjectDaoService;
    DeveloperSkillDaoService developerSkillDaoService;
    ISkillDaoService skillDaoService;

    public UpdateDeveloperCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        developerDaoService = new HibernateDeveloperDaoService();
        projectDaoService = new HibernateProjectDaoService();
        developerProjectDaoService = new DeveloperProjectDaoService(connection);
        skillDaoService = new HibernateSkillDaoService();
        developerSkillDaoService = new DeveloperSkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Developer developer = new Developer();
        developer.setId(Long.parseLong(req.getParameter("developerId")));
        developer.setFirst_name(req.getParameter("developerFirstName"));
        developer.setLast_name(req.getParameter("developerLastName"));
        developer.setAge(Integer.parseInt(req.getParameter("developerAge")));
        developer.setSalary(Integer.parseInt(req.getParameter("developerSalary")));

        developerDaoService.update(developer);

//        List<String> projectNames = projectDaoService.getAll().stream().map(Project::getName).toList();
//        List<String> branches = skillDaoService.getAll().stream().map(it -> it.getBranch().name()).toList();
//        List<String> skills = skillDaoService.getAll().stream().map(it -> it.getSkill().name()).toList();
//
//
//        if (projectNames.contains(req.getParameter("developerProject"))){
//            List<Long> projId = projectDaoService.getAll()
//                    .stream()
//                    .filter(it -> it.getName().equals(req.getParameter("developerProject")))
//                    .map(Project::getId)
//                    .toList();
//
//            developerProjectDaoService.updateDevProj(developer.getId(), projId.get(0));
//        }
//
//        if (branches.contains(req.getParameter("developerLanguage")) && skills.contains(req.getParameter("developerLanguageLevel"))){
//            List<Long> skillId = skillDaoService.getAll()
//                    .stream()
//                    .filter(it -> it.getBranch().name().equals(req.getParameter("developerLanguage")))
//                    .filter(it -> it.getSkill().name().equals(req.getParameter("developerLanguageLevel")))
//                    .map(Skill::getId)
//                    .toList();
//
//            developerSkillDaoService.insertIntoDevSkill(developer.getId(), skillId.get(0));
//        }
    }
}
