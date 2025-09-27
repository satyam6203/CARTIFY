package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.satyam.Ecommerce.Domain.SellerReports;

@Repository
public interface SellerReportRepo extends JpaRepository<SellerReports,Long> {
    SellerReports findBySellerId(Long sellerId);
}
