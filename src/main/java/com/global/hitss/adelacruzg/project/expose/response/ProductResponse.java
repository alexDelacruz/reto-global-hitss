package com.global.hitss.adelacruzg.project.expose.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    boolean success;
    String message;
    List<ProductResponseDetail> list;
}
