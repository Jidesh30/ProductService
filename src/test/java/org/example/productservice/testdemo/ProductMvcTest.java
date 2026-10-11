import org.example.productservice.controllers.ProductController;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    @Qualifier("SelfProductService")
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct() throws Exception {

        // Arrange
        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setTitle("iPhone");
        product.setPrice(999.0);

        when(productService.getProductById(productId))
                .thenReturn(product);

        // Act & Assert
        mockMvc.perform(
                        get("/products/{id}", productId)
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("iPhone"))
                .andExpect(jsonPath("$.price").value(999.0));

        verify(productService).getProductById(productId);
    }
}