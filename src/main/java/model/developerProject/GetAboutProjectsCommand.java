package model.developerProject;

import model.Command;
import model.developerProject.DeveloperProjectDaoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class GetAboutProjectsCommand implements Command {
    DeveloperProjectDaoService developerProjectDaoService;

    public GetAboutProjectsCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        developerProjectDaoService = new DeveloperProjectDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        System.out.println(Long.parseLong(req.getParameter("developerId")));
        System.out.println(developerProjectDaoService.getAllProjects(Long.parseLong(req.getParameter("developerId"))));

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", developerProjectDaoService.getAllProjects(Long.parseLong(req.getParameter("developerId"))))
        );

        engine.process("developers/showAllDevelopersProjects", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
