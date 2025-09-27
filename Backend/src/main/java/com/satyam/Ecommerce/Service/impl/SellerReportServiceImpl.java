package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.SellerReports;
import com.satyam.Ecommerce.Repo.SellerReportRepo;
import com.satyam.Ecommerce.Service.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{

    private final SellerReportRepo sellerReportRepo;

    @Override
    public SellerReports getSellerReport(Seller seller) {
        SellerReports sr=sellerReportRepo.findBySellerId(seller.getId());
        if(sr==null){
            SellerReports newReports=new SellerReports();
            newReports.setSeller(seller);
            return sellerReportRepo.save(newReports);
        }
        return sr;
    }

    @Override
    public SellerReports updateSellerReport(SellerReports sellerReports) {
        return sellerReportRepo.save(sellerReports);
    }
}
