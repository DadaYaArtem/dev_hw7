package model.skills;

import model.Command;
import model.projects.Project;
import model.projects.ProjectDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddSkillCommand implements Command {
    SkillDaoService skillDaoService;

    public AddSkillCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        skillDaoService = new SkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Skill skill = new Skill();
        skill.setBranch(Skill.Branch.valueOf(req.getParameter("skillBranch")));
        skill.setSkill(Skill.Skillset.valueOf(req.getParameter("skillSkill")));

        if (skillDaoService.create(skill) == skillDaoService.maxId()) {
            resp.getWriter().write("Successfully created new skill with id: " + skillDaoService.maxId());
        } else {
            resp.getWriter().write("Error");
        }
    }
}
