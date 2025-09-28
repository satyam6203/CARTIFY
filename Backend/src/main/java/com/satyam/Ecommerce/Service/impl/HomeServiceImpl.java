package com.satyam.Ecommerce.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.satyam.Ecommerce.Domain.Deal;
import com.satyam.Ecommerce.Domain.Home;
import com.satyam.Ecommerce.Domain.HomeCategory;
import com.satyam.Ecommerce.Repo.DealRepo;
import com.satyam.Ecommerce.Service.HomeService;
import com.satyam.Ecommerce.constants.HomeCategorySection;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeServiceImpl implements HomeService{

    private final DealRepo dealRepo;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {
        List<HomeCategory> gridCategories=allCategories.stream().filter(
            category->category.getSection() ==HomeCategorySection.GRID
        ).collect(Collectors.toList());

        List<HomeCategory> shopCategories=allCategories.stream().filter(
            category->category.getSection() ==HomeCategorySection.SHOP_BY_CATEGORIES
        ).collect(Collectors.toList());

        List<HomeCategory> electricCategories=allCategories.stream().filter(
            category->category.getSection() ==HomeCategorySection.ELECTRIC_CATEGORIES
        ).collect(Collectors.toList());

        List<HomeCategory> dealCategories=allCategories.stream().filter(
            category->category.getSection() ==HomeCategorySection.DEALS
        ).collect(Collectors.toList());
        
        List<Deal> creatDeals=new ArrayList<>();

        if(dealRepo.findAll().isEmpty()){
            List<Deal> deals=allCategories.stream()
                .filter(category->category.getSection() == HomeCategorySection.DEALS)
                .map(category->new Deal(null,10,category))
                .collect(Collectors.toList());
            creatDeals=dealRepo.saveAll(deals);
        }
        else{
            creatDeals =dealRepo.findAll();
        }

        Home home=new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopCategories);
        home.setDeals(creatDeals);
        home.setElectricCategories(electricCategories);
        home.setDealCategories(dealCategories);

        return home;
    }
}
