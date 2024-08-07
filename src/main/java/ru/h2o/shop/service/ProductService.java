package ru.h2o.shop.service;

import org.springframework.stereotype.Service;
import ru.h2o.shop.entity.Product;
import ru.h2o.shop.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByType(String type) {
        return productRepository.findProductByType(type);
    }
}
