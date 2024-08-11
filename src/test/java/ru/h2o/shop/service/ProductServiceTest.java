package ru.h2o.shop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.h2o.shop.entity.Desktop;
import ru.h2o.shop.entity.Product;
import ru.h2o.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProductTest() {
        Desktop desktop = new Desktop();
        desktop.setSerialNumber("12345");
        desktop.setManufacturer("Test Manufacturer");
        desktop.setPrice(BigDecimal.valueOf(1000));
        desktop.setStockQuantity(10);
        desktop.setFormFactor("Desktop");

        when(productRepository.save(any(Desktop.class))).thenReturn(desktop);

        Product createdProduct = productService.create(desktop);

        assertNotNull(createdProduct);
        assertEquals("12345", createdProduct.getSerialNumber());
        assertEquals("Test Manufacturer", createdProduct.getManufacturer());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void findByIdTest() {
        Desktop desktop = new Desktop();
        desktop.setId(1L);
        desktop.setSerialNumber("12345");
        desktop.setManufacturer("Test Manufacturer");

        when(productRepository.findById(1L)).thenReturn(Optional.of(desktop));

        Product foundProduct = productService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void updateProductTest() {
        Desktop existingDesktop = new Desktop();
        existingDesktop.setId(1L);
        existingDesktop.setSerialNumber("12345");
        existingDesktop.setManufacturer("Test Manufacturer");
        existingDesktop.setPrice(BigDecimal.valueOf(1000));
        existingDesktop.setStockQuantity(10);
        existingDesktop.setFormFactor("Desktop");

        Desktop newDesktop = new Desktop();
        newDesktop.setSerialNumber("67890");
        newDesktop.setManufacturer("New Manufacturer");
        newDesktop.setPrice(BigDecimal.valueOf(1500));
        newDesktop.setStockQuantity(5);
        newDesktop.setFormFactor("Mini Desktop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingDesktop));
        when(productRepository.save(any(Desktop.class))).thenReturn(newDesktop);

        Product updatedProduct = productService.update(1L, newDesktop);

        assertNotNull(updatedProduct);
        assertEquals("67890", updatedProduct.getSerialNumber());
        assertEquals("New Manufacturer", updatedProduct.getManufacturer());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void findAllByTypeTest() {
        List<Product> desktops = new ArrayList<>();
        desktops.add(new Desktop());
        desktops.add(new Desktop());

        when(productRepository.findAllByType("desktop")).thenReturn(desktops);

        List<Product> foundProducts = productService.findAllByType("desktop");

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        verify(productRepository, times(1)).findAllByType("desktop");
    }
}
