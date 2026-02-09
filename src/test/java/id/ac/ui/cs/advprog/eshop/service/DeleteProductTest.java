package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProductTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void deleteById_callsRepository() {
        // Act
        productService.deleteById("id-1");
        // Assert
        verify(productRepository).deleteById("id-1");
    }

    @Test
    void deleteById_nonExistingId_doesNotThrow() {
        assertDoesNotThrow(() -> productService.deleteById("missing"));
        verify(productRepository).deleteById("missing");
    }
}

