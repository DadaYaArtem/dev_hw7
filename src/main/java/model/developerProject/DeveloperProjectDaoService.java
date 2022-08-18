package model.developerProject;

import model.projects.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeveloperProjectDaoService {
    private PreparedStatement getProjectsDevIsWorkingOn;
    private PreparedStatement insertIntoDevProj;
    private PreparedStatement updateDevProj;
    private PreparedStatement updateDevSkill;

    public DeveloperProjectDaoService(Connection connection) throws SQLException {
        getProjectsDevIsWorkingOn = connection.prepareStatement("SELECT * FROM DEVELOPERS_PROJECTS " +
                "JOIN DEVELOPERS ON DEVELOPERS_PROJECTS.DEVELOPER_ID = DEVELOPERS.ID " +
                "JOIN PROJECTS ON DEVELOPERS_PROJECTS.PROJECT_ID = PROJECTS.ID WHERE DEVELOPERS.ID  = ?");

        insertIntoDevProj = connection.prepareStatement("INSERT INTO DEVELOPERS_PROJECTS (DEVELOPER_ID, PROJECT_ID) VALUES (?, ?)");

        updateDevProj = connection.prepareStatement("UPDATE DEVELOPERS_PROJECTS SET project_id = ? WHERE developer_id = ?");

        updateDevSkill = connection.prepareStatement("UPDATE SKILLS_DEVELOPERS SET skill_id = ? WHERE developer_id = ?");
    }

    public List<Project> getAllProjects(long id) throws SQLException {
        getProjectsDevIsWorkingOn.setLong(1, id);
        try (ResultSet rs = getProjectsDevIsWorkingOn.executeQuery()) {
            List<Project> result = new ArrayList<>();

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("PROJECT_ID"));
                project.setName(rs.getString("name"));
                project.setCost(rs.getInt("cost"));
                project.setFoundation_date(LocalDate.parse(rs.getString("foundation_date")));

                result.add(project);
            }

            return result;
        }
    }

    public void insertIntoDevProj(long devId, long projId) throws SQLException {
        insertIntoDevProj.setLong(1, devId);
        insertIntoDevProj.setLong(2, projId);

        insertIntoDevProj.executeUpdate();
    }

    public void updateDevProj(long devId, long projId) throws SQLException {
        updateDevProj.setLong(1, projId);
        updateDevProj.setLong(2, devId);

        updateDevProj.executeUpdate();
    }

    public void updateDevSkill(long skillId, long devId) throws SQLException {
        updateDevProj.setLong(1, skillId);
        updateDevProj.setLong(2, devId);

        updateDevProj.executeUpdate();
    }
}
