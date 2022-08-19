package model.companies;

import model.Command;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCompanyCommand implements Command {
    ICompanyDaoService companyDaoService;

    public DeleteCompanyCommand() throws SQLException {
        companyDaoService = new HibernateCompaniesDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        companyDaoService.deleteById(Long.parseLong(req.getParameter("companyId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
