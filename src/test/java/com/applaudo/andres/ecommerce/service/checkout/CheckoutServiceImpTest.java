package com.applaudo.andres.ecommerce.service.checkout;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.PaymentDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartItemDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.*;
import com.applaudo.andres.ecommerce.exceptions.CartItemNotExistException;
import com.applaudo.andres.ecommerce.mapper.CheckoutMapper;
import com.applaudo.andres.ecommerce.mapper.ProductMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.CheckoutRepository;
import com.applaudo.andres.ecommerce.service.address.AddressService;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the Checkout service")
class CheckoutServiceImpTest {
    @Mock
    private CheckoutRepository checkoutRepository;
    @Mock
    private CheckoutMapper checkoutMapper;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private AddressService addressService;
    @Mock
    private UserMapper userMapper;
    private CheckoutServiceImp checkoutServiceImp;

    private UserDto userDto;
    private UserEntity userEntity;
    private PaymentMethod paymentMethod;
    private PaymentDto paymentDto;
    private AddressEntity addressEntity;
    private AddressDto addressDto;
    private List<AddressDto> addressDtoList;
    private List<AddressEntity> addressEntityList;
    private ProductDto productDto;
    private ProductEntity productEntity;
    private CheckoutEntity checkoutEntity;
    private List<CheckoutEntity> checkoutEntityList;
    private List<CheckoutDto> checkoutDtoList;
    private CheckoutDto checkoutDto;
    private CartDto cartDto;
    private CartItemDto cartItemDto;
    private List<CartItemDto> cartItemDtoList;
    private AddToCartDto addToCartDto;

    @BeforeEach
    void setUp() {
        checkoutServiceImp = new CheckoutServiceImp(checkoutRepository,checkoutMapper,productMapper,userService, productService,addressService, userMapper);
        userDto = new UserDto("Andres","Rodriguez", "andresr427@gmail.com","12345","3123454321",null);
        userEntity = new UserEntity(1,"Andres","Rodriguez", "andresr427@gmail.com","12345","3123454321",null);
        addressEntity = new AddressEntity(1,"house", "calle 17 12-61", userEntity);
        addressDto = new AddressDto(1,"house", "calle 17 12-61", userEntity);
        addressDtoList = List.of(addressDto);
        addressEntityList = List.of(addressEntity);
        paymentMethod = new PaymentMethod(1, "cash");
        paymentDto = new PaymentDto(1, "cash");
        productDto = new ProductDto(1,"coffee", 10,1000.0);
        productEntity = new ProductEntity(1,"coffee", 10,1000.0);
        checkoutEntity = new CheckoutEntity(productEntity,5,userEntity);
        checkoutDto = new CheckoutDto(productEntity,5,userEntity);
        checkoutEntityList = Arrays.asList(checkoutEntity);
        checkoutDtoList = Arrays.asList(checkoutDto);
        cartItemDto = new CartItemDto(checkoutEntity);
        cartItemDtoList = Arrays.asList(cartItemDto);
        addToCartDto = new AddToCartDto(productDto.getId(), productDto.getId(), 5);
        cartDto = new CartDto(cartItemDtoList, 5000.0);

    }

    @Test
    void addToCart() {
        when(productService.getProduct(anyInt())).thenReturn(productDto);
        when(userService.getUserFromToken()).thenReturn(userDto);
        when(productMapper.productDtoFull2ProductEntity(productDto)).thenReturn(productEntity);
        when(userMapper.userDtoFull2UserEntityFull(userDto)).thenReturn(userEntity);
        when(checkoutRepository.save(any(CheckoutEntity.class))).thenReturn(checkoutEntity);
        when(checkoutMapper.cartEntity2CartDto(any(CheckoutEntity.class))).thenReturn(checkoutDto);

        CheckoutDto cartAdded = checkoutServiceImp.addToCart(addToCartDto);
        assertThat(cartAdded, is(equalTo(checkoutDto)));
        verify(checkoutRepository, times(1)).save(any(CheckoutEntity.class));

    }

    @Test
    void listCartItems() {
        when(userService.getUserFromToken()).thenReturn(userDto);
        when(userMapper.userDtoFull2UserEntityFull(any(UserDto.class))).thenReturn(userEntity);
        when(checkoutRepository.findAllByUser_email(anyString())).thenReturn(checkoutEntityList);

        CartDto cartDtoFound = checkoutServiceImp.listCartItems();
        assertThat(cartDtoFound, is(equalTo(cartDto)));
        verify(checkoutRepository, times(1)).findAllByUser_email(anyString());
    }

    @Test
    void updateCartItem() {
        when(checkoutRepository.getReferenceById(anyInt())).thenReturn(checkoutEntity);
        when(checkoutRepository.save(any(CheckoutEntity.class))).thenReturn(checkoutEntity);
        when(checkoutMapper.cartEntity2CartDto(any(CheckoutEntity.class))).thenReturn(checkoutDto);

        CheckoutDto checkoutDtoExpected = checkoutServiceImp.updateCartItem(addToCartDto);
        assertThat(checkoutDtoExpected, is(equalTo(checkoutDto)));
        verify(checkoutRepository, times(1)).save(any(CheckoutEntity.class));

    }

    @Test
    void deleteCartItem() {
        when(checkoutRepository.existsById(anyInt())).thenReturn(true);
        when(checkoutRepository.getReferenceById(anyInt())).thenReturn(checkoutEntity);
        when(checkoutMapper.cartEntity2CartDto(any(CheckoutEntity.class))).thenReturn(checkoutDto);
        doNothing().when(checkoutRepository).deleteById(anyInt());

        CheckoutDto checkoutDeleted = checkoutServiceImp.deleteCartItem(1);
        assertThat(checkoutDeleted, is(equalTo(checkoutDto)));
        verify(checkoutRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void setDeliveryAddress() {
        when(userService.getUserFromToken()).thenReturn(userDto);
        when(addressService.findAddressByUser(any(UserDto.class))).thenReturn(addressDtoList);
        when(checkoutRepository.findAllByUser_email(anyString())).thenReturn(checkoutEntityList);
        when(checkoutRepository.save(any(CheckoutEntity.class))).thenReturn(checkoutEntity);
        when(checkoutMapper.cartEntityList2CartDtoList(anyList())).thenReturn(checkoutDtoList);

        List<CheckoutDto> checkoutListModified = checkoutServiceImp.setDeliveryAddress(addressDto.getAddress());
        assertThat(checkoutListModified, is(equalTo(checkoutDtoList)));
        verify(checkoutRepository, times(1)).save(any(CheckoutEntity.class));
    }

    @Test
    void updateAddress() {
        AddressDto newAddressDto = new AddressDto(1,"office","calle 5 5-55",userEntity);
        CheckoutEntity checkoutExpected = new CheckoutEntity(1,userEntity, productEntity,5,new Date(),newAddressDto.getAddress());
        CheckoutDto checkoutDtoExpected = new CheckoutDto(1, userEntity, productEntity,5,checkoutExpected.getDate(),newAddressDto.getAddress());

        when(userService.getUserFromToken()).thenReturn(userDto);
        when(addressService.addAddress(any(AddressDto.class),any(UserDto.class))).thenReturn(addressDto);
        when(checkoutRepository.findByUser_email(anyString())).thenReturn(checkoutEntity);
        when(checkoutRepository.save(any(CheckoutEntity.class))).thenReturn(checkoutExpected);
        when(checkoutMapper.cartEntity2CartDto(any(CheckoutEntity.class))).thenReturn(checkoutDtoExpected);

        CheckoutDto checkoutUpdated = checkoutServiceImp.updateAddress(newAddressDto);
        assertThat(checkoutUpdated,is(equalTo(checkoutDtoExpected)));
        verify(checkoutRepository, times(1)).save(any(CheckoutEntity.class));
    }

    @Test
    void deleteUserCartItems() {
        when(checkoutRepository.deleteByUser_email(anyString())).thenReturn(checkoutEntityList);
        when(checkoutMapper.cartEntityList2CartDtoList(anyList())).thenReturn(checkoutDtoList);

        List<CheckoutDto> cartItemsDtoDeleted = checkoutServiceImp.deleteUserCartItems(userDto);
        assertThat(cartItemsDtoDeleted, is(equalTo(checkoutDtoList)));
    }

    @Test

    void shouldThrow_CartItemNotFoundException_WhenCartNotFound() {
        when(checkoutRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(CartItemNotExistException.class, () -> {
            checkoutServiceImp.deleteCartItem(anyInt());
        });
        verify(checkoutRepository, never()).deleteByUser_email(anyString());

    }
}