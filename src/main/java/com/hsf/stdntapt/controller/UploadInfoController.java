package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.service.InfoService;

@Controller
@RequestMapping("/uploadInfo")
public class UploadInfoController {

	// @Value("${application.jsp.path}")
	// private String jspPath;

	@Resource
	InfoService infoService;

	@RequestMapping(value = "/addCollege.do", method = RequestMethod.POST)
	public String uploadInfo() {
		return "uploadInfo.jsp";
	}

	/** 接收上传的文件 */
	@RequestMapping(value = "/uploadCollege.do", method = RequestMethod.POST)
	public String uploadInfo(@RequestParam(value = "filename") MultipartFile file, Map<String, Object> map) {
		String msg = "";
		// 获取文件名
		String name = file.getOriginalFilename();
		// 定义一个本地文件副本(路径+文件名),用来接收前端上传的文件内容容
		String localfile = "/app/newFile.xlsx";// 需要修改文件路径
		try {
			List<College> collegeList = infoService.getCollegeInfo(name, file, localfile);
			System.out.println(collegeList);
			msg = "解析成功,总共" + collegeList.size() + "条!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败......";
		}
		map.put("msg", msg);
		return "uploadInfo.jsp";
	}
}
