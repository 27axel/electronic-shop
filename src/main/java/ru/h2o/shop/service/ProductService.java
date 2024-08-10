package ru.h2o.shop.service;

import org.springframework.stereotype.Service;
import ru.h2o.shop.entity.*;
import ru.h2o.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

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

    public Product update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product newProduct = productOptional.get();
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
            }

            return productRepository.save(newProduct);
        }

        return productRepository.save(product);
    }

    public List<Product> findAllByType(String type) {
        return productRepository.findAllByType(type);
    }
}
