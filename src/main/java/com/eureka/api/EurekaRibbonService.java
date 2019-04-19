package com.eureka.api;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eureka.service.DemoServiceFallback;
import com.eureka.utils.RestResponse;


@FeignClient(name = "EURKA-CLIENT",path="/h-person")
public interface EurekaRibbonService{
	@RequestMapping(value="/api/hello/{id}",method= RequestMethod.GET)
	public String hello(@PathVariable(value="id") String id);
	
	@RequestMapping(value="/mobile/bill/list",method= RequestMethod.GET)
	public String mobileBillServiceQuery(@RequestParam(value="params") Map<String, Object> params);
}