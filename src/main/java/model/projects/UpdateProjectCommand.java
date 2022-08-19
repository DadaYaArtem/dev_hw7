package model.projects;

import model.Command;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateProjectCommand implements Command {
    IProjectDaoService projectDaoService;


    public UpdateProjectCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        projectDaoService = new HibernateProjectDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Project project = new Project();
        project.setId(Long.parseLong(req.getParameter("projectId")));
        project.setName(req.getParameter("projectCost"));
        project.setCost(Integer.parseInt(req.getParameter("projectCost")));
        project.setFoundation_date(LocalDate.parse(req.getParameter("projectDate")));


        projectDaoService.update(project);
    }
}
