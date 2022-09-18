package com.applaudo.andres.ecommerce.service.cart;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.cartDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto2;
import com.applaudo.andres.ecommerce.dto.cartDto.CartItemDto;
import com.applaudo.andres.ecommerce.entity.CartEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.exceptions.CartItemNotExistException;
import com.applaudo.andres.ecommerce.mapper.CartMapper;
import com.applaudo.andres.ecommerce.mapper.ProductMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.CartRepository;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImp implements CartService{

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final ProductService productService;

    private final UserMapper userMapper;

    @Override
    public CartDto2 addToCart(AddToCartDto cartDto, Integer id) {
        ProductDto product = productService.getProduct(cartDto.getProductId());
        UserDto user = userService.findUserById(id);
        ProductEntity productEntity = productMapper.productDtoFull2ProductEntity((product));
        UserEntity userEntity = userMapper.userDtoFull2UserEntityFull(user);
        CartEntity cartEntity = new CartEntity(productEntity, cartDto.getQuantity(),userEntity);
        CartEntity cartSaved = cartRepository.save(cartEntity);
        return cartMapper.cartEntity2CartDto(cartSaved);
    }

    @Override
    public CartDto listCartItems(UserDto user) {
        UserEntity userEntity = userMapper.userDtoFull2UserEntityFull(user);
        List<CartEntity> cartList = cartRepository.findByUser_email(userEntity.getEmail());
        List<CartItemDto> cartItems = new ArrayList<>();
        for(CartEntity cart: cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for(CartItemDto cartItemDto: cartItems){
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    @Override
    public CartDto2 updateCartItem(AddToCartDto cartDto, UserDto user, ProductDto product) {
        CartEntity cart = cartRepository.getReferenceById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setDate(new Date());
        return cartMapper.cartEntity2CartDto(cartRepository.save(cart));
    }


    @Override
    public CartDto2 deleteCartItems(Integer userId) {
      cartRepository.deleteAll();
      return null;
    }

    @Override
    public CartDto2 deleteCartItem(Integer id) {
        if(cartRepository.existsById(id)){
            CartDto2 car = cartMapper.cartEntity2CartDto(cartRepository.getReferenceById(id));
            cartRepository.deleteById(id);
            return car;
        }
        throw new CartItemNotExistException("Cart Id is invalid: " + id);
    }

    @Override
    public List<CartDto2> deleteUserCartItems(UserDto user) {
        List<CartEntity> cartEntities = cartRepository.deleteByUser_email(user.getEmail());
        return cartMapper.cartEntityList2CartDtoList(cartEntities);
    }

    public static CartItemDto getDtoFromCart(CartEntity cart){
        return new CartItemDto(cart);
    }
}
