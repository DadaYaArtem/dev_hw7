package model.customers;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCustomerCommand implements Command {
    ICustomerDaoService customerDaoService;

    public DeleteCustomerCommand() throws SQLException {
        customerDaoService = new HibernateCustomerDaoService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        customerDaoService.deleteById(Long.parseLong(req.getParameter("customerId")));

//        resp.getWriter().write("Successfully created new developers with id: " + req.getParameter("developerId") + 1);
    }
}
