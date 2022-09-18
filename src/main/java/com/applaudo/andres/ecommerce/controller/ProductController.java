package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto>getProduct(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @GetMapping("/")
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id")Integer id,@RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id,productDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.NO_CONTENT);
    }

}
