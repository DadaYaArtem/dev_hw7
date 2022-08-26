package model.developerProject;

import model.Command;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class GetAboutProjectsCommand implements Command {
    HibernateDevProjDaoService developerProjectDaoService;

    public GetAboutProjectsCommand() throws SQLException {
        developerProjectDaoService = new HibernateDevProjDaoService();
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
