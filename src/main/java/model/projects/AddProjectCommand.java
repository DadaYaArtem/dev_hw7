package model.projects;

import model.Command;
import model.companies.Company;
import model.companies.CompanyDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddProjectCommand implements Command {
    ProjectDaoService projectDaoService;


    public AddProjectCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        projectDaoService = new ProjectDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Project project = new Project();
        project.setName(req.getParameter("projectName"));
        project.setCost(Integer.parseInt(req.getParameter("projectCost")));
        project.setFoundation_date(LocalDate.parse(req.getParameter("projectDate")));

        if (projectDaoService.create(project) == projectDaoService.maxId()) {
            resp.getWriter().write("Successfully created new project with id: " + projectDaoService.maxId());
        } else {
            resp.getWriter().write("Error");
        }
    }
}
