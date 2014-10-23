package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.Customer;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface CustomerService {
	
	public DataWrapper<Customer> getCustomerById(Long customerId);
	
	public DataWrapper<Customer> addCustomer(Customer customer);
	
	public DataWrapper<Customer> updateCustomer(Customer customer);
	
	public DataWrapper<Void> deleteCustomer(Long customerId);
	
	public DataWrapper<List<Customer>> getCustomerList(int currPageNum, int numPerPage);	
}
