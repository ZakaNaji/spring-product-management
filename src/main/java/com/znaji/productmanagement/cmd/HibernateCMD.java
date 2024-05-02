package com.znaji.productmanagement.cmd;

import com.znaji.productmanagement.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HibernateCMD implements CommandLineRunner {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void run(String... args) throws Exception {

        final Session session = sessionFactory.openSession();
        System.out.println("category with id 1: " + session.get(Category.class, 1));

        Transaction transaction = session.beginTransaction();
        final Category category = Category.builder()
                .id(9)
                .name("new category")
                .description("new category description")
                .build();
        try {
            session.save(category);
            transaction.commit();
            System.out.println("category created: " + category);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("error: " + e.getMessage());
        }

        session.close();
    }
}
