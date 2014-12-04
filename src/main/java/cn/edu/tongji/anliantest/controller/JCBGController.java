package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.service.JCBGService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class JCBGController {
	JCBGService jcbgService;
	
	@Autowired
	public JCBGController(JCBGService jcbgService) {
		this.jcbgService = jcbgService;
	}
	
	@RequestMapping(value="jcbg/upload", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<JCBGTable> uploadJCBGFile(
			 @RequestParam("file") MultipartFile file,
			 @RequestParam("projectId") Long projectId) {
		return jcbgService.uploadJCBGTable(projectId, file);
	}
	
	@RequestMapping(value="jcbg/test", method=RequestMethod.GET)
	public void test() {
		jcbgService.test();
	}
}
