package model.companies;

import model.Command;
import model.developers.DeveloperDaoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class GetInformationAboutAllCompaniesCommand implements Command {
    CompanyDaoService companyDaoService;


    public GetInformationAboutAllCompaniesCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        companyDaoService = new CompanyDaoService(connection);
    }


    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", companyDaoService.getAll())
        );

        engine.process("companies/showAllCompanies", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
