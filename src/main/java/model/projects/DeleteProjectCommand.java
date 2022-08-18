package model.projects;

import model.Command;
import model.companies.CompanyDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteProjectCommand implements Command {
    ProjectDaoService projectDaoService;


    public DeleteProjectCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        projectDaoService = new ProjectDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        projectDaoService.deleteById(Long.parseLong(req.getParameter("projectId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
