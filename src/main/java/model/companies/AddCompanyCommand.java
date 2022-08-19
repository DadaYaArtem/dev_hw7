package model.companies;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddCompanyCommand implements Command {
    ICompanyDaoService companyDaoService;

    public AddCompanyCommand() throws SQLException {
        companyDaoService = new HibernateCompaniesDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Company company = new Company();
        company.setName(req.getParameter("companyName"));
        company.setCeo(req.getParameter("companyCEO"));

        if (companyDaoService.create(company) == companyDaoService.maxId()) {
            resp.getWriter().write("Successfully created new company with id: " + companyDaoService.maxId());
        } else {
            resp.getWriter().write("Error");
        }
    }
}
