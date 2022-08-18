package model;

import model.companies.*;
import model.customers.*;
import model.developerProject.GetAboutProjectsCommand;
import model.developerSkill.GetAboutDeveloperSkills;
import model.main.ChooseTableCommand;
import model.main.StartCommand;
import model.projects.*;
import model.skills.*;
import model.developers.*;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private Map<String, Command> commands;

    public CommandService() throws SQLException {
        commands = new HashMap<>();

        commands.put("GET /ProjectManagementSystem", new StartCommand());
        commands.put("POST /ProjectManagementSystem", new ChooseTableCommand());

        commands.put("GET /ProjectManagementSystem/developers", new DevelopersMenuCommand());
        commands.put("GET /ProjectManagementSystem/developers/all_developers", new GetInformationAboutAllDevelopersCommand());
        commands.put("POST /ProjectManagementSystem/developers/developer_info", new GetInformationAboutDeveloperByIDCommand());
        commands.put("POST /ProjectManagementSystem/developers/add", new AddDeveloperCommand());
        commands.put("POST /ProjectManagementSystem/developers/update", new UpdateDeveloperCommand());
        commands.put("POST /ProjectManagementSystem/developers/delete", new DeleteDeveloperCommand());
        commands.put("POST /ProjectManagementSystem/developers/developer_project_info", new GetAboutProjectsCommand());
        commands.put("POST /ProjectManagementSystem/developers/developer_skill_info", new GetAboutDeveloperSkills());

        commands.put("GET /ProjectManagementSystem/companies", new CompaniesMenuCommand());
        commands.put("GET /ProjectManagementSystem/companies/all_companies", new GetInformationAboutAllCompaniesCommand());
        commands.put("POST /ProjectManagementSystem/companies/company_info", new GetInformationAboutCompanyByIDCommand());
        commands.put("POST /ProjectManagementSystem/companies/add", new AddCompanyCommand());
        commands.put("POST /ProjectManagementSystem/companies/update", new UpdateCompanyCommand());
        commands.put("POST /ProjectManagementSystem/companies/delete", new DeleteCompanyCommand());

        commands.put("GET /ProjectManagementSystem/customers", new CustomersMenuCommand());
        commands.put("GET /ProjectManagementSystem/customers/all_customers", new GetInformationAboutAllCustomersCommand());
        commands.put("POST /ProjectManagementSystem/customers/customer_info", new GetInformationAboutCustomerByIDCommand());
        commands.put("POST /ProjectManagementSystem/customers/add", new AddCustomerCommand());
        commands.put("POST /ProjectManagementSystem/customers/update", new UpdateCustomerCommand());
        commands.put("POST /ProjectManagementSystem/customers/delete", new DeleteCustomerCommand());

        commands.put("GET /ProjectManagementSystem/projects", new ProjectsMenuCommand());
        commands.put("GET /ProjectManagementSystem/projects/all_projects", new GetInformationAboutAllProjectsCommand());
        commands.put("POST /ProjectManagementSystem/projects/project_info", new GetInformationAboutProjectByIDCommand());
        commands.put("POST /ProjectManagementSystem/projects/add", new AddProjectCommand());
        commands.put("POST /ProjectManagementSystem/projects/update", new UpdateProjectCommand());
        commands.put("POST /ProjectManagementSystem/projects/delete", new DeleteProjectCommand());

        commands.put("GET /ProjectManagementSystem/skills", new SkillsMenuCommand());
        commands.put("GET /ProjectManagementSystem/skills/all_skills", new GetInformationAboutAllSkillsCommand());
        commands.put("POST /ProjectManagementSystem/skills/skill_info", new GetInformationAboutSkillByIDCommand());
        commands.put("POST /ProjectManagementSystem/skills/add", new AddSkillCommand());
        commands.put("POST /ProjectManagementSystem/skills/update", new UpdateSkillCommand());
        commands.put("POST /ProjectManagementSystem/skills/delete", new DeleteSkillCommand());

    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;
        System.out.println(commandKey);
        commands.get(commandKey).process(req, resp, engine);
    }

}
