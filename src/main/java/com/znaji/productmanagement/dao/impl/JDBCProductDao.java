package com.znaji.productmanagement.dao.impl;

import com.znaji.productmanagement.dao.ProductDao;
import com.znaji.productmanagement.entity.Product;
import com.znaji.productmanagement.exception.DaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository("jdbcProductDao")
public class JDBCProductDao implements ProductDao {


    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> Product.builder()
                .id(rs.getInt("product_id"))
                .name(rs.getString("product_name"))
                .supplierId(rs.getInt("supplier_id"))
                .categoryId(rs.getInt("category_id"))
                .quantityPerUnit(rs.getString("quantity_per_unit"))
                .unitPrice(rs.getDouble("unit_price"))
                .unitsInStock(rs.getInt("units_in_stock"))
                .unitsOnOrder(rs.getInt("units_on_order"))
                .reorderLevel(rs.getInt("reorder_level"))
                .discontinued(rs.getInt("discontinued"))
                .build();


    @Override
    public void addProduct(final Product product) throws DaoException {
        final String sql = "INSERT INTO products (product_name, supplier_id, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level, discontinued) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getSupplierId(), product.getCategoryId(), product.getQuantityPerUnit(), product.getUnitPrice(), product.getUnitsInStock(), product.getUnitsOnOrder(), product.getReorderLevel(), product.getDiscontinued());
    }

    @Override
    public void updateProduct(Product product) throws DaoException {
        final String sql = "UPDATE products SET product_name = ?, supplier_id = ?, category_id = ?, quantity_per_unit = ?, unit_price = ?, units_in_stock = ?, units_on_order = ?, reorder_level = ?, discontinued = ? WHERE product_id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getSupplierId(), product.getCategoryId(), product.getQuantityPerUnit(), product.getUnitPrice(), product.getUnitsInStock(), product.getUnitsOnOrder(), product.getReorderLevel(), product.getDiscontinued(), product.getId());
    }

    @Override
    public void deleteProduct(Integer productId) throws DaoException {
        //this is a soft delete: we just mark the product as discontinued
        final String sql = "UPDATE products SET discontinued = 1 WHERE product_id = ?";
    }

    @Override
    public Product getProduct(Integer productId) throws DaoException {
        final String query = "SELECT * FROM products WHERE product_id = ?";
        return jdbcTemplate.queryForObject(query, productRowMapper, productId);
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        final String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
        final String query = "SELECT * FROM products WHERE category_id = ?";
        return jdbcTemplate.query(query, productRowMapper, categoryId);
    }

    @Override
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) throws DaoException {
        final String query = "SELECT * FROM products WHERE unit_price BETWEEN ? AND ?";
        return jdbcTemplate.query(query, productRowMapper, minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsNotInStock() throws DaoException {
        final String query = "SELECT * FROM products WHERE units_in_stock = 0";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public List<Product> getProductsOnOrder() throws DaoException {
        final String query = "SELECT * FROM products WHERE units_on_order > 0";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public List<Product> getDiscontinuedProducts() throws DaoException {
        final String query = "SELECT * FROM products WHERE discontinued = 1";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public Integer count() {
        final String query = "SELECT COUNT(*) FROM products";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
