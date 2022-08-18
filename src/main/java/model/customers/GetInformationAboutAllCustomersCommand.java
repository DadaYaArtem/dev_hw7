package model.customers;

import model.Command;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class GetInformationAboutAllCustomersCommand implements Command {
    CustomerDaoService customerDaoService;


    public GetInformationAboutAllCustomersCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        customerDaoService = new CustomerDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("list", customerDaoService.getAll())
        );

        engine.process("customers/showAllCustomers", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
