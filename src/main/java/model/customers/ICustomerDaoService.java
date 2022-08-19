package model.customers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ICustomerDaoService {
    long create(Customer customer) throws SQLException;

    Customer getById(long id) throws SQLException;

    List<Customer> getAll() throws SQLException;

    void update(Customer customer) throws SQLException;

    void deleteById(long id) throws SQLException;

    long maxId() throws SQLException;
}
