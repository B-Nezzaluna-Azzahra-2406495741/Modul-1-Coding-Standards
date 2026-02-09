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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateProductTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void create_setsIdWhenNull_andReturnsProduct() {
        // mock repository to set an id when productId is null
        given(productRepository.create(any(Product.class))).willAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            if (p.getProductId() == null) {
                p.setProductId("generated-id");
            }
            return p;
        });

        Product input = new Product();
        input.setProductName("Sampo Cap Bambang");
        input.setProductQuantity(100);
        // service rely on repository to generate ID
        input.setProductId(null);

        // create product
        Product created = productService.create(input);

        // Assert
        assertNotNull(created);
        assertEquals("generated-id", created.getProductId());
        assertEquals("Sampo Cap Bambang", created.getProductName());
        assertEquals(100, created.getProductQuantity());

        // Verify repository
        verify(productRepository).create(any(Product.class));
    }

    @Test
    void create_preservesGivenId_andReturnsProduct() {
        // product with pre-set ID
        given(productRepository.create(any(Product.class))).willAnswer(invocation -> invocation.getArgument(0));

        Product input = new Product();
        input.setProductId("custom-id");
        input.setProductName("Sampo Cap Usep");
        input.setProductQuantity(50);

        // create product
        Product created = productService.create(input);

        // Assert
        assertEquals("custom-id", created.getProductId());
        assertEquals("Sampo Cap Usep", created.getProductName());
        assertEquals(50, created.getProductQuantity());

        // Verify repository
        verify(productRepository).create(any(Product.class));
    }

    @Test
    void findById_returnsProduct_whenPresent() {
        // Arrange
        Product p = new Product();
        p.setProductId("id-1");
        p.setProductName("Item A");
        p.setProductQuantity(1);
        given(productRepository.findById("id-1")).willReturn(Optional.of(p));

        // Act
        Optional<Product> result = productService.findById("id-1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("id-1", result.get().getProductId());
        assertEquals("Item A", result.get().getProductName());
        assertEquals(1, result.get().getProductQuantity());
    }

    @Test
    void findById_returnsEmpty_whenNotFound() {
        // Arrange
        given(productRepository.findById("missing")).willReturn(Optional.empty());

        // Act
        Optional<Product> result = productService.findById("missing");

        // Assert
        assertTrue(result.isEmpty());
    }
}
