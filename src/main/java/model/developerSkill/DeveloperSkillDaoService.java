package model.developerSkill;

import model.skills.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperSkillDaoService {
    private PreparedStatement getSkillsDevHave;
    private PreparedStatement insertIntoDevSkill;

    public DeveloperSkillDaoService(Connection connection) throws SQLException {
        getSkillsDevHave = connection.prepareStatement("SELECT * FROM SKILLS_DEVELOPERS " +
                "JOIN DEVELOPERS ON SKILLS_DEVELOPERS.DEVELOPER_ID = DEVELOPERS.ID " +
                "JOIN SKILLS ON SKILLS_DEVELOPERS.SKILL_ID = SKILLS.ID where DEVELOPERS.id = ?");

        insertIntoDevSkill = connection.prepareStatement("INSERT INTO SKILLS_DEVELOPERS (SKILL_ID, DEVELOPER_ID) VALUES (?, ?)");
    }

    public List<Skill> getAllSkills(long id) throws SQLException {
        getSkillsDevHave.setLong(1, id);
        try (ResultSet rs = getSkillsDevHave.executeQuery()) {
            List<Skill> result = new ArrayList<>();

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getLong("SKILL_ID"));
                skill.setBranch((rs.getString("branch")));
                skill.setSkill(Skill.Skillset.valueOf(rs.getString("skill")));

                result.add(skill);
            }

            return result;
        }
    }

    public void insertIntoDevSkill(long devId, long skillId) throws SQLException {
        insertIntoDevSkill.setLong(1, skillId);
        insertIntoDevSkill.setLong(2, devId);

        insertIntoDevSkill.executeUpdate();
    }
}
