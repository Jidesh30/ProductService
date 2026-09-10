package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);

    Product updateProductById(Long id, Product product);

    void deleteProductById(Long id) throws ProductNotFoundException;

    Product createProduct(Product product);
}
