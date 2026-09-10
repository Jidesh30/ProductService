package org.example.productservice.controllers;

import org.example.productservice.dtos.ProductNotFoundExceptionDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repos.CategoryRepo;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService, CategoryRepo categoryRepo) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = null;
//        try {
            product = productService.getProductById(id);
//        } catch (InstanceNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        ResponseEntity<Product> productResponseEntity;
//        if(product == null){
//            productResponseEntity = new ResponseEntity<>("Product Not found for ID: "+ id, HttpStatus.NOT_FOUND);
//            return productResponseEntity;
//        }
        productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return productResponseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProductById(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ProductNotFoundExceptionDto> handleInstanceNotFoundException(ProductNotFoundException exception) {
//        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
//        productNotFoundExceptionDto.setErrorCode(exception.getId());
//        productNotFoundExceptionDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ProductNotFoundExceptionDto> handleNullPointerException(ProductNotFoundException exception) {
//        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
//        productNotFoundExceptionDto.setErrorCode(exception.getId());
//        productNotFoundExceptionDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
//    }

}
