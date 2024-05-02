package com.znaji.productmanagement.dao;

import com.znaji.productmanagement.entity.Product;
import com.znaji.productmanagement.exception.DaoException;

import java.util.List;

public interface ProductDao {

    //CRUD operations
    void addProduct(final Product product) throws DaoException;

    void updateProduct(final Product product) throws DaoException;

    void deleteProduct(final Integer productId) throws DaoException;

    Product getProduct(final Integer productId) throws DaoException;

    //Query operations
    List<Product> getAllProducts() throws DaoException;

    List<Product> getProductsByCategory(Integer categoryId) throws DaoException;

    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) throws DaoException;

    List<Product> getProductsNotInStock() throws DaoException;

    List<Product> getProductsOnOrder() throws DaoException;

    List<Product> getDiscontinuedProducts() throws DaoException;

    Integer count();
}
