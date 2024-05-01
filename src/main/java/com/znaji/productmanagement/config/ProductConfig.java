package com.znaji.productmanagement.config;

import com.znaji.productmanagement.dao.impl.DummyProductDoa;
import com.znaji.productmanagement.dao.impl.JDBCProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(JdbcProperties.class)
@PropertySource("classpath:jdbc.properties")
public class ProductConfig {

    private final JdbcProperties jdbcProperties;

    @Bean
    public DummyProductDoa dummyProductDoa() {
        return new DummyProductDoa();
    }

    @Bean
    public Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcProperties.getDriverClassName());
        return DriverManager.getConnection(jdbcProperties.getUrl(), jdbcProperties.getUsername(), jdbcProperties.getPassword());
    }
    @Bean
    public JDBCProductDao jdbcProductDao() {

        return new JDBCProductDao();
    }
}
