package model.companies;

import java.sql.SQLException;
import java.util.List;

public interface ICompanyDaoService {
    long create(Company company) throws SQLException;

    Company getById(long id) throws SQLException;

    List<Company> getAll();

    void update(Company developer) throws SQLException;

    void deleteById(long id) throws SQLException;

    long maxId() throws SQLException;
}
