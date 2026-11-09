package com.dilki.shoplk.repository;
import com.dilki.shoplk.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}