package com.hperson.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hperson.demo.feign.FeignDemoService;

/**
 * 
 * @author xuchen
 * @description 用feign调用对方接口
 */
@RestController
public class FeignController {
	@Autowired
	private FeignDemoService feignDemoService;
	
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> params) {
		String object =  feignDemoService.mobileBillServiceQuery(params);
		System.out.println(object);
		return object;
	}
}
