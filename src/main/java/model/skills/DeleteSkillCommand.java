package model.skills;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteSkillCommand implements Command {
    ISkillDaoService skillDaoService;

    public DeleteSkillCommand() throws SQLException {
        skillDaoService = new HibernateSkillDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        skillDaoService.deleteById(Long.parseLong(req.getParameter("skillId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
