package com.dilki.shoplk.repository;
import com.dilki.shoplk.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {}