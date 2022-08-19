package model.customers;

import model.Command;
import org.thymeleaf.TemplateEngine;
import storage.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddCustomerCommand implements Command {
    ICustomerDaoService customerDaoService;

    public AddCustomerCommand() throws SQLException {
        customerDaoService = new HibernateCustomerDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Customer customer = new Customer();
        customer.setFirst_name(req.getParameter("customerFirstName"));
        customer.setLast_name(req.getParameter("customerLastName"));
        customer.setPhone_number((req.getParameter("customerPhone_number")));

        if (customerDaoService.create(customer) == customerDaoService.maxId()) {
            resp.getWriter().write("Successfully created new customer with id: " + customerDaoService.maxId());
        } else {
            resp.getWriter().write("Error");
        }
    }
}
