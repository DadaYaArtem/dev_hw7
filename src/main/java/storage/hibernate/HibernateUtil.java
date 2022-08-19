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
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        HibernateUtil util = HibernateUtil.getInstance();

//        Get single
//        Session session = util.getSessionFactory().openSession();
//        Developer developer = session.get(Developer.class, 5L);
//        System.out.println(developer);
//        session.close();

        //List all developers
//        Session session = util.getSessionFactory().openSession();
//        List<Developer> from_customer = session.createQuery("from Developer", Developer.class).list();
//        System.out.println(from_customer);
//        session.close();

        //Save new dev
//        Session session = util.getSessionFactory().openSession();
//             Transaction transaction = session.beginTransaction();
//                  Developer developer = new Developer();
//                  developer.setFirst_name("fname");
//                  developer.setLast_name("lname");
//                  developer.setSalary(1);
//                  developer.setAge(2);
//                  session.persist(developer);
//        System.out.println(developer);
//        transaction.commit();
//        session.close();

//        Modify existing dev
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Developer existing = session.get(Developer.class, 4L);
//                existing.setFirst_name("new fname");
//                session.persist(existing);
//            transaction.commit();
//        session.close();

//        List skills
        Session session = util.getSessionFactory().openSession();
        List<Skill> from_skill = session.createQuery("from Skill", Skill.class).list();
        System.out.println(from_skill);
        session.close();
    }
}
