package com.global.hitss.adelacruzg.project.service.impl;

import com.global.hitss.adelacruzg.project.common.Constants;
import com.global.hitss.adelacruzg.project.common.DateConverter;
import com.global.hitss.adelacruzg.project.common.ExceptionHitss;
import com.global.hitss.adelacruzg.project.dao.ProductDao;
import com.global.hitss.adelacruzg.project.entity.ProductTransaction;
import com.global.hitss.adelacruzg.project.expose.request.ProductRequest;
import com.global.hitss.adelacruzg.project.expose.response.ProductResponse;
import com.global.hitss.adelacruzg.project.expose.response.ProductResponseDetail;
import com.global.hitss.adelacruzg.project.model.Product;
import com.global.hitss.adelacruzg.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public ProductResponse saveAndList(ProductRequest request) throws ExceptionHitss {
        Product record = transformed(request);
        ProductTransaction result = productDao.saveAndInsert(record);
        if (!result.getSuccess()) {
            return ProductResponse.builder().success(false).message(result.getMessage()).build();
        } else {
            return ProductResponse.builder().success(true).message(result.getMessage()).list(
                    result.getList().stream()
                            .map(product -> new ProductResponseDetail(product.getId(), product.getName(), DateConverter.dateToString(product.getDate())))
                            .collect(Collectors.toList())
            ).build();
        }
    }

    private Product transformed(ProductRequest request) throws ExceptionHitss {
        if (request.getOperation() == null) {
            throw new ExceptionHitss("parameter 'operation' -> es requerido");
        }
        if (!Arrays.asList(Constants.TYPES_OPERATION).contains(request.getOperation())) {
            throw new ExceptionHitss("parameter 'operation'-> solo admite:" + Arrays.toString(Constants.TYPES_OPERATION));
        }
        if (Constants.INSERT.equalsIgnoreCase(request.getOperation())) {
            if (request.getName() == null || request.getName().isEmpty()) {
                throw new ExceptionHitss("parameter 'name' -> es requerido");
            } else if (request.getDate() == null) {
                throw new ExceptionHitss("parameter 'date' -> es requerido");
            } else if (DateConverter.stringToDate(request.getDate()) == null) {
                throw new ExceptionHitss("parameter 'date' -> formato incorrecto - requiere " + DateConverter.DATE_FORMAT_REQUEST);
            }
        }
        Product record = new Product();
        record.setId(record.getId());
        record.setName(request.getName());
        record.setDate(DateConverter.stringToDate(request.getDate()));
        record.setOperation(request.getOperation());
        return record;
    }
}
