package model.developerSkill;

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

public class GetAboutDeveloperSkills implements Command {
    DeveloperSkillDaoService developerSkillDaoService;

    public GetAboutDeveloperSkills() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        developerSkillDaoService = new DeveloperSkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        System.out.println(Long.parseLong(req.getParameter("developerId")));
        System.out.println(developerSkillDaoService.getAllSkills(Long.parseLong(req.getParameter("developerId"))));

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", developerSkillDaoService.getAllSkills(Long.parseLong(req.getParameter("developerId"))))
        );

        engine.process("developers/showAllDevelopersSkills", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
