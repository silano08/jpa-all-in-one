package com.jpaprac.jpapractice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> getOrdersByPrice(Double price) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> order = query.from(Order.class);
        query.select(order).where(cb.greaterThan(order.get("price"), price));
        return entityManager.createQuery(query).getResultList();
    }
}