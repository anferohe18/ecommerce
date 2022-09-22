package com.applaudo.andres.ecommerce.service.product;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.mapper.ProductMapper;
import com.applaudo.andres.ecommerce.repository.ProductRepository;
import com.applaudo.andres.ecommerce.service.address.AddressServiceImp;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        ProductEntity productEntity = productMapper.productDto2ProductEntity(productDto);
        ProductEntity productSaved = productRepository.save(productEntity);
        return productMapper.productEntity2ProductDto(productSaved);
    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            ProductEntity productEntity = optionalProduct.get();
            return productMapper.productEntity2ProductDto(productEntity);
        }
        return null;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productMapper.productEntityList2ProductDtoList(productEntities);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {

        Optional<ProductEntity> optionalUser = productRepository.findById(productId);
        if (optionalUser.isPresent()) {
            ProductEntity productFound = optionalUser.get();
            ProductDto productDtoFound = productMapper.productEntity2ProductDto(productFound);
            productDtoFound.setName(productDto.getName());
            productDtoFound.setPrice(productDto.getPrice());
            productDtoFound.setAvailableQuantity(productDto.getAvailableQuantity());
            productFound = productMapper.productDto2ProductEntity(productDtoFound);
            productRepository.save(productFound);
            productDtoFound = productMapper.productEntity2ProductDto(productFound);
            return productDtoFound;

        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public ProductDto deleteProduct(Integer productId) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
           ProductEntity productEntity = optionalProduct.get();
           ProductDto productDto = productMapper.productEntity2ProductDto(productEntity);
           productRepository.deleteById(productId);
           return productDto;
        }
        else{
            throw new EntityNotFoundException();
        }
    }
}
