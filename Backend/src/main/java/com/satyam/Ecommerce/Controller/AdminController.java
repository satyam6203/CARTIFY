package com.satyam.Ecommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.constants.AccountStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final SellerService sellerService;

    @PatchMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updateSeller(
        @PathVariable Long id,
        @PathVariable AccountStatus status
    ) throws Exception{
        Seller updateSeller=sellerService.updateSellerAccountStatus(id, status);
        return ResponseEntity.ok(updateSeller);
    }

}
