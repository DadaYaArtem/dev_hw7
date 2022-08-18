package model.skills;

import model.Command;
import model.projects.ProjectDaoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class GetInformationAboutAllSkillsCommand implements Command {
    SkillDaoService skillDaoService;

    public GetInformationAboutAllSkillsCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        skillDaoService = new SkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", skillDaoService.getAll())
        );

        engine.process("skills/showAllSkills", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
