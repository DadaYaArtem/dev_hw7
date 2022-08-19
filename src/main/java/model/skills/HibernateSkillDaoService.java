package model.skills;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class HibernateSkillDaoService implements ISkillDaoService {
    @Override
    public long create(Skill skill) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(skill);
            transaction.commit();
        session.close();
        return skill.getId();
    }

    @Override
    public Skill getById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Skill> skills = session.createQuery("from Skill", Skill.class).list();
        session.close();
        return skills;
    }

    @Override
    public void update(Skill skill) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(skill);
            transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;BEGIN TRANSACTION").executeUpdate();

        NativeQuery query1 = session.createNativeQuery("DELETE FROM SKILLS WHERE ID=:skillId");
        query1.setParameter("skillId", id);
        query1.executeUpdate();
        NativeQuery query2 = session.createNativeQuery("DELETE FROM SKILLS_DEVELOPERS WHERE SKILL_ID=:devSkillId");
        query2.setParameter("devSkillId", id);
        query2.executeUpdate();

        session.createNativeQuery("COMMIT;SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

        transaction.commit();
        session.clear();
        session.close();
    }


    @Override
    public long maxId() throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Query<Long> query = session.createQuery("SELECT MAX(id) from Skill as maxID", Long.class);
                Long singleResult = query.getSingleResult();
            transaction.commit();
        session.close();
        return singleResult;
    }
}
