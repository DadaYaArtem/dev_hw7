package model.developerProject;

import model.developers.Developer;
import model.developers.HibernateDeveloperDaoService;
import model.projects.HibernateProjectDaoService;
import model.projects.Project;
import org.hibernate.Session;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateDevProjDaoService {
    HibernateDeveloperDaoService developerDaoService;
    HibernateProjectDaoService projectDaoService;

    public HibernateDevProjDaoService(){
        developerDaoService = new HibernateDeveloperDaoService();
        projectDaoService = new HibernateProjectDaoService();
    }

    public List<Project> getAllProjects(long id) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        return new ArrayList<>(developer.getProjects());
    }


}
