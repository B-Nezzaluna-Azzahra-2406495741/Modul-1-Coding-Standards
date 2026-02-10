package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EditProductTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void editProduct_updatesFields_whenProductExists() {
        // Arrange existing product in repository
        Product existing = new Product();
        existing.setProductId("id-1");
        existing.setProductName("Old Name");
        existing.setProductQuantity(10);
        given(productRepository.findById("id-1")).willReturn(Optional.of(existing));

        // update data
        Product update = new Product();
        update.setProductId("id-1");
        update.setProductName("New Name");
        update.setProductQuantity(25);

        // Act
        Product result = productService.editProduct(update);

        // repository updated and returned
        assertNotNull(result);
        assertEquals("id-1", result.getProductId());
        assertEquals("New Name", result.getProductName());
        assertEquals(25, result.getProductQuantity());
        // findById should be called
        verify(productRepository).findById("id-1");
    }

    @Test
    void editProduct_throwsWhenProductNotFound() {
        // repository returns empty
        given(productRepository.findById(anyString())).willReturn(Optional.empty());

        Product update = new Product();
        update.setProductId("missing");
        update.setProductName("Whatever");
        update.setProductQuantity(1);

        // edit product & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> productService.editProduct(update));
        assertTrue(ex.getMessage().contains("Product not found"));
        verify(productRepository).findById("missing");
    }
}

