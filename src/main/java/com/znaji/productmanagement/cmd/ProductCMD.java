package com.znaji.productmanagement.cmd;

import com.znaji.productmanagement.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


public class ProductCMD implements CommandLineRunner {

    final private ApplicationContext applicationContext;

    @Autowired
    public ProductCMD(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductDao productDao = applicationContext.getBean("jdbcProductDao", ProductDao.class);
        System.out.println("ProductCMD.run: " + productDao.count() + " products found");
        System.out.println("ProductCMD.run: " + productDao.count() + " products found");
    }
}
