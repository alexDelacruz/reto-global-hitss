package com.global.hitss.adelacruzg.project.expose.controller;

import com.global.hitss.adelacruzg.project.common.ExceptionHitss;
import com.global.hitss.adelacruzg.project.common.UtilPath;
import com.global.hitss.adelacruzg.project.expose.request.ProductRequest;
import com.global.hitss.adelacruzg.project.expose.response.ProductResponse;
import com.global.hitss.adelacruzg.project.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({UtilPath.BASE_ENDPOINT_PRODUCT})
@Tag(name = "Producto", description = "Operaciones para producto")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping({UtilPath.PROCESS_PRODUCT})
    @Operation(summary = "Guardar y listar productoS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)),
                    }),
            @ApiResponse(responseCode = "400", description = "Parametros incorrectos",
                    content = {
                            @Content(mediaType = "application/json", schemaProperties = {
                               @SchemaProperty(name = "success",schema = @Schema(implementation = Boolean.class,defaultValue = "false")),
                                    @SchemaProperty(name = "message",schema = @Schema(implementation = String.class))
                            }),
                    }),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = {
                            @Content(mediaType = "application/json", schemaProperties = {
                                    @SchemaProperty(name = "success",schema = @Schema(implementation = Boolean.class,defaultValue = "false")),
                                    @SchemaProperty(name = "message",schema = @Schema(implementation = String.class))
                            }),
                    }),
    })
    public ResponseEntity<ProductResponse> select(@RequestBody ProductRequest request) {
        try {
            ProductResponse response = service.saveAndList(request);
            return ResponseEntity.ok(response);
        } catch (ExceptionHitss ex) {
            return ResponseEntity.badRequest().body(ProductResponse.builder().success(false).message(ex.getMessage()).build());
        } catch (Exception ex) {
            return (ResponseEntity<ProductResponse>) ResponseEntity.internalServerError();
        }
    }
}
