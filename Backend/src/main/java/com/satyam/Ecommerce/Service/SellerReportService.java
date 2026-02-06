package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Model.Seller;
import com.satyam.Ecommerce.Model.SellerReports;

public interface SellerReportService {
    SellerReports getSellerReport(Seller seller);
    SellerReports updateSellerReport(SellerReports sellerReports);
}
