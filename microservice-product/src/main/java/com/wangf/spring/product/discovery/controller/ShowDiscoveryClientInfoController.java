package com.wangf.spring.product.discovery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/discovery")
@RequiredArgsConstructor
public class ShowDiscoveryClientInfoController {


    private final DiscoveryClient discoveryClient;


    @RequestMapping("/info")
    public List<String> discoveryInfo() {
        List<String> instances = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            for (ServiceInstance serviceInstance : serviceInstances) {
                instances.add("%s:%s".formatted(serviceInstance.getServiceId(), serviceInstance.getUri()));
            }
        }
        return instances;
    }
}
