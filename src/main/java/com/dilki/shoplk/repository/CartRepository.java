package com.dilki.shoplk.repository;
import com.dilki.shoplk.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}