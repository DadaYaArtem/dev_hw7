package model.projects;

import model.Command;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GetInformationAboutProjectByIDCommand implements Command {
    IProjectDaoService projectDaoService;


    public GetInformationAboutProjectByIDCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        projectDaoService = new HibernateProjectDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        Context context = new Context();
        context.setVariable("project", projectDaoService.getById(Long.parseLong(req.getParameter("projectId"))));
        System.out.println(req.getParameter("projectId"));
        System.out.println(projectDaoService.getById(Long.parseLong(req.getParameter("projectId"))));

        engine.process("projects/projectById", context, resp.getWriter());
        resp.getWriter().close();
    }
}
