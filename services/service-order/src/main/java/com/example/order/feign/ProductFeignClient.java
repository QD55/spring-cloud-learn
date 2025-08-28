package com.example.order.feign;

import com.example.order.feign.fallback.ProductFeignClientFallBack;
import com.example.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallBack.class)
public interface ProductFeignClient {


    /**
     *  mvc注解的两套使用逻辑
     *  1. 标注在Controller上，是接收请求
     *  2. 标注在FeignClient上，是发送请求
     */
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long productId);
}
