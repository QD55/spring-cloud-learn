package com.example.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")  // 配置批量绑定nacos配置中心的properties，可无需@RefreshScope就能实现自动刷新
@Data
public class OrderProperties {

    private String timeout;
    private String autoConfirm;
    private String dbUrl;
}
