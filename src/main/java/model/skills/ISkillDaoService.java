package model.skills;

import java.sql.SQLException;
import java.util.List;

public interface ISkillDaoService {
    long create(Skill skill) throws SQLException;

    Skill getById(long id) throws SQLException;

    List<Skill> getAll();

    void update(Skill skill) throws SQLException;

    void deleteById(long id) throws SQLException;

    long maxId() throws SQLException;
}
