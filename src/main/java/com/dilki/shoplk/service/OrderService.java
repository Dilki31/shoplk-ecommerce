package com.dilki.shoplk.service;

import com.dilki.shoplk.entity.*;
import com.dilki.shoplk.repository.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    /**
     * Converts the current user's cart into an order. Runs in one transaction so the order,
     * the stock update and the cart clean-up either all succeed or all roll back.
     */
    @Transactional
    public Order placeOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart is empty"));
        if (cart.getItems().isEmpty()) throw new RuntimeException("Cart is empty");

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            if (cartItem.getQuantity() > product.getStockQuantity()) {
                throw new IllegalArgumentException("Not enough stock for " + product.getName()
                        + ". Available: " + product.getStockQuantity());
            }
        }

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

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            orderItems.add(OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(product.getPrice())
                    .build());
        }
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear(); // orphanRemoval deletes the cart rows
        cartRepository.save(cart);

        return savedOrder;
    }

    @Transactional(readOnly = true)
    public List<Order> getMyOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return orderRepository.findByUserId(user.getId());
    }
}
