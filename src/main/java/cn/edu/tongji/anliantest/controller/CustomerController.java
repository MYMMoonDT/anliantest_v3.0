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

import cn.edu.tongji.anliantest.model.Customer;
import cn.edu.tongji.anliantest.service.CustomerService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class CustomerController {
CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value="customer", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Customer>> getCustomerList(
		@RequestParam(value = "currPageNum") int currPageNum,
		@RequestParam(value = "numPerPage") int numPerPage) {
		return customerService.getCustomerList(currPageNum, numPerPage);
	}
	
	@RequestMapping(value="customer/{customerId}", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Customer> getCustomerById(
		@PathVariable("customerId") Long customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@RequestMapping(value="customer", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Customer> addCustomer(
		@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping(value="customer", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<Customer> updateCustomer(
		@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}
	
	@RequestMapping(value="customer/{customerId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteCustomer(
		@PathVariable("customerId") Long customerId) {
		return customerService.deleteCustomer(customerId);
	}
}
