package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.Customer;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface CustomerDao {
	public Customer getCustomerById(Long customerId);
	
	public Long addCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	public void deleteCustomer(Long customerId);
	
	public DataWrapper<List<Customer>> getCustomerList(int currPageNum, int numPerPage);
}
