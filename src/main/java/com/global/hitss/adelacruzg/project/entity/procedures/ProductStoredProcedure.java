package com.global.hitss.adelacruzg.project.entity.procedures;

import com.global.hitss.adelacruzg.project.entity.ProductTransaction;
import com.global.hitss.adelacruzg.project.model.Product;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductStoredProcedure extends StoredProcedure {
    private static final String PROCEDURE_NAME = "sp_insertAndListProducts";

    public ProductStoredProcedure(DataSource dataSource) {
        super(dataSource, PROCEDURE_NAME);
        declareParameter(new SqlParameter("p_id", Types.INTEGER));
        declareParameter(new SqlParameter("p_nombre", Types.VARCHAR));
        declareParameter(new SqlParameter("p_fecha", Types.DATE));
        declareParameter(new SqlParameter("p_operacion", Types.VARCHAR));
        declareParameter(new SqlOutParameter("p_resultado", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("p_mensaje", Types.VARCHAR));
        declareParameter(new SqlOutParameter("p_codigo", Types.INTEGER));
        compile();
    }

    public ProductTransaction executeProcedure(Product product) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_id", product.getId());
        inParams.put("p_nombre", product.getName());
        inParams.put("p_fecha", product.getDate());
        inParams.put("p_operacion", product.getOperation());
        Map<String, Object> out = execute(inParams);
        List<Map<String, Object>> products = (List<Map<String, Object>>) out.get("p_resultado");
        String message = (String) out.get("p_mensaje");
        Boolean success= ((Integer) out.get("p_codigo"))==0;
        return ProductTransaction.builder().success(success).message(message)
                .list(success?products.stream()
                        .map(this::mapToProduct)
                        .collect(Collectors.toList()):null).build();
    }
    private Product mapToProduct(Map<String, Object> row) {
        Product product = new Product();
        product.setId(((BigDecimal) row.get("id")).longValue());
        product.setName((String) row.get("nombre"));
        product.setDate((Date) row.get("fecharegistro"));
        return product;
    }
}
