package ru.h2o.shop.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.h2o.shop.entity.Desktop;
import ru.h2o.shop.entity.Product;
import ru.h2o.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void addProductTest() throws Exception {
        Desktop product = new Desktop();

        product.setId(1);
        product.setPrice(BigDecimal.valueOf(1000));
        product.setManufacturer("Test");
        product.setSerialNumber("12345");
        product.setStockQuantity(10);
        product.setFormFactor("Test form factor");

        JSONObject productJson = new JSONObject();
        productJson.put("id", 1);
        productJson.put("manufacturer", "Test");
        productJson.put("serialNumber", "12345");
        productJson.put("stockQuantity", 10);
        productJson.put("formFactor", "Test form factor");
        productJson.put("price", 1000);
        productJson.put("type", "desktop");

        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/add")
                    .content(productJson.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void updateProductTest() throws Exception {
        Desktop product = new Desktop();
        product.setId(1);
        product.setPrice(BigDecimal.valueOf(1000));
        product.setManufacturer("Test");
        product.setSerialNumber("12345");
        product.setStockQuantity(10);
        product.setFormFactor("Test form factor");

        JSONObject productJson = new JSONObject();
        productJson.put("id", 1);
        productJson.put("manufacturer", "Test");
        productJson.put("serialNumber", "12345");
        productJson.put("stockQuantity", 10);
        productJson.put("formFactor", "Test form factor");
        productJson.put("price", 1000);
        productJson.put("type", "desktop");

        when(productService.update(1L, product)).thenReturn(new Desktop());

        mockMvc.perform(patch("/product/1")
                        .content(productJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).update(1L, product);
    }

    @Test
    void getProductTest() throws Exception {
        when(productService.findById(1L)).thenReturn(new Desktop());

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());

        verify(productService, times(1)).findById(any(Long.class));
    }

    @Test
    void getProductsByTypeTest() throws Exception {
        when(productService.findAllByType("desktop")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/product/type/desktop"))
                .andExpect(status().isOk());

        verify(productService, times(1)).findAllByType(any(String.class));
    }
}
