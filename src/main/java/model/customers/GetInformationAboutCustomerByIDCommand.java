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

public class GetInformationAboutCustomerByIDCommand implements Command {
    ICustomerDaoService customerDaoService;

    public GetInformationAboutCustomerByIDCommand() throws SQLException {
        customerDaoService = new HibernateCustomerDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        Context context = new Context();
        context.setVariable("customer", customerDaoService.getById(Long.parseLong(req.getParameter("customerId"))));
        System.out.println(req.getParameter("customerId"));
        System.out.println(customerDaoService.getById(Long.parseLong(req.getParameter("customerId"))));

        engine.process("customers/customerById", context, resp.getWriter());
        resp.getWriter().close();
    }
}
