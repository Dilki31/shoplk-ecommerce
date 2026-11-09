package com.dilki.shoplk.controller;

import com.dilki.shoplk.entity.Order;
import com.dilki.shoplk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<Order> placeOrder() {
        return ResponseEntity.ok(orderService.placeOrder());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }
}