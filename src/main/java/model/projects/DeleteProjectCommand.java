package model.projects;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProjectCommand implements Command {
    IProjectDaoService projectDaoService;

    public DeleteProjectCommand() throws SQLException {
        projectDaoService = new HibernateProjectDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        projectDaoService.deleteById(Long.parseLong(req.getParameter("projectId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
