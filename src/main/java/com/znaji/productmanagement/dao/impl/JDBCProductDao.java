package com.znaji.productmanagement.dao.impl;

import com.znaji.productmanagement.dao.ProductDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@NoArgsConstructor
@Getter
@Setter
public class JDBCProductDao implements ProductDao {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    @Autowired

    private Connection connection;

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }
    @Override
    public int count() {
        try(Connection connection = createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
