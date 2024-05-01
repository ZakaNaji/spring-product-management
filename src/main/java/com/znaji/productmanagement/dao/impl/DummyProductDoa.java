package com.znaji.productmanagement.dao.impl;

import com.znaji.productmanagement.dao.ProductDao;
import org.springframework.stereotype.Component;

@Component
public class DummyProductDoa implements ProductDao {
    @Override
    public int count() {
        return 0;
    }
}
