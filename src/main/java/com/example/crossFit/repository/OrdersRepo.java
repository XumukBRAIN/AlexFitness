package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    Orders findByClientId(UUID ID);

    List<Orders> findByPhoneNumber(String phoneNumber);
}
