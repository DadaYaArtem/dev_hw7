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

public class GetInformationAboutCompanyByIDCommand implements Command {
    CompanyDaoService companyDaoService;


    public GetInformationAboutCompanyByIDCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        companyDaoService = new CompanyDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        Context context = new Context();
        context.setVariable("company", companyDaoService.getById(Long.parseLong(req.getParameter("companyId"))));
        System.out.println(req.getParameter("companyId"));
        System.out.println(companyDaoService.getById(Long.parseLong(req.getParameter("companyId"))));

        engine.process("companies/companyById", context, resp.getWriter());
        resp.getWriter().close();
    }
}
