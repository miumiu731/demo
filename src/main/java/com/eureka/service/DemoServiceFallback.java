package com.eureka.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.eureka.api.EurekaRibbonService;

@Component
public class DemoServiceFallback implements EurekaRibbonService {

	@Override
	public String hello(String id) {
		return "error";
	}

	@Override
	public String mobileBillServiceQuery(Map<String, Object> params) {
		return "error";
	}
    
}
