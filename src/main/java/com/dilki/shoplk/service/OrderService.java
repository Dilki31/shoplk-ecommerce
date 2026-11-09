package com.dilki.shoplk.service;

import com.dilki.shoplk.entity.*;
import com.dilki.shoplk.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public Order placeOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart empty"));
        if (cart.getItems().isEmpty()) throw new RuntimeException("Cart is empty");

        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalAmount(total)
                .status(Order.OrderStatus.PENDING)
                .items(new ArrayList<>())
                .build();

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem ->
                OrderItem.builder()
                        .order(order)
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getProduct().getPrice())
                        .build()
        ).toList();

        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    public List<Order> getMyOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return orderRepository.findByUserId(user.getId());
    }
}