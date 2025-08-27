package com.example.order.controller;

import com.example.order.bean.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

/*    @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;*/

    @GetMapping("/config")
    public String config() {
        return "order.timeout:" + orderProperties.getTimeout() +
                ", order.auto-confirm:" + orderProperties.getAutoConfirm() +
                ", order.db-url:" + orderProperties.getDbUrl();
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,
                             @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
