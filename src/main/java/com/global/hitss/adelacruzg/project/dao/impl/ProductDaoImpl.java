package com.global.hitss.adelacruzg.project.dao.impl;

import com.global.hitss.adelacruzg.project.connection.Connection;
import com.global.hitss.adelacruzg.project.dao.ProductDao;
import com.global.hitss.adelacruzg.project.entity.ProductTransaction;
import com.global.hitss.adelacruzg.project.entity.procedures.ProductStoredProcedure;
import com.global.hitss.adelacruzg.project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDaoImpl extends Connection implements ProductDao {
    @Autowired
    ProductStoredProcedure procedure;
    @Override
    public ProductTransaction saveAndInsert(Product product) {
        return procedure.executeProcedure(product);
    }
}
