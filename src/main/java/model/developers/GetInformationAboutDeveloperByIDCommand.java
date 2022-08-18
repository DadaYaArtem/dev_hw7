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

public class GetInformationAboutDeveloperByIDCommand implements Command {
    IDeveloperDaoService developerDaoService;

    public GetInformationAboutDeveloperByIDCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        developerDaoService = new HibernateDeveloperDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        Context context = new Context();
        context.setVariable("developer", developerDaoService.getById(Long.parseLong(req.getParameter("developerId"))));
        System.out.println(req.getParameter("developerId"));
        System.out.println(developerDaoService.getById(Long.parseLong(req.getParameter("developerId"))));

        engine.process("developers/developerById", context, resp.getWriter());
        resp.getWriter().close();
    }
}
