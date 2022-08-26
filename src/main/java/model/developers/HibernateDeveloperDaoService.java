package model.developers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class HibernateDeveloperDaoService implements IDeveloperDaoService{
    @Override
    public long create(Developer developer) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(developer);
            transaction.commit();
        session.close();
        return developer.getId();
    }

    @Override
    public Developer getById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Developer> developers = session.createQuery("from Developer", Developer.class).list();
        session.close();
        return developers;
    }

    @Override
    public void update(Developer developer) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(developer);
            transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;BEGIN TRANSACTION").executeUpdate();

        NativeQuery query1 = session.createNativeQuery("DELETE FROM DEVELOPERS WHERE ID=:devId");
        query1.setParameter("devId", id);
        query1.executeUpdate();
        NativeQuery query2 = session.createNativeQuery("DELETE FROM SKILLS_DEVELOPERS WHERE DEVELOPER_ID=:devSkillId");
        query2.setParameter("devSkillId", id);
        query2.executeUpdate();
        NativeQuery query3 = session.createNativeQuery("DELETE FROM DEVELOPERS_PROJECTS WHERE DEVELOPER_ID=:devProjId");
        query3.setParameter("devProjId", id);
        query3.executeUpdate();

        session.createNativeQuery("COMMIT;SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

        transaction.commit();
        session.clear();
        session.close();
    }


    @Override
    public long maxId() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Query<Long> query = session.createQuery("SELECT MAX(id) from Developer as maxID", Long.class);
                Long singleResult = query.getSingleResult();
            transaction.commit();
        session.close();
        return singleResult;
    }
}
