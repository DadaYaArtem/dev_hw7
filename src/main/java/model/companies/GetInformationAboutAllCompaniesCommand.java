package model.companies;

import model.Command;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class GetInformationAboutAllCompaniesCommand implements Command {
    ICompanyDaoService companyDaoService;

    public GetInformationAboutAllCompaniesCommand() throws SQLException {
        companyDaoService = new HibernateCompaniesDaoService();
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
