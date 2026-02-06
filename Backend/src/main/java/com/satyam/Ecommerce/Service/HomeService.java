package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Model.Home;
import com.satyam.Ecommerce.Model.HomeCategory;


public interface HomeService {

    Home creatHomePageData(List<HomeCategory> categories);

}
