package com.example.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.example.order.bean.Order;
import com.example.order.feign.ProductFeignClient;
import com.example.order.service.OrderService;
import com.example.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);

        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        // 远程调用计算总金额
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("尚硅谷");
        // 远程调用查询商品列表
        order.setProductList(List.of(product));

        return order;
    }

    public Product getProductFromRemote (Long productId) {
        // 1. 获取远程服务地址
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getUri().toString() + "/product/" + productId;

        log.info("请求 {}", url);
        // 2. 向远程服务发送请求
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    // 使用负载均衡完成远程调用
    public Product getProductFromRemoteWithLoadBalancer (Long productId) {
        // 3. 使用负载均衡实现具体使用哪个远程服务
        ServiceInstance choose = loadBalancerClient.choose("service-product");

        String url = choose.getUri().toString() + "/product/" + productId;

        log.info("请求 {}", url);
        // 2. 向远程服务发送请求
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    // 使用负载均衡注解完成远程调用
    public Product getProductFromRemoteWithLoadBalancerAnnotation (Long productId) {

        String url = "http://service-product/product/" + productId;
        // 2. 向远程服务发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
