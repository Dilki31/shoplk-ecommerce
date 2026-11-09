package com.dilki.shoplk.service;

import com.dilki.shoplk.entity.*;
import com.dilki.shoplk.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Cart getMyCart() {
        User user = getCurrentUser();
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).items(new ArrayList<>()).build()));
    }

    public Cart addItemToCart(Long productId, Integer quantity) {
        Cart cart = getMyCart();
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();

        cartItemRepository.save(item);
        return cartRepository.findById(cart.getId()).orElseThrow();
    }
}