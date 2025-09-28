package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.Deal;

public interface DealService {
    List<Deal> getDeal();
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal,Long id)throws Exception;
    void deleteDeal(Long id)throws Exception;
}
