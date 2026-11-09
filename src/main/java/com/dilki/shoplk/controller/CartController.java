package com.dilki.shoplk.controller;

import com.dilki.shoplk.entity.Cart;
import com.dilki.shoplk.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok(cartService.getMyCart());
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.addItemToCart(productId, quantity));
    }
}