package com.satyam.Ecommerce.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.PaymentOrder;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.SellerReports;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Response.PaymentLinkResponse;
import com.satyam.Ecommerce.Service.PaymentService;
import com.satyam.Ecommerce.Service.SellerReportService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.TransactionService;
import com.satyam.Ecommerce.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
        @PathVariable String paymentId,
        @RequestParam String paymentLinkId,
        @RequestHeader("Authorization")String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        PaymentLinkResponse paymentLinkResponse;

        PaymentOrder paymentOrder=paymentService.
                getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess=paymentService.ProceedPaymentOrder(
            paymentOrder, 
            paymentId, 
            paymentLinkId
        );
        
        if(paymentSuccess){
            for(Order order:paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerByID(order.getSellerId());
                SellerReports reports = sellerReportService.getSellerReport(seller);
                reports.setTotalOrders(reports.getTotalOrders()+1);
                reports.setTotalEarning(reports.getTotalEarning()+order.getOrderItems().size());
                reports.setTotalSales(reports.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(reports);
            }
        }
        ApiResponse res=new ApiResponse();
        res.setMessage("Payment Successful");
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
