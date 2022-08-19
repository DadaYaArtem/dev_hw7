package model.skills;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddSkillCommand implements Command {
    ISkillDaoService skillDaoService;

    public AddSkillCommand() throws SQLException {
        skillDaoService = new HibernateSkillDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Skill skill = new Skill();
        skill.setBranch((req.getParameter("skillBranch")));
        skill.setSkill(Skill.Skillset.valueOf(req.getParameter("skillSkill")));

        if (skillDaoService.create(skill) == skillDaoService.maxId()) {
            resp.getWriter().write("Successfully created new skill with id: " + skillDaoService.maxId());
        } else {
            resp.getWriter().write("Error");
        }
    }
}
