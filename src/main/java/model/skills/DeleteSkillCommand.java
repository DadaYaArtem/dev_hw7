package model.skills;

import model.Command;
import model.projects.ProjectDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteSkillCommand implements Command {
    SkillDaoService skillDaoService;

    public DeleteSkillCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        skillDaoService = new SkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        skillDaoService.deleteById(Long.parseLong(req.getParameter("skillId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
