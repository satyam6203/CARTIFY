package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.SellerReports;

public interface SellerReportService {
    SellerReports getSellerReport(Seller seller);
    SellerReports updateSellerReport(SellerReports sellerReports);
}
