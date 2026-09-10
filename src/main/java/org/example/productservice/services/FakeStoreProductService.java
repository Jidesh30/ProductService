package org.example.productservice.services;

import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.models.Category;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException(100L, "Product not found for ID: "+id);
        }
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtoList = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList) {
            productList.add(convertFakeStoreProductDtotoProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto1 =
                restTemplate.
                        execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor)
                        .getBody();
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto1);
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        Product existingProduct = null;
        try {
            existingProduct = getProductById(id);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (product.getTitle() != null) {
            existingProduct.setTitle(product.getTitle());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(existingProduct.getTitle());
        fakeStoreProductDto.setPrice(existingProduct.getPrice());
        fakeStoreProductDto.setDescription(existingProduct.getDescription());
        fakeStoreProductDto.setCategory(existingProduct.getCategory().getTitle());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto1 =
                restTemplate.
                        execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor)
                        .getBody();
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto1);
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        Product product = getProductById(id);
        if(product == null) {
            throw new ProductNotFoundException(100L, "Product not found for ID: "+id);
        }
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    private Product convertFakeStoreProductDtotoProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null) return null;

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());

        product.setCategory(category);
        return product;
    }
}
