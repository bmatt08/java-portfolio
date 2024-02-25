package com.capstone.ekart.repository;

import org.springframework.data.repository.CrudRepository;

import com.capstone.ekart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
