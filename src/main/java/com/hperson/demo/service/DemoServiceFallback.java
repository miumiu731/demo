package com.hperson.demo.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.hperson.demo.feign.FeignDemoService;


/**
 * @author xuchen
 * @description 失败后返回错误信息
 */

@Component
public class DemoServiceFallback implements FeignDemoService {

	@Override
	public String hello(String id) {
		return "error";
	}

	@Override
	public String mobileBillServiceQuery(Map<String, Object> params) {
		return "error";
	}
    
}
