package model.developers;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteDeveloperCommand  implements Command {
    IDeveloperDaoService developerDaoService;

    public DeleteDeveloperCommand() throws SQLException {
        developerDaoService = new HibernateDeveloperDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        developerDaoService.deleteById(Long.parseLong(req.getParameter("developerId")));
    }
}
