package model.customers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class HibernateCustomerDaoService implements ICustomerDaoService{
    @Override
    public long create(Customer customer) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
        session.close();
        return customer.getId();
    }

    @Override
    public Customer getById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Customer> developers = session.createQuery("from Customer", Customer.class).list();
        session.close();
        return developers;
    }

    @Override
    public void update(Customer customer) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;BEGIN TRANSACTION").executeUpdate();

        NativeQuery query1 = session.createNativeQuery("DELETE FROM customers WHERE ID=:cusId");
        query1.setParameter("cusId", id);
        query1.executeUpdate();
        NativeQuery query2 = session.createNativeQuery("DELETE FROM PROJECT_CUSTOMERS WHERE CUSTOMER_ID=:cusProjId");
        query2.setParameter("cusProjId", id);
        query2.executeUpdate();

        session.createNativeQuery("COMMIT;SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public long maxId() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Long> query = session.createQuery("SELECT MAX(id) from Customer as maxID", Long.class);
        Long singleResult = query.getSingleResult();
        transaction.commit();
        session.close();
        return singleResult;
    }
}
