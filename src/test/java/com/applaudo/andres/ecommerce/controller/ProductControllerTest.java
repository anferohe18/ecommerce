package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Testing de product controller")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;

    private ProductDto productDto;
    private List<ProductDto> productDtoList;


    @BeforeEach
    void setUp() {
        productDto = ProductDto.builder()
                .name("coffee")
                .availableQuantity(20)
                .price(300.0)
                .build();

        productDtoList = Arrays.asList(productDto);
    }

    @Test
    void addProduct() throws Exception {
        when(productService.addProduct(any(ProductDto.class))).thenReturn(productDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/").content(new ObjectMapper().writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("coffee")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availableQuantity", is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(300.0)));


    }

    @Test
    void getProduct() throws Exception {
        when(productService.getProduct(anyInt())).thenReturn(productDto);
        mockMvc.perform((MockMvcRequestBuilders.get("/api/products/1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("coffee")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availableQuantity", is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(300.0)))
                .andExpect(status().isOk());
    }

    @Test
    void getProducts() throws Exception {
        when(productService.getProducts()).thenReturn(productDtoList);
        mockMvc.perform((MockMvcRequestBuilders.get("/api/products/")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("coffee")));
    }



    @Test
    void updateProduct() throws Exception {
        ProductDto productUpdated = new ProductDto(2,"water",10,300.0);
        when(productService.updateProduct(anyInt(),any(ProductDto.class))).thenReturn(productUpdated);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1").content(new ObjectMapper().writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("water")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availableQuantity", is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(300.0)));
    }

    @Test
    void deleteProduct() throws Exception {
        when(productService.deleteProduct(anyInt())).thenReturn(productDto);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}