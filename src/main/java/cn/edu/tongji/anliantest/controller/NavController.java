package cn.edu.tongji.anliantest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavController {
	@RequestMapping(value = "/login")
	public ModelAndView loginPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/login");
        return view;
	}
	
	@RequestMapping(value = "/project")
	public ModelAndView projectPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/project");
        return view;
	}
	
	@RequestMapping(value = "/project/{projectId}")
	public ModelAndView projectDetailPage(
			@PathVariable("projectId") Long projectId,
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/project/"+projectId);
        return view;
	}
	
	@RequestMapping(value = "/employee")
	public ModelAndView employeePage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/employee");
        return view;
	}
	
	@RequestMapping(value = "/customer")
	public ModelAndView customerPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/customer");
        return view;
	}
	
	@RequestMapping(value = "/authority")
	public ModelAndView authorityPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/authority");
        return view;
	}
	
	@RequestMapping(value = "/authGrpConfig")
	public ModelAndView authGrpConfigPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/authGrpConfig");
        return view;
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView testPage(
            HttpServletRequest request){
        String host = request.getRequestURL().substring(0,request.getRequestURL().indexOf("/",10)+1);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:"+host+"#/test");
        return view;
	}
}
