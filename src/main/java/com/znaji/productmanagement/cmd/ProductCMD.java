package com.znaji.productmanagement.cmd;

import com.znaji.productmanagement.dao.ProductDao;
import com.znaji.productmanagement.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductCMD implements CommandLineRunner {

    final private ApplicationContext applicationContext;

    @Autowired
    public ProductCMD(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductDao productDao = applicationContext.getBean("jdbcProductDao", ProductDao.class);
        Product product = productDao.getProduct(1);
        System.out.println(product);
        product.setUnitPrice(20.0);
        productDao.updateProduct(product);
        System.out.println("Product updated successfully!");

        List<Product> productsList = productDao.getProductsByPriceRange(50.0, 200.0);
        System.out.println("Products with price between 10 and 20: " + productsList.size());

        productsList = productDao.getDiscontinuedProducts();
        System.out.println("Discontinued products: " + productsList.size());
    }
}
