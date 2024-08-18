package com.wangf.spring.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cloudbus")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class CloudBusTestController {
    private final Environment environment;

    private String readValue() {
        return environment.getProperty("config.test");
    }


    @EventListener({RefreshScopeRefreshedEvent.class,
            ApplicationReadyEvent.class})
    public void onCloudBusEvent() {
        log.info("New Value: {}", readValue());
    }


    @RequestMapping("/read")
    public String read() {
        return readValue();
    }
}
