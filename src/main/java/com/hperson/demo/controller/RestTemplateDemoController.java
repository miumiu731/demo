package com.hperson.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author xuchen
 * @description 使用RestTemplate调用的三种方式
 */
@RestController
@PropertySource(value = "classpath:api.properties", ignoreResourceNotFound = true)
@Api(value = "用户管理")
@RequestMapping("/api")
public class RestTemplateDemoController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Value("${base_url}")
	private String baseUrl;

	@Value("${bill_list}")
	private String BillList;

	//第一种方式
	@GetMapping("/firstMode")
	public String modebyone(){
		String url = baseUrl + BillList;
		String response = restTemplate.getForObject(url,String.class);
		return response;
	}
	
	//第二种方式
	@GetMapping("/secondMode")
	public String clientMsg(){
		ServiceInstance serviceInstance = loadBalancerClient.choose("EURKA-CLIENT");
		String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/h-person/mobile/bill/list?page=1&limit=10";
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url,String.class);
		return response;
	}
	
	// 3.第三种方式，利用@LoadBalanced注解，可在restTemplate里使用应用名称进行调用
	@GetMapping("/thirdMode")
	@ApiOperation(value = "第三种方式", notes = "第三种方式传值")
    @ApiImplicitParam(name = "params", value = "传进来的参数", required = true,
            dataType = "map", paramType = "path")
	public String info(@RequestParam Map<String, Object> params) {
		String url = baseUrl+BillList;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		params.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
		url = builder.build().encode().toString(); 
		System.out.println("url:"+url);
		System.out.println(params);
		String forObjects = restTemplate.getForObject(url, String.class,params);
		return forObjects;
	}
}
