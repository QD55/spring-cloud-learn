package com.example.order.feign.fallback;
import java.math.BigDecimal;

import com.example.order.feign.ProductFeignClient;
import com.example.product.bean.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientFallBack implements ProductFeignClient {


    @Override
    public Product getProductById(Long productId) {
        System.out.println("兜完了....");
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("兜底商品");
        product.setNum(0);

        return product;
    }
}
