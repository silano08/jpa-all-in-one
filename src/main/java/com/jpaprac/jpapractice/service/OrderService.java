package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.entity.Order;
import com.jpaprac.jpapractice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public void createOrderWithTransactionTemplate(Order order) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(status -> {
            orderRepository.save(order);
            return null;
        });
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOrderInNewTransaction(Order order) {
        orderRepository.save(order);
    }
}
