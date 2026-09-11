package org.example.productservice.repos;

import org.example.productservice.models.Product;
import org.example.productservice.projections.ProductTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    //HQL
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductTitleAndDescription getProductByTitleAndDesc(@Param("id") Long id);

    //SQL
    @Query(value = "select title, description from product where id :id", nativeQuery = true)
    ProductTitleAndDescription getProductByTitleAndDescSQL(@Param("id") Long id);
}
