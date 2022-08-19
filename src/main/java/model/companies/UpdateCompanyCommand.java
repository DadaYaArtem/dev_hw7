package model.companies;

import model.Command;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateCompanyCommand implements Command {
    ICompanyDaoService companyDaoService;

    public UpdateCompanyCommand() throws SQLException {
        companyDaoService = new HibernateCompaniesDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Company company = new Company();
        company.setId(Long.parseLong(req.getParameter("companyId")));
        company.setName(req.getParameter("companyName"));
        company.setCeo(req.getParameter("companyCEO"));

        companyDaoService.update(company);
    }
}
