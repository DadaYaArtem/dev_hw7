package model.developerSkill;

import model.developers.Developer;
import model.developers.HibernateDeveloperDaoService;
import model.skills.HibernateSkillDaoService;
import model.skills.Skill;
import org.hibernate.Session;
import storage.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class HibernateDevSkillDaoService {
    HibernateDeveloperDaoService developerDaoService;
    HibernateSkillDaoService hibernateSkillDaoService;

    public HibernateDevSkillDaoService(){
        developerDaoService = new HibernateDeveloperDaoService();
        hibernateSkillDaoService = new HibernateSkillDaoService();
    }

    public List<Skill> getAllSkills(long id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        return new ArrayList<>(developer.getSkills());
    }
}
