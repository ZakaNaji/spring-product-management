package com.znaji.productmanagement.config;

import com.znaji.productmanagement.dao.impl.DummyProductDoa;
import com.znaji.productmanagement.dao.impl.JDBCProductDao;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(JdbcProperties.class)
@PropertySource("classpath:jdbc.properties")
public class GeneralConfig {

    private final JdbcProperties jdbcProperties;

    @Bean
    public DummyProductDoa dummyProductDoa() {
        return new DummyProductDoa();
    }

    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        return dataSource;

    }

     @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
