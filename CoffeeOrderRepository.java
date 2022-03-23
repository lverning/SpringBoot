package com.example.demo.repository;

import com.example.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/3/9 9:35
 */

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
