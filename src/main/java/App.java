import model.companies.CompanyDaoService;
import model.developerProject.DeveloperProjectDaoService;
import model.developers.Developer;
import model.developers.DeveloperDaoService;
import model.developers.HibernateDeveloperDaoService;
import model.projects.Project;
import model.projects.ProjectDaoService;
import prefs.Prefs;
import storage.DatabaseConnection;
import storage.DatabaseInitService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Connection connection = DatabaseConnection.getConnection();
//        String connectionUrl = new Prefs().getString(Prefs.URL);
        new DatabaseInitService().initDB("jdbc:h2:D:\\homeworks\\hw-6\\test");
//
//        ProjectDaoService projectDaoService = new ProjectDaoService(connection);
//        DeveloperProjectDaoService developerProjectDaoService = new DeveloperProjectDaoService(connection);


        HibernateDeveloperDaoService hDevDaoService = new HibernateDeveloperDaoService();
        System.out.println(hDevDaoService.getById(2));
//        hDevDaoService.create(new Developer());
//        System.out.println(hDevDaoService.getAll());

    }
}
