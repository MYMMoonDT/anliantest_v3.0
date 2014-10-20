package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.TaskService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class TaskController {
	TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(value="task", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<List<Task>> getTaskById(
		@RequestParam("id") Long id,
		@RequestParam("type") String type) {
		if(type.equals("employee")) {
			return taskService.getTaskListByEmployeeId(id);
		}else if(type.equals("department")) {
			return taskService.getTaskListByDepartmentId(id);
		}else {
			return null;
		}
	}
	
}
