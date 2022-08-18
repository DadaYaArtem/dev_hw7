package model.developers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoService implements IDeveloperDaoService{
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;

    private PreparedStatement selectMaxIdSt;
    private PreparedStatement getAllSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement clearSt;


    public DeveloperDaoService(Connection connection) throws SQLException {
        getAllSt = connection.prepareStatement("SELECT * FROM developers");
        getByIdSt = connection.prepareStatement("SELECT * FROM developers WHERE id = ?");
        selectMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxID FROM developers");
        updateSt = connection.prepareStatement("UPDATE developers SET first_name =?, last_name=?, age=?, salary=? WHERE id=?");
        createSt = connection.prepareStatement("INSERT INTO developers (first_name, last_name, age, salary) VALUES (?, ?, ?, ?)");
        clearSt = connection.prepareStatement("DELETE FROM developers");

        deleteByIdSt = connection.prepareStatement(
                "SET REFERENTIAL_INTEGRITY FALSE;\n" +
                        "BEGIN TRANSACTION;\n" +
                        "DELETE FROM DEVELOPERS WHERE ID = ?;\n" +
                        "DELETE FROM DEVELOPERS_PROJECTS WHERE DEVELOPER_ID = ?;" +
                        "DELETE FROM SKILLS_DEVELOPERS WHERE DEVELOPER_ID = ?;" +
                        "COMMIT;\n" +
                        "SET REFERENTIAL_INTEGRITY TRUE;"
        );
    }

    public long create(Developer developer) throws SQLException {
        createSt.setString(1, developer.getFirst_name());
        createSt.setString(2, developer.getLast_name());
        createSt.setInt(3, developer.getAge());
        createSt.setInt(4, developer.getSalary());
        createSt.executeUpdate();

        long id;

        try (ResultSet resultSet = selectMaxIdSt.executeQuery();) {
            resultSet.next();
            id = resultSet.getLong("maxId");
        }

        return id;
    }

    public Developer getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);
        try (ResultSet resultSet = getByIdSt.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }
            Developer result = new Developer();
            result.setId(id);
            result.setFirst_name(resultSet.getString("first_name"));
            result.setLast_name(resultSet.getString("last_name"));
            result.setAge(resultSet.getInt("age"));
            result.setSalary(resultSet.getInt("salary"));
            return result;
        }
    }

    public List<Developer> getAll() {
        try (ResultSet rs = getAllSt.executeQuery()) {
            List<Developer> result = new ArrayList<>();

            while (rs.next()) {
                Developer developer = new Developer();
                developer.setId(rs.getLong("id"));
                developer.setFirst_name(rs.getString("first_name"));
                developer.setLast_name(rs.getString("last_name"));
                developer.setAge(rs.getInt("age"));
                developer.setSalary(rs.getInt("salary"));

                result.add(developer);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Developer developer) throws SQLException {
        updateSt.setString(1, developer.getFirst_name());
        updateSt.setString(2, developer.getLast_name());
        updateSt.setInt(3, developer.getAge());
        updateSt.setInt(4, developer.getSalary());
        updateSt.setLong(5, developer.getId());

        updateSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);
        deleteByIdSt.setLong(2, id);
        deleteByIdSt.setLong(3, id);
        deleteByIdSt.executeUpdate();
    }

    public void clear() throws SQLException {
        clearSt.executeUpdate();
    }

    public long maxId() throws SQLException {
        try (ResultSet resultSet = selectMaxIdSt.executeQuery();) {
            resultSet.next();
            return resultSet.getLong("maxId");
        }
    }
}