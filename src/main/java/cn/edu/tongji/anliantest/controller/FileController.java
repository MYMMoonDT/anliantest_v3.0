package cn.edu.tongji.anliantest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.model.FileItem;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.service.FileService;
import cn.edu.tongji.anliantest.util.DataWrapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("api")
public class FileController {
	FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@RequestMapping(value="files", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<FileGroup>> getFileGroupList(
		@RequestParam(value = "projectId") Long projectId,	
		@RequestParam(value = "currPageNum") int currPageNum,
		@RequestParam(value = "numPerPage") int numPerPage) {
		return fileService.getFileGroupList(projectId, currPageNum, numPerPage);
	}
	
	@RequestMapping(value="files/{fileGroupId}", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<FileGroup> getFileGroupById(
		@PathVariable("fileGroupId") Long fileGroupId) {
		return fileService.getFileGroupById(fileGroupId);
	}
	
	@RequestMapping(value="files", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<FileGroup> addFileGroup(
		HttpServletRequest request,
		@RequestParam("groupName") String groupName,
		@RequestParam("comment") String comment,
		@RequestParam("project") String project,
		@RequestParam("items") String items,
		@RequestParam("file") List<MultipartFile> files) throws JsonParseException, JsonMappingException, IOException {
		
		FileGroup fileGroup = new FileGroup();
		fileGroup.setGroupName(groupName);
		fileGroup.setComment(comment);
		
		ObjectMapper mapper = new ObjectMapper();
		Project projectTemp = mapper.readValue(project, Project.class);
		fileGroup.setProject(projectTemp);
		
		mapper = new ObjectMapper();
		FileItem[] itemsTemp = mapper.readValue(items, FileItem[].class);
		
		return fileService.addFileGroup(fileGroup, itemsTemp, files);
	}
	
	@RequestMapping(value="files", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<FileGroup> updateFileGroup(
		@RequestBody FileGroup fileGroup) {
		return fileService.updateFileGroup(fileGroup);
	}
	
	@RequestMapping(value="files/{fileGroupId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteFileGroup(
		@PathVariable("fileGroupId") Long fileGroupId) {
		return fileService.deleteFileGroup(fileGroupId);
	}
}
