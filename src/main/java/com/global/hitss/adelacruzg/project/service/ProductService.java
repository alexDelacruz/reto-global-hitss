package com.global.hitss.adelacruzg.project.service;

import com.global.hitss.adelacruzg.project.expose.request.ProductRequest;
import com.global.hitss.adelacruzg.project.expose.response.ProductResponse;

public interface ProductService {
    ProductResponse saveAndList(ProductRequest request);
}
