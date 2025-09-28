package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.Home;
import com.satyam.Ecommerce.Domain.HomeCategory;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
