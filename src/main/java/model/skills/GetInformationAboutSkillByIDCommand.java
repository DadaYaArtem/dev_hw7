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

public class GetInformationAboutSkillByIDCommand implements Command {
    SkillDaoService skillDaoService;

    public GetInformationAboutSkillByIDCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        skillDaoService = new SkillDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        Context context = new Context();
        context.setVariable("skill", skillDaoService.getById(Long.parseLong(req.getParameter("skillId"))));
        System.out.println(req.getParameter("skillId"));
        System.out.println(skillDaoService.getById(Long.parseLong(req.getParameter("skillId"))));

        engine.process("skills/skillById", context, resp.getWriter());
        resp.getWriter().close();
    }
}
