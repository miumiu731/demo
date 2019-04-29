package com.hperson.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hperson.demo.domain.InfoPojo;
import com.hperson.demo.feign.FeignDemoService;
import com.hperson.demo.utils.CollectionUtil;
import com.hperson.demo.utils.StringUtils;

/**
 * 
 * @author xuchen
 * @description 用feign调用对方接口
 */
@RestController
@RequestMapping("/api")
public class FeignController {
	@Autowired
	private FeignDemoService feignDemoService;
	
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> params) {
		String object =  feignDemoService.mobileBillServiceQuery(params);
		 if(StringUtils.isNotBlank(object)){
			 List<InfoPojo> infoList = JsonParse(object);
	        if(CollectionUtil.isNotEmpty(infoList)){
	        	return  infoList.get(0).getBankName()+"------------"+infoList.get(0).getCompanyName();
	        }
	        
	}
		 return "无";
	}

	/**   
	 * @Title: JsonParse   
	 * @Description:
	 * @param object
	 * @return     
	 * @return: List<InfoPojo>   
	 * @author: 徐琛  
	 * @throws   
	 */
	private List<InfoPojo> JsonParse(String object) {
		JSONObject jsonObject = JSON.parseObject(object);
		String page = jsonObject.getString("page");
		JSONObject jsonObject2 = JSON.parseObject(page);
		String records = jsonObject2.getString("records");
		List<InfoPojo> infoList = JSONObject.parseArray(records, InfoPojo.class);
		return infoList;
	}
}
