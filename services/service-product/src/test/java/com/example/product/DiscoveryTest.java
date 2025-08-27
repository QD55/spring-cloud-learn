package com.example.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for (String name : nacosServiceDiscovery.getServices()){
            System.out.println(name);
            for (ServiceInstance instance : nacosServiceDiscovery.getInstances(name)) {
                System.out.println(instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
            }

        }
    }


    @Test
    void discoveryClientTest(){
        for (String serviceName : discoveryClient.getServices()) {
            System.out.println("service = " + serviceName);
            for (ServiceInstance instance : discoveryClient.getInstances(serviceName)) {
                System.out.println(instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
            }

        }
    }
}
