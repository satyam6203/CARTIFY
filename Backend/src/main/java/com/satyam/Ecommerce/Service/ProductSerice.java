package com.satyam.Ecommerce.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Request.CreateProductRequest;

public interface ProductSerice {
    public Product createProduct(CreateProductRequest req,Seller seller);
    public void deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId,Product product) throws ProductException;
    Product findProductById(Long ProductId) throws ProductException;
    List<Product> searchProducts(String query);
    public  Page<Product> getAllProducts(
        String category,
        String brand,
        String color,
        String sizes,
        Integer minPrice,
        Integer maxPrice,
        Integer minDiscount,
        String sort,
        String stock,
        Integer pageNumber
    );
    List<Product> getProductSellerId(Long SellerId);
}
