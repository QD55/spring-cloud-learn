package com.example.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@EnableFeignClients     // 开启openfeign远程调用
@EnableDiscoveryClient  // 开启nacosdiscovery服务发现
@SpringBootApplication
public class OrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    /**
     *  1. 监听配置文件变化
     *  2. 打印变化信息
     *  3. 发送邮件
     */
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        return args -> {
            System.out.println("======项目启动======");
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener("service-order.properties",
                    "DEFAULT_GROUP", new Listener() {
                        @Override
                        public Executor getExecutor() {
                            return Executors.newFixedThreadPool(4);
                        }

                        @Override
                        public void receiveConfigInfo(String s) {
                            System.out.println("变化的配置：" + s);
                            System.out.println("邮件通知");
                        }
                    });
        };
    }

}
