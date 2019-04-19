package com.eureka.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eureka.api.EurekaRibbonService;



@RestController
@PropertySource(value = "classpath:api.properties", ignoreResourceNotFound = true)
public class MobileController {

	@Autowired
	private EurekaRibbonService mobillServer;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${base_url}")
	private String baseUrl;
	
	@Value("${bill_list}")
	private String BillList;

	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> params) {
		String object =  mobillServer.mobileBillServiceQuery(params);
		System.out.println(object);
		return object;
	}

	@GetMapping("/clientMsg")
	public String clientMsg(){
		//第一种方式
		 RestTemplate restTemplate = new RestTemplate();
		 String response = restTemplate.getForObject("http://www.hperson.com/h-person/mobile/bill/list?page=3&limit=10",String.class);
//
//		//第二种方式
//		ServiceInstance serviceInstance = loadBalancerClient.choose("EURKA-CLIENT");
//		String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/h-person/mobile/bill/list?page=1&limit=10";
//		System.out.println(url);
//		RestTemplate restTemplate = new RestTemplate();
//		String response = restTemplate.getForObject(url,String.class);
		return response;
	}

	@GetMapping("/info")
	public String info(@RequestParam Map<String, Object> params) {
		// 3.第三种方式，利用@LoadBalanced注解，可在restTemplate里使用应用名称进行调用
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		map.put("page", 2);
//		map.put("limit", 10);
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
