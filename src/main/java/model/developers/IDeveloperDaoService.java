package model.developers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDeveloperDaoService {
    long create(Developer developer) throws SQLException;

    Developer getById(long id) throws SQLException;

    List<Developer> getAll();

    void update(Developer developer) throws SQLException;

    void deleteById(long id) throws SQLException;

    long maxId() throws SQLException;
}
