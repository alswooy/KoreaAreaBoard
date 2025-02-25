package com.min.koreaareaboard.category.repository;

import com.min.koreaareaboard.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
