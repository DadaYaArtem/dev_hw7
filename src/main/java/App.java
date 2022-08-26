import model.customers.HibernateCustomerDaoService;
import model.developerProject.HibernateDevProjDaoService;
import model.developerSkill.HibernateDevSkillDaoService;
import model.developers.HibernateDeveloperDaoService;
import model.projects.HibernateProjectDaoService;
import model.skills.HibernateSkillDaoService;

import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException{


        HibernateProjectDaoService hProjService = new HibernateProjectDaoService();
        HibernateDevSkillDaoService hDevSkillService = new HibernateDevSkillDaoService();
        HibernateDevProjDaoService hDevProjService = new HibernateDevProjDaoService();
        HibernateDeveloperDaoService hDevDaoService = new HibernateDeveloperDaoService();
        HibernateCustomerDaoService hCusDaoService = new HibernateCustomerDaoService();
        HibernateSkillDaoService hSkillService = new HibernateSkillDaoService();

//        System.out.println(hDevProjService.getAllProjects(1));

    }
}
