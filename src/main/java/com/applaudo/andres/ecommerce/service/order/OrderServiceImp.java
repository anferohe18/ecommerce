package com.applaudo.andres.ecommerce.service.order;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartItemDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.OrderEntity;
import com.applaudo.andres.ecommerce.entity.OrderItemsEntity;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
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
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final CheckoutService checkoutService;
    private final UserMapper userMapper;
    private final UserService userService;
    private final AddressService addressService;
    private final CheckoutRepository checkoutRepository;

    private final PaymentService paymentService;


    @Override
    public OrderDtoFull placeOrder(Integer paymentId) {
        // first let get cart items for the user
        UserDto user = getUserFromToken();
        PaymentMethod paymentMethod = paymentService.getPaymentMethod(paymentId);
        user.setPaymentMethod(paymentMethod);
        UserDto userFinal = userService.updateUser(user);
        CartDto cartDto = checkoutService.listCartItems();

        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        CheckoutEntity checkout = checkoutRepository.findByUser_email(user.getEmail());

        // create the order and save it
        OrderEntity order = new OrderEntity();
        order.setUser(userMapper.userDtoFull2UserEntityFull(userFinal));
        order.setTotalPrice(cartDto.getTotalCost());
        order.setDate(new Date());
        List<OrderItemsEntity> orderItemsList = getOrderItems(cartItemDtoList);
        order.setOrderItems(orderItemsList);
        order.setDeliveryAddress(checkout.getDeliveryAddress());
        orderRepository.save(order);
        return orderMapper.orderEntity2OrderDto(order);
    }

    public List<OrderItemsEntity> getOrderItems(List<CartItemDto> cartItemList){
        List<OrderItemsEntity> orderItemList = new ArrayList<>();
        for(CartItemDto cartItemDto : cartItemList){
            OrderItemsEntity  item = new OrderItemsEntity();
            item.setDate(new Date());
            item.setPrice(cartItemDto.getProduct().getPrice());
            item.setProduct(cartItemDto.getProduct());
            item.setQuantity(cartItemDto.getQuantity());
            // add to order item list
            OrderItemsEntity orderItemSaved = orderItemRepository.save(item);
            orderItemList.add(orderItemSaved);
        }
        return orderItemList;
    }

    @Override
    public List<OrderDtoFull> getOrderByUser() {
        UserDto user = getUserFromToken();
        List<OrderEntity> orderEntities = orderRepository.findByUser_idOrderByDate(user.getId());
        return orderMapper.orderEntityList2OrderDtoList(orderEntities);
    }

    @Override
    public  OrderDtoFull getOrderById(Integer id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if(orderEntity.isPresent()){
            return orderMapper.orderEntity2OrderDto(orderEntity.get());
        }
        throw new OrderNotFoundException("Order not found");
    }

    @Override
    public OrderDtoFull updateAddress(AddressDto addressDto) {
        UserDto user = getUserFromToken();
        AddressDto newAddress = addressService.addAddress(addressDto, user);
        CartDto cartDto = checkoutService.listCartItems();
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        // create the order and save it
        OrderEntity order = new OrderEntity();
        order.setUser(userMapper.userDtoFull2UserEntityFull(user));
        order.setTotalPrice(cartDto.getTotalCost());
        order.setDate(new Date());
        List<OrderItemsEntity> orderItemsList = getOrderItems(cartItemDtoList);
        order.setOrderItems(orderItemsList);
        order.setDeliveryAddress(newAddress.getAddress());
        orderRepository.save(order);
        checkoutService.deleteUserCartItems(user);
        return orderMapper.orderEntity2OrderDto(order);
    }

    public UserDto getUserFromToken(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Object email = userService.getEmailFromToken(principal);
        return userService.findUserByEmail(email.toString());
    }
}
