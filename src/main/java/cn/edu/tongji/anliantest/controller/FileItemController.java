package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.FileItem;
import cn.edu.tongji.anliantest.service.FileItemService;
import cn.edu.tongji.anliantest.util.DataWrapper;


@Controller
@RequestMapping("api")
public class FileItemController {
	FileItemService fileitemService;
	
	@Autowired
	public FileItemController(FileItemService fileitemService) {
		this.fileitemService = fileitemService;
	}
	
	@RequestMapping(value="fileitem/all",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<FileItem>> getAllFileList(
		@RequestParam(value = "projectId") Long projectId) {
		return fileitemService.getFileListByProjectId(projectId);
	}
	
	@RequestMapping(value="fileitem", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<FileItem> addFile(
		@RequestBody FileItem fileitem) {
		return fileitemService.addFileItem(fileitem);
	}
	
	@RequestMapping(value="fileitem", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<FileItem> updateFile(
		@RequestBody FileItem fileitem) {
		return fileitemService.updateFileItem(fileitem);
	}
	
	@RequestMapping(value="file/{fileId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteFile(
		@PathVariable("fileId") Long fileId) {
		return fileitemService.deleteFileItem(fileId);
	}

}
