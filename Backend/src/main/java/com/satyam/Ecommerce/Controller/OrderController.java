package com.satyam.Ecommerce.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.PaymentLink;
import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Domain.Address;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.OrderItem;
import com.satyam.Ecommerce.Domain.PaymentOrder;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.SellerReports;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.PaymentOrderRepo;
import com.satyam.Ecommerce.Response.PaymentLinkResponse;
import com.satyam.Ecommerce.Service.CartService;
import com.satyam.Ecommerce.Service.OrderService;
import com.satyam.Ecommerce.Service.PaymentService;
import com.satyam.Ecommerce.Service.SellerReportService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.UserService;
import com.satyam.Ecommerce.constants.PaymentMethod;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final JwtProvider jwtProvider;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepo paymentOrderRepo;


    @GetMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

        PaymentOrder paymentOrder=paymentService.createOrder(user, orders);
        PaymentLinkResponse res = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment=paymentService.createRazorPaymentLink(
                user,
                paymentOrder.getAmount(), 
                paymentOrder.getId()
            );
            String paymentUrl=payment.get("short_url");
            String paymentUrlId=payment.get("id");
            
            res.setPayment_link_url(paymentUrl);
            paymentOrder.setPaymentLink(paymentUrlId);

            paymentOrderRepo.save(paymentOrder);
        }
        else{
            String paymentUrl=paymentService.craeteStripePaymentLink(
                user,
                paymentOrder.getAmount(), 
                paymentOrder.getId()
            );
            res.setPayment_link_url(paymentUrl);

        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistoryHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
        @PathVariable Long orderId,
        @RequestHeader("Authorization")String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order orders=orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
        @PathVariable Long orderItemId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        OrderItem orderItem=orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancle")
    public ResponseEntity<Order> cancleOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception{  
        User user =userService.findUserByJwtToken(jwt);
        Order order=orderService.cancelOrder(orderId, user);
        Seller seller=sellerService.getSellerByID(order.getSellerId());
        SellerReports report=sellerReportService.getSellerReport(seller);

        report.setCanceledOrder(report.getCanceledOrder()+1);
        report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);
        
        return ResponseEntity.ok(order);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReports> getSellerReport(
            @RequestHeader("Authorization")String jwt
    ) throws Exception{
        Seller seller=sellerService.getSellerProfile(jwt);
        SellerReports report=sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report,HttpStatus.OK);
    }
}
