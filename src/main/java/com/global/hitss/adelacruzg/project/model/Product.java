package com.global.hitss.adelacruzg.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Long id;
    private String name;
    private Date date;
    private String operation;
    public Product(){}
    public Product(Long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
