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
import java.util.Map;

public class GetInformationAboutAllProjectsCommand implements Command {
    IProjectDaoService projectDaoService;

    public GetInformationAboutAllProjectsCommand() throws SQLException {
        projectDaoService = new HibernateProjectDaoService();
    }


    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", projectDaoService.getAll())
        );

        engine.process("projects/showAllProjects", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
