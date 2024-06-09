package com.jtsp.springcassandrajpa.repository;


import com.jtsp.springcassandrajpa.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ProductRepository extends CassandraRepository<Product, Integer> {
}
