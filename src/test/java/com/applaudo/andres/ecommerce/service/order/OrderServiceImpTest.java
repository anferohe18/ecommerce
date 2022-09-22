package com.applaudo.andres.ecommerce.service.order;

import com.applaudo.andres.ecommerce.dto.PaymentDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartItemDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.*;
import com.applaudo.andres.ecommerce.exceptions.OrderNotFoundException;
import com.applaudo.andres.ecommerce.mapper.OrderMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.CheckoutRepository;
import com.applaudo.andres.ecommerce.repository.OrderItemRepository;
import com.applaudo.andres.ecommerce.repository.OrderRepository;
import com.applaudo.andres.ecommerce.service.address.AddressService;
import com.applaudo.andres.ecommerce.service.checkout.CheckoutService;
import com.applaudo.andres.ecommerce.service.payment.PaymentService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the Order service")
class OrderServiceImpTest {

    @Mock
    private  OrderRepository orderRepository;
    @Mock
    private  OrderItemRepository orderItemRepository;
    @Mock
    private  OrderMapper orderMapper;
    @Mock
    private  CheckoutService checkoutService;
    @Mock
    private  UserMapper userMapper;
    @Mock
    private  UserService userService;
    @Mock
    private  AddressService addressService;
    @Mock
    private  CheckoutRepository checkoutRepository;
    @Mock
    private  PaymentService paymentService;

    private OrderServiceImp orderServiceImp;
    private UserDto userDto;
    private UserEntity userEntity;
    private PaymentMethod paymentMethod;
    private PaymentDto paymentDto;
    private CartDto cartDto;
    private CartItemDto cartItemDto;
    private CheckoutEntity checkoutEntity;
    private ProductEntity productEntity;
    private ProductDto productDto;
    private List<CartItemDto> cartItemDtoList;
    private OrderItemEntity orderItemEntity;
    private List<OrderItemEntity> orderItemEntityList;
    private OrderDtoFull orderDto;
    private OrderEntity orderEntity;
    private List<OrderEntity> orderEntityList;
    private List<OrderDtoFull>orderDtoList;
    private AddressEntity addressEntity;



    @BeforeEach
    void setUp() {
        orderServiceImp = new OrderServiceImp(orderRepository,orderItemRepository,orderMapper,checkoutService,userMapper,userService,addressService,checkoutRepository,paymentService);
        userDto = new UserDto("Andres","Rodriguez", "andresr427@gmail.com","12345","3123454321",null);
        userEntity = new UserEntity(1,"Andres","Rodriguez", "andresr427@gmail.com","12345","3123454321",null);
        addressEntity = new AddressEntity(1,"house", "calle 17 12-61", userEntity);
        paymentDto = new PaymentDto(1,"cash");
        paymentMethod = new PaymentMethod(1, "cash");
        productEntity = new ProductEntity(1,"coffee", 10,1300.0);
        productDto = new ProductDto(1,"coffee", 10,1300.0);
        checkoutEntity = new CheckoutEntity(productEntity,5,userEntity);
        cartItemDto = new CartItemDto(checkoutEntity);
        cartDto = new CartDto(Arrays.asList(cartItemDto),5000.0);
        cartItemDtoList = Arrays.asList(cartItemDto);
        orderItemEntity = new OrderItemEntity(cartItemDto.getID(), cartItemDto.getQuantity(),cartItemDto.getProduct().getPrice(),new Date(),cartItemDto.getProduct());
        orderItemEntityList = Arrays.asList(orderItemEntity);
        orderEntity = new OrderEntity(1,new Date(),5000.0, orderItemEntityList,userEntity,addressEntity.getAddress());
        orderDto = new OrderDtoFull(1,orderEntity.getDate(),5000.0, orderItemEntityList,userEntity,addressEntity.getAddress());
        orderEntityList = Arrays.asList(orderEntity);
        orderDtoList = Arrays.asList(orderDto);
    }

    @Test
    void placeOrder() {

        when(userService.getUserFromToken()).thenReturn(userDto);
        when(paymentService.getPaymentMethod(anyInt())).thenReturn(paymentMethod);
        when(userService.updateUser(userDto)).thenReturn(userDto);
        when(checkoutService.listCartItems()).thenReturn(cartDto);
        when(checkoutRepository.findByUser_email(anyString())).thenReturn(checkoutEntity);
        when(userMapper.userDtoFull2UserEntityFull(userDto)).thenReturn(userEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderMapper.orderEntity2OrderDto(any(OrderEntity.class))).thenReturn(orderDto);

        OrderDtoFull orderSaved = orderServiceImp.placeOrder(paymentDto.getId());
        assertThat(orderSaved, is(equalTo(orderDto)));
    }

    @Test
    void getOrderItems() {
        when(orderItemRepository.save(any(OrderItemEntity.class))).thenReturn(orderItemEntity);

        List<OrderItemEntity> orderItemsEntitiesListed = orderServiceImp.getOrderItems(cartItemDtoList);
        assertThat(orderItemsEntitiesListed, is(equalTo(orderItemEntityList)));
        verify(orderItemRepository, times(1)).save(any(OrderItemEntity.class));
    }

    @Test
    void getOrderByUser() {
        when(userService.getUserFromToken()).thenReturn(userDto);
        lenient().when(orderRepository.findByUser_idOrderByDate(anyInt())).thenReturn(orderEntityList);
        when(orderMapper.orderEntityList2OrderDtoList(any(List.class))).thenReturn(orderDtoList);

        List<OrderDtoFull> orderDtoFound = orderServiceImp.getOrderByUser();
        assertThat(orderDtoFound, is(equalTo(orderDtoList)));
    }

    @Test
    void getOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(orderEntity));
        when(orderMapper.orderEntity2OrderDto(any(OrderEntity.class))).thenReturn(orderDto);

        OrderDtoFull orderFound = orderServiceImp.getOrderById(orderDto.getId());
        assertThat(orderFound, is(equalTo(orderDto)));
    }

    @Test
    void shouldThrow_OrderNotFoundException_WhenOrderDoesNotExists(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> {
            orderServiceImp.getOrderById(anyInt());
        });
    }
}