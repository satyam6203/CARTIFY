package com.satyam.Ecommerce.Service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import jakarta.persistence.criteria.Join;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.satyam.Ecommerce.Domain.Category;
import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Repo.CategoryRepo;
import com.satyam.Ecommerce.Repo.ProductRepo;
import com.satyam.Ecommerce.Request.CreateProductRequest;
import com.satyam.Ecommerce.Service.ProductSerice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductSerice {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {
        Category category1 = categoryRepo.findByCategoryId(req.getCategory());
        if (category1 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory());
            category.setLevel(1);
            category1 = categoryRepo.save(category);
        }
        Category category2 = categoryRepo.findByCategoryId(req.getCategory());
        if (category2 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory2());
            category.setLevel(2);
            category2 = categoryRepo.save(category);
        }
        Category category3 = categoryRepo.findByCategoryId(req.getCategory());
        if (category3 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory3());
            category.setLevel(3);
            category3 = categoryRepo.save(category);
        }
        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setSellingPrice(req.getSellingPrice());
        product.setImage(req.getImages());
        product.setMrpPrice(req.getMrpPrice());
        product.setSizes(req.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepo.save(product);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Actual price must be greater than Zero.");
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercent = (discount / mrpPrice) * 100;
        return (int) discountPercent;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepo.delete(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        findProductById(productId);
        product.setId(productId);
        return productRepo.save(product);
    }

    @Override
    public Product findProductById(Long ProductId) throws ProductException {
        return productRepo.findById(ProductId)
                .orElseThrow(() -> new ProductException("Product is not found with id :->" + ProductId));
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepo.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String color, String sizes, Integer minPrice,
            Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (category != null) {
                Join<Product, Category> categoryJion = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJion.get("categoryId"), category));
            }
            if (color != null && !color.isEmpty()) {
                System.out.println("color " + color);
                predicates.add(criteriaBuilder.equal(root.get("color"), color));
            }
            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("sizes"), sizes));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }
            if (minDiscount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDiscount));
            }
            if (stock != null) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "price_low":
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10,
                            Sort.by("sellingPrice").ascending());
                    break;

                case "price_high":
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10,
                            Sort.by("sellingPrice").descending());
                    break;
                default:
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10,
                            Sort.unsorted());
                    break;
            }
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());

        }
        return productRepo.findAll(spec, pageable);
    }

    @Override
    public List<Product> getProductSellerId(Long SellerId) {
        return productRepo.findBySellerId(SellerId);
    }
}
