package com.example.demo.repository;

import com.example.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/3/9 9:36
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
