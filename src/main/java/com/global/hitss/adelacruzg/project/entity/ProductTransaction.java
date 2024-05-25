package com.global.hitss.adelacruzg.project.entity;

import com.global.hitss.adelacruzg.project.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductTransaction {
    private Boolean success;
    private String message;
    private Product record;
    private List<Product> list;
}
