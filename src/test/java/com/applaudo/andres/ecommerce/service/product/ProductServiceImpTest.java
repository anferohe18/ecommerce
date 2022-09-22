package com.applaudo.andres.ecommerce.service.product;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.mapper.ProductMapper;
import com.applaudo.andres.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the Product service")
class ProductServiceImpTest {

    @Mock
    private  ProductRepository productRepository;
    @Mock
    private  ProductMapper productMapper;

    private ProductServiceImp productServiceImp;
    private ProductEntity productEntity;
    private ProductDto productDto;

    @BeforeEach
    void setUp(){
        productDto= ProductDto.builder()
                .id(1)
                .name("coffee")
                .availableQuantity(15)
                .price(1500.0)
                .build();
        productEntity = ProductEntity.builder()
                .id(1)
                .name("coffee")
                .availableQuantity(15)
                .price(1500.0)
                .build();

        productServiceImp = new ProductServiceImp(productRepository, productMapper);
    }

    @Test
    void addProduct() {
        when(productMapper.productDto2ProductEntity(any(ProductDto.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.productEntity2ProductDto(any(ProductEntity.class))).thenReturn(productDto);

        ProductDto productSaved = productServiceImp.addProduct(productDto);
        assertThat(productSaved, is(equalTo(productDto)));
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void getProduct() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(productEntity));
        when(productMapper.productEntity2ProductDto(any(ProductEntity.class))).thenReturn(productDto);

        ProductDto productSaved = productServiceImp.getProduct(productDto.getId());
        assertThat(productSaved, is(equalTo(productDto)));
        verify(productRepository, times(1)).findById(anyInt());
    }

    @Test
    void getProducts() {
        List<ProductDto> productDtoList = Arrays.asList(productDto);
        List<ProductEntity> productEntityList = Arrays.asList(productEntity);

        when(productRepository.findAll()).thenReturn(productEntityList);
        when(productMapper.productEntityList2ProductDtoList(productEntityList)).thenReturn(productDtoList);

        List<ProductDto> productDtoListSaved = productServiceImp.getProducts();
        assertThat(productDtoListSaved, is(equalTo(productDtoList)));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void updateProduct() {
        ProductDto productUpdated = ProductDto.builder()
                .id(1)
                .name("coffee")
                .availableQuantity(30)
                .price(1600.0)
                .build();

        ProductEntity productEntityUpdated = ProductEntity.builder()
                .id(1)
                .name("coffee")
                .availableQuantity(30)
                .price(1600.0)
                .build();

        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(productEntity));
        when(productMapper.productEntity2ProductDto(any(ProductEntity.class))).thenReturn(productDto);
        when(productMapper.productDto2ProductEntity(any(ProductDto.class))).thenReturn(productEntityUpdated);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntityUpdated);
        when(productMapper.productEntity2ProductDto(productEntityUpdated)).thenReturn(productUpdated);

        ProductDto productSaved = productServiceImp.updateProduct(productDto.getId(),productDto);
        assertThat(productSaved, is(equalTo(productUpdated)));
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void deleteProduct() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(productEntity));
        when(productMapper.productEntity2ProductDto(any(ProductEntity.class))).thenReturn(productDto);
        doNothing().when(productRepository).deleteById(anyInt());

        ProductDto productDeleted = productServiceImp.deleteProduct(productDto.getId());
        assertThat(productDeleted, is(equalTo(productDto)));
        verify(productRepository, times(1)).deleteById(productDto.getId());
    }
}