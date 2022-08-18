package model.main;

import model.Command;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseTableCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        switch (req.getParameter("tables")) {
            case "developers" -> resp.sendRedirect("/ProjectManagementSystem/developers");
            case "projects" -> resp.sendRedirect("/ProjectManagementSystem/projects");
            case "companies" -> resp.sendRedirect("/ProjectManagementSystem/companies");
            case "customers" -> resp.sendRedirect("/ProjectManagementSystem/customers");
            case "skills" -> resp.sendRedirect("/ProjectManagementSystem/skills");
            default -> resp.sendRedirect("/ProjectManagementSystem");
        }
    }
}
