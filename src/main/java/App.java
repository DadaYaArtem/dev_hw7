import model.customers.HibernateCustomerDaoService;
import model.developers.HibernateDeveloperDaoService;
import prefs.Prefs;
import storage.DatabaseInitService;

import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException{
        new DatabaseInitService().initDB(new Prefs().getString(Prefs.URL));

        HibernateDeveloperDaoService hDevDaoService = new HibernateDeveloperDaoService();
        HibernateCustomerDaoService hCusDaoService = new HibernateCustomerDaoService();

//        hDevDaoService.create(new Developer());
        System.out.println(hDevDaoService.getAll());
    }
}
