package com.znaji.productmanagement.cmd;

import com.znaji.productmanagement.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class JdbcTemplateCMD implements CommandLineRunner {

    public final JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        //insertShippers();
        //updateShipperPhone(4, "(123) 456-789");
        //printProductsCount();
        //printShipperName(4);
        //printProductDetails(1);
        //printAllShippers();
        //printAllShippersNames();
        getCategoryUsingRowMapper(1);
    }

    private void getCategoryUsingRowMapper(int id) {
        final String query = "SELECT * FROM categories WHERE category_id = ?";
        final RowMapper<Category> rowMapper = (rs, rowNum) -> {
            final Category category = new Category();
            category.setId(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("description"));
            category.setPicture(rs.getBytes("picture"));
            return category;
    };
        final Category category = jdbcTemplate.queryForObject(query, rowMapper, id);
        System.out.println(category);
    }

    private void printAllShippersNames() {
        jdbcTemplate.queryForList("SELECT company_name FROM shippers", String.class)
                .forEach(System.out::println);
    }

    private void printAllShippers() {
        jdbcTemplate.queryForList("SELECT * FROM shippers")
                .forEach(System.out::println);
    }

    private void printProductDetails(int id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        jdbcTemplate.queryForMap(query, id)
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private void printShipperName(int id) {
        String name = jdbcTemplate.queryForObject("select company_name from shippers where shipper_id = ?", String.class, id);
        System.out.println("Shipper name: " + name);
    }

    private void printProductsCount() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products", Integer.class);
        System.out.println("Products count: " + count);
    }

    private void updateShipperPhone(int i, String phone) {
        jdbcTemplate.update("UPDATE shippers SET phone = ? WHERE shipper_id = ?", phone, i);
        System.out.println("Shipper phone updated");
    }

    private void insertShippers() {
        jdbcTemplate.update("INSERT INTO shippers (shipper_id, company_name, phone) VALUES (?, ?, ?)", 4, "DHL", "123456789");
        jdbcTemplate.update("INSERT INTO shippers (shipper_id, company_name, phone) VALUES (?, ?, ?)", 5, "FedEx", "987654321");
        jdbcTemplate.update("INSERT INTO shippers (shipper_id, company_name, phone) VALUES (?, ?, ?)", 6, "UPS", "123123123");
        System.out.println("Shippers inserted");
    }
}
