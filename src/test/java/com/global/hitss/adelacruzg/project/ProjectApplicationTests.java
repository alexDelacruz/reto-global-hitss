package com.global.hitss.adelacruzg.project;

import com.global.hitss.adelacruzg.project.common.Constants;
import com.global.hitss.adelacruzg.project.entity.ProductTransaction;
import com.global.hitss.adelacruzg.project.entity.procedures.ProductStoredProcedure;
import com.global.hitss.adelacruzg.project.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectApplicationTests {
	@Autowired
	ProductStoredProcedure procedure;
	@Test
	void listProduct() {
		Product product=new Product();
		product.setOperation(Constants.SELECT);
		ProductTransaction result= procedure.executeProcedure(product);
		assertNotNull(result,"El resultado no deberia ser nulo");
		assertTrue(result.getSuccess(),"La lista esta vacia");

	}
	@Test
	void saveProduct() {
		Product product=new Product();
		product.setOperation(Constants.INSERT);
		product.setName("COMPUTADORA");
		product.setDate(new Date());
		ProductTransaction result=procedure.executeProcedure(product);
		assertAll("producto",
				() -> assertNotNull(result, "El resultado no debería ser nulo"),
				() -> assertEquals(1, result.getList().size(), "El tamaño de la lista debería ser 1"),
				() -> {
					Product productoInsertado = result.getList().get(0);
					assertEquals(product.getName(), productoInsertado.getName(), "El nombre del producto insertado debería ser "+product.getName());
				}
		);
	}

	@Test
	void updateProduct() {
		Product product=new Product();
		product.setId(4L);
		product.setOperation(Constants.INSERT);
		product.setName("COMPUTADORA UPDATE");
		product.setDate(new Date());
		ProductTransaction result=procedure.executeProcedure(product);
		assertAll("producto",
				() -> assertNotNull(result, "El resultado no debería ser nulo"),
				() -> assertEquals(1, result.getList().size(), "El tamaño de la lista debería ser 1"),
				() -> {
					Product productoInsertado = result.getList().get(0);
					assertEquals(product.getName(), productoInsertado.getName(), "El nombre del producto insertado debería ser "+product.getName());
				}
		);
	}
}
