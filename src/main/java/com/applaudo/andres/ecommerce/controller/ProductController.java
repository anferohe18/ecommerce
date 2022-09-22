package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Allows you to create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content)})
    @PostMapping("/")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Allows you to get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to get the products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Products not found",
                    content = @Content)})
    @GetMapping("/")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }


    @Operation(summary = "Allows you to update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id") Integer id, @RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product was deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @DeleteMapping("{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.NO_CONTENT);
    }

}
