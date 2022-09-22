package com.applaudo.andres.ecommerce.service.checkout;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartItemDto;
import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.exceptions.CartItemNotExistException;
import com.applaudo.andres.ecommerce.mapper.AddressMapper;
import com.applaudo.andres.ecommerce.mapper.CheckoutMapper;
import com.applaudo.andres.ecommerce.mapper.ProductMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.CheckoutRepository;
import com.applaudo.andres.ecommerce.service.address.AddressService;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CheckoutServiceImp implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutMapper checkoutMapper;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final ProductService productService;
    private final AddressService addressService;
    private final UserMapper userMapper;

    @Override
    public CheckoutDto addToCart(AddToCartDto cartDto) {
        ProductDto product = productService.getProduct(cartDto.getProductId());
        UserDto user = userService.getUserFromToken();
        ProductEntity productEntity = productMapper.productDtoFull2ProductEntity((product));
        UserEntity userEntity = userMapper.userDtoFull2UserEntityFull(user);
        CheckoutEntity checkoutEntity = new CheckoutEntity(productEntity, cartDto.getQuantity(),userEntity);
        CheckoutEntity cartSaved = checkoutRepository.save(checkoutEntity);
        return checkoutMapper.cartEntity2CartDto(cartSaved);
    }

    @Override
    public CartDto listCartItems() {
        UserDto user = userService.getUserFromToken();
        UserEntity userEntity = userMapper.userDtoFull2UserEntityFull(user);
        List<CheckoutEntity> cartList = checkoutRepository.findAllByUser_email(userEntity.getEmail());
        List<CartItemDto> cartItems = new ArrayList<>();
        for(CheckoutEntity cart: cartList){
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
    public CheckoutDto updateCartItem(AddToCartDto cartDto) {
        CheckoutEntity cart = checkoutRepository.getReferenceById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setDate(new Date());
        return checkoutMapper.cartEntity2CartDto(checkoutRepository.save(cart));

    }

    @Override
    public CheckoutDto deleteCartItem(Integer itemId) {
        if(checkoutRepository.existsById(itemId)){
            CheckoutDto cart = checkoutMapper.cartEntity2CartDto(checkoutRepository.getReferenceById(itemId));
            checkoutRepository.deleteById(itemId);
            return cart;
        }
        throw new CartItemNotExistException("Cart Id is invalid: " + itemId);
    }

    @Override
    public List<CheckoutDto> setDeliveryAddress(String typeAddress) {
        UserDto user = userService.getUserFromToken();
        List<AddressDto> clientAddresses = addressService.findAddressByUser(user);
        String addressChosen = null;
        for(AddressDto addressDto: clientAddresses){
            if(addressDto.getType().equals(typeAddress)){
                addressChosen = addressDto.getAddress();
            }

        }
        List<CheckoutEntity> cartList = checkoutRepository.findAllByUser_email(user.getEmail());
        for(CheckoutEntity checkoutEntity : cartList){
            checkoutEntity.setDeliveryAddress(addressChosen);
            checkoutRepository.save(checkoutEntity);
        }
        return checkoutMapper.cartEntityList2CartDtoList(cartList);
    }


    @Override
    public CheckoutDto updateAddress(AddressDto address) {
        UserDto user = userService.getUserFromToken();
        AddressDto newAddress = addressService.addAddress(address, user);
        CheckoutEntity cart = checkoutRepository.findByUser_email(user.getEmail());
        cart.setDeliveryAddress(newAddress.getAddress());
        checkoutRepository.save(cart);
        return checkoutMapper.cartEntity2CartDto(cart);
    }

    @Override
    public List<CheckoutDto> deleteUserCartItems(UserDto user) {
        List<CheckoutEntity> cartEntities = checkoutRepository.deleteByUser_email(user.getEmail());
        return checkoutMapper.cartEntityList2CartDtoList(cartEntities);
    }

    public static CartItemDto getDtoFromCart(CheckoutEntity cart){
        return new CartItemDto(cart);
    }


}
