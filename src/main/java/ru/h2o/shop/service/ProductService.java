package ru.h2o.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.h2o.shop.entity.*;
import ru.h2o.shop.exception.InvalidProductTypeException;
import ru.h2o.shop.exception.ProductNotFoundException;
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Transactional
    public Product update(Long id, Product product) {
        return productRepository.findById(id)
                .map(newProduct -> {
                    newProduct.setSerialNumber(product.getSerialNumber());
                    newProduct.setManufacturer(product.getManufacturer());
                    newProduct.setPrice(product.getPrice());
                    newProduct.setStockQuantity(product.getStockQuantity());

                    if (product instanceof Desktop) {
                        ((Desktop) newProduct).setFormFactor(((Desktop) product).getFormFactor());
                    } else if (product instanceof Laptop) {
                        ((Laptop) newProduct).setDiagonal(((Laptop) product).getDiagonal());
                    } else if (product instanceof Monitor) {
                        ((Monitor) newProduct).setScreenSize(((Monitor) product).getScreenSize());
                    } else if (product instanceof HardDrive) {
                        ((HardDrive) newProduct).setMemorySize(((HardDrive) product).getMemorySize());
                    } else {
                        throw new InvalidProductTypeException(product.getClass().getSimpleName());
                    }

                    return productRepository.save(newProduct);
                }).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public List<Product> findAllByType(String type) {
        List<Product> products = productRepository.findAllByType(type);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found of type: " + type);
        }

        return products;
    }
}
