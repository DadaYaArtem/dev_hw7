package model.customers;

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

public class UpdateCustomerCommand implements Command {
    CustomerDaoService customerDaoService;

    public UpdateCustomerCommand() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        customerDaoService = new CustomerDaoService(connection);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        resp.setContentType("text/html, charset=utf-8");

        Customer customer = new Customer();
        customer.setId(Long.parseLong(req.getParameter("customerId")));
        customer.setFirst_name(req.getParameter("customerFirstName"));
        customer.setLast_name(req.getParameter("customerLastName"));
        customer.setPhone_number(req.getParameter("customerPhone_number"));

        customerDaoService.update(customer);
    }
}
