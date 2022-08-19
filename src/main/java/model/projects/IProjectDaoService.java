package model.projects;

import java.sql.SQLException;
import java.util.List;

public interface IProjectDaoService {
    long create(Project project) throws SQLException;

    Project getById(long id) throws SQLException;

    List<Project> getAll();

    void update(Project project) throws SQLException;

    void deleteById(long id) throws SQLException;

    long maxId() throws SQLException;
}
