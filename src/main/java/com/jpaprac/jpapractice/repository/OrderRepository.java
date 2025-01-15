package com.jpaprac.jpapractice.repository;

import com.jpaprac.jpapractice.model.entity.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.price > :price")
    List<Order> findOrdersWithPriceGreaterThan(@Param("price") Double price);

    List<Order> findByUserId(Long userId);
}