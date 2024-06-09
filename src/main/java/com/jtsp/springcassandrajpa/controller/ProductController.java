package com.jtsp.springcassandrajpa.controller;

import com.jtsp.springcassandrajpa.repository.ProductRepository;
import com.jtsp.springcassandrajpa.entity.Product;
import com.jtsp.springcassandrajpa.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        productRepository.save(product);
        return product;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer productId){
        Product product=productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found:" + productId));
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Integer productId,
                                                 @RequestBody Product productDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        product.setName(productDetails.getName());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found: " + productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

}