package model.developers;

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

public class GetInformationAboutAllDevelopersCommand implements Command {
    IDeveloperDaoService developerDaoService;


    public GetInformationAboutAllDevelopersCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        developerDaoService = new HibernateDeveloperDaoService();
    }


    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html, charset=utf-8");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", developerDaoService.getAll())
        );

        engine.process("developers/showAllDevelopers", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
