package com.dilki.shoplk.repository;

import com.dilki.shoplk.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}