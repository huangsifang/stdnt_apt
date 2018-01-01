package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.service.InfoService;

@Controller
@RequestMapping("/uploadInfo")
public class UploadInfoController {

	@Resource
	InfoService infoService;

	@RequestMapping(value = "/uploadInfo.do")
	public String uploadInfo() {
		return "uploadInfo";
	}

	/** 接收上传的文件 */
	@RequestMapping(value = "/uploadInfoFromType.do")
	public String uploadInfo(@RequestParam(value = "filename") MultipartFile file,
			@RequestParam(value = "filetype") String type, Map<String, Object> map) {
		String msg = "";
		// 获取文件名
		String name = file.getOriginalFilename();
		try {
			if (type.equals("college")) {
				List<College> collegeList = infoService.getCollegeInfo(name, file);
				for (int i = 0; i < collegeList.size(); i++) {
					infoService.insertCollegeList(collegeList.get(i).getCollegeID(),
							collegeList.get(i).getCollegeName());
				}
				msg = "解析成功,总共" + collegeList.size() + "条!";
			} else if (type.equals("class")) {
				List<Class> classList = infoService.getClassInfo(name, file);
				for (int i = 0; i < classList.size(); i++) {
					infoService.insertClassList(classList.get(i).getClassID(), classList.get(i).getClassName(),
							classList.get(i).getSpeciID(), classList.get(i).getConsellID());
				}
				msg = "解析成功,总共" + classList.size() + "条!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败......";
		}
		map.put("msg", msg);
		return "uploadInfo";
	}
}
