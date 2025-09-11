package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Request.CreateProductRequest;
import com.satyam.Ecommerce.Service.ProductSerice;
import com.satyam.Ecommerce.Service.SellerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product/seller")
public class SellerProductController {

    private final SellerService sellerService;
    private final ProductSerice productSerice;

    @GetMapping()
    public ResponseEntity<List<Product>> getProductBySellerId(
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        Seller seller=sellerService.getSellerProfile(jwt);
        List<Product> products=productSerice.getProductSellerId(seller.getId());
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Product> createProduct(
        @RequestBody CreateProductRequest request,
        @RequestHeader("Authorization")String jwt
    ) throws Exception{
        Seller seller=sellerService.getSellerProfile(jwt);
        Product product=productSerice.createProduct(request, seller);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        try{
            productSerice.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
