package com.jpaprac.jpapractice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Data
@BatchSize(size = 10)
@Table(name = "users") // 예약어를 피하기 위해 이름 변경
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 200)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Order> orders;

    @Embedded
    private Address address;

    // Getters and Setters


}