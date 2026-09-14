package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.projections.ProductTitleAndDescription;
import org.example.productservice.repos.CategoryRepo;
import org.example.productservice.repos.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService {

    ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        ProductTitleAndDescription productTitleAndDescription = productRepo.getProductByTitleAndDesc(id);
        System.out.println("Title: " + productTitleAndDescription.getTitle() +  " Desc: " + productTitleAndDescription.getDescription());
        return productRepo.findById(id).get();
//        return productRepo.getProductByTitleAndDesc(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {

    }

    @Override
    public Product createProduct(Product product) {
//        Category category = product.getCategory();
//        if(category.getId() == null){
//            Category savedCategory = categoryRepo.save(category);
//            product.setCategory(savedCategory);
//        } else {
//
//        }
        return productRepo.save(product);
    }
}
