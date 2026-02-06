package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Model.Seller;
import com.satyam.Ecommerce.Model.SellerReports;
import com.satyam.Ecommerce.Repo.SellerReportRepo;
import com.satyam.Ecommerce.Service.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepo sellerReportRepository;


    @Override
    public SellerReports getSellerReport(Seller seller) {
        SellerReports report = sellerReportRepository.findBySellerId(seller.getId());
        if (report == null) {
            SellerReports newReport = new SellerReports();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return report;
    }



    @Override
    public SellerReports updateSellerReport(SellerReports sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }
}