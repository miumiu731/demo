package com.hperson.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.hperson.api.model.MobileBillVO;
import com.hperson.api.service.MobileBillPro;
import com.hperson.demo.utils.RestResponse;


/**
 * 
 * @author xuchen
 * @description 用feign调用对方接口
 */
@RestController
public class FeignController {

	@Autowired(required = false)
	private MobileBillPro mobileBillPro;
	
	@GetMapping("/provide/mobileBill/{id}")
	public RestResponse queryAll(@PathVariable("id") Long id) throws Exception {
		MobileBillVO mobileBillVO = mobileBillPro.queryById(id);
		return RestResponse.success().put("mobileBillEntity", mobileBillVO);
	}

}
