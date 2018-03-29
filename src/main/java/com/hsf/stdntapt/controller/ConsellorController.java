package com.hsf.stdntapt.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.service.ConsellorService;

@Controller
@RequestMapping("/consellor")
public class ConsellorController {
	@Resource
	ConsellorService consellorService;

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/{consellId}", method = RequestMethod.GET)
	@ResponseBody
	public Consellor consellor(@PathVariable(value = "consellId") int consellId) {
		Consellor consellor = consellorService.findOneConsellor(consellId);
		return consellor;
	}
}
