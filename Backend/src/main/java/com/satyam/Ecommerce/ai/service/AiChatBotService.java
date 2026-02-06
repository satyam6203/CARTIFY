package com.satyam.Ecommerce.ai.service;

import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Response.ApiResponse;

public interface AiChatBotService {
    ApiResponse aiChatBot(String prompt,Long productId,Long userId) throws ProductException;
}
