package storage.hibernate;


import lombok.Getter;
import model.companies.Company;
import model.customers.Customer;
import model.developers.Developer;
import model.projects.Project;
import model.skills.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import prefs.Prefs;
import storage.DatabaseInitService;

import java.util.List;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Skill.class)
                .buildSessionFactory();

        new DatabaseInitService().initDB(new Prefs().getString(Prefs.URL));
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

}
