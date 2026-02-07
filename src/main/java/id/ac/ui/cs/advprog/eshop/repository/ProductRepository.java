package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    //create product
    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(java.util.UUID.randomUUID().toString()); // Generate ID otomatis
        }
        productData.add(product);
        return product;
    }

    //delete product
    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }

    //edit product
    public Product editProduct(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }

    //get all products
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    //get product by id
    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}