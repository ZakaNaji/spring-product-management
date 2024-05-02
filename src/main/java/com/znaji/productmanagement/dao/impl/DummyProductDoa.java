package com.znaji.productmanagement.dao.impl;

import com.znaji.productmanagement.dao.ProductDao;
import com.znaji.productmanagement.entity.Product;
import com.znaji.productmanagement.exception.DaoException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyProductDoa implements ProductDao {
    @Override
    public void addProduct(final Product product) throws DaoException {

    }

    @Override
    public void updateProduct(Product product) throws DaoException {

    }

    @Override
    public void deleteProduct(Integer productId) throws DaoException {

    }

    @Override
    public Product getProduct(Integer productId) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsNotInStock() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsOnOrder() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getDiscontinuedProducts() throws DaoException {
        return null;
    }

    @Override
    public Integer count() {
        return 0;
    }
}
