package model.skills;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateSkillCommand implements Command {
    ISkillDaoService skillDaoService;

    public UpdateSkillCommand() throws SQLException {
        skillDaoService = new HibernateSkillDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Skill skill = new Skill();
        skill.setId(Long.parseLong(req.getParameter("skillId")));
        skill.setBranch((req.getParameter("skillBranch")));
        skill.setSkill(Skill.Skillset.valueOf(req.getParameter("skillSkill")));


        skillDaoService.update(skill);
    }
}
