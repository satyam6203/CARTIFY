package com.satyam.Ecommerce.ai.service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.ai.service.AiProductService;
import org.springframework.beans.factory.annotation.Value;


@Service
public class AiProductServiceImpl implements AiProductService {

    @Value("${gemini.api.key}")
    private static String API_KEY;


    @Override
    public String simpleChat(String prompt) {
        return "";
    }
}