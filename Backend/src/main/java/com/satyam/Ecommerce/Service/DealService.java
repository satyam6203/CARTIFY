package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Model.Deal;;

public interface DealService {
    Deal createDeal(Deal deal);
//    List<Deal> createDeals(List<Deal> deals);
    List<Deal> getDeals();
    Deal updateDeal(Deal deal,Long id) throws Exception;
    void deleteDeal(Long id) throws Exception;

}
