package model.companies;

import model.Command;
import model.developers.Developer;
import model.developers.DeveloperDaoService;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateCompanyCommand implements Command {
    CompanyDaoService companyDaoService;

    public UpdateCompanyCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        companyDaoService = new CompanyDaoService(connection);
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
