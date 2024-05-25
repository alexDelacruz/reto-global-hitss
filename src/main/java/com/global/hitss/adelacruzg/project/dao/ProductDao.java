package com.global.hitss.adelacruzg.project.dao;

import com.global.hitss.adelacruzg.project.entity.ProductTransaction;
import com.global.hitss.adelacruzg.project.model.Product;

public interface ProductDao {
    ProductTransaction saveAndInsert(Product product);
}
