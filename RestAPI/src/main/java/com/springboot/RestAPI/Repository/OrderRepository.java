package com.springboot.RestAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RestAPI.Model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
