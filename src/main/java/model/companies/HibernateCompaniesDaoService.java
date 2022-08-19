package model.companies;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class HibernateCompaniesDaoService implements ICompanyDaoService {

    @Override
    public long create(Company company) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        session.close();
        return company.getId();
    }

    @Override
    public Company getById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Company> developers = session.createQuery("from Company", Company.class).list();
        session.close();
        return developers;
    }

    @Override
    public void update(Company company) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(company);
            transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;BEGIN TRANSACTION").executeUpdate();

        NativeQuery query1 = session.createNativeQuery("DELETE FROM COMPANIES WHERE ID=:compId");
        query1.setParameter("compId", id);
        query1.executeUpdate();
        NativeQuery query2 = session.createNativeQuery("DELETE FROM PROJECTS_COMPANIES WHERE COMPANY_ID=:compProjId");
        query2.setParameter("compProjId", id);
        query2.executeUpdate();

        session.createNativeQuery("COMMIT;SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

        transaction.commit();
        session.close();
    }


    @Override
    public long maxId() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Query<Long> query = session.createQuery("SELECT MAX(id) from Companies as maxID", Long.class);
                Long singleResult = query.getSingleResult();
            transaction.commit();
        session.close();
        return singleResult;
    }
}
