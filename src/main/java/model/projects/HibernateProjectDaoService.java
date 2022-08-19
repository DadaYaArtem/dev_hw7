package model.projects;

import model.developers.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class HibernateProjectDaoService implements IProjectDaoService{
    @Override
    public long create(Project project) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
        session.close();
        return project.getId();
    }

    @Override
    public Project getById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public List<Project> getAll() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Project> project = session.createQuery("from Project", Project.class).list();
        session.close();
        return project;
    }

    @Override
    public void update(Project project) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(project);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;BEGIN TRANSACTION").executeUpdate();

        NativeQuery query1 = session.createNativeQuery("DELETE FROM PROJECTS WHERE ID=:projId");
        query1.setParameter("projId", id);
        query1.executeUpdate();
        NativeQuery query2 = session.createNativeQuery("DELETE FROM DEVELOPERS_PROJECTS WHERE PROJECT_ID=:projDevId");
        query2.setParameter("projDevId", id);
        query2.executeUpdate();
        NativeQuery query3 = session.createNativeQuery("DELETE FROM PROJECTS_COMPANIES WHERE PROJECT_ID=:projCompId");
        query3.setParameter("projCompId", id);
        query3.executeUpdate();
        NativeQuery query4 = session.createNativeQuery("DELETE FROM PROJECT_CUSTOMERS WHERE PROJECT_ID=:projCustId");
        query4.setParameter("projCustId", id);
        query4.executeUpdate();

        session.createNativeQuery("COMMIT;SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

        transaction.commit();
        session.clear();
        session.close();
    }

    @Override
    public long maxId() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Long> query = session.createQuery("SELECT MAX(id) from Project as maxID", Long.class);
        Long singleResult = query.getSingleResult();
        transaction.commit();
        session.close();
        return singleResult;
    }
}
