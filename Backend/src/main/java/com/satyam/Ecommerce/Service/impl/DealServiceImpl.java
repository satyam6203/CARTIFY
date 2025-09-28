package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Deal;
import com.satyam.Ecommerce.Domain.HomeCategory;
import com.satyam.Ecommerce.Repo.DealRepo;
import com.satyam.Ecommerce.Repo.HomeCategoryRepo;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Service.DealService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepo dealRepo;
    private final HomeCategoryRepo homeCategoryRepo;

    @Override
    public List<Deal> getDeal() {
        return dealRepo.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory category = homeCategoryRepo.findById(deal.getCategory().getId()).orElse(null);

        Deal newDeal = dealRepo.save(deal);
        newDeal.setCategory(category);
        newDeal.setDiacount(deal.getDiacount());
        return dealRepo.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepo.findById(id).orElse(null);
        HomeCategory category = homeCategoryRepo.findById(deal.getCategory().getId()).orElse(null);
        if (existingDeal != null) {
            if (deal.getDiacount() != null) {
                existingDeal.setDiacount(deal.getDiacount());
            }
            if (category != null) {
                existingDeal.setCategory(category);
            }
            return dealRepo.save(existingDeal);
        }
        throw new Exception("Deal Not found");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepo.findById(id).orElseThrow(() -> new Exception("Deal not found with this id"));
        dealRepo.delete(deal);
        ApiResponse res = new ApiResponse();
        res.setMessage("Del deleted SuccessFully");
    }

}
