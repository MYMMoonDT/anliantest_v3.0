package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.CustomerContactPersonDao;
import cn.edu.tongji.anliantest.dao.CustomerDao;
import cn.edu.tongji.anliantest.dao.CustomerProductDao;
import cn.edu.tongji.anliantest.model.Customer;
import cn.edu.tongji.anliantest.model.CustomerContactPerson;
import cn.edu.tongji.anliantest.model.CustomerProduct;
import cn.edu.tongji.anliantest.service.CustomerService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerContactPersonDao customerContactPersonDao;
	@Autowired
	private CustomerProductDao customerProductDao;
	
	
	@Override
	public DataWrapper<Customer> getCustomerById(Long customerId) {
		DataWrapper<Customer> ret = new DataWrapper<>();
        
		Customer customer = customerDao.getCustomerById(customerId);
        ret.setData(customer);
        
        return ret;
	}

	@Override
	public DataWrapper<Customer> addCustomer(Customer customer) {
		DataWrapper<Customer> ret = new DataWrapper<>();
		
		customer.getCustomerHealthDep().setCustomer(customer);
		for(CustomerContactPerson item : customer.getContactPersonItems()) {
			item.setCustomer(customer);
		}
		for(CustomerProduct item : customer.getProductItems()) {
			item.setCustomer(customer);
		}
		
		customerDao.addCustomer(customer);
		
		ret.setData(customerDao.getCustomerById(customer.getId()));
		
		logger.info("添加客户信息:"+customer.getCompanyName()+"("+customer.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Customer> updateCustomer(Customer customer) {
		DataWrapper<Customer> ret = new DataWrapper<>();
		
		//删除已有的一对多关系
		Customer oldCustomer = customerDao.getCustomerById(customer.getId());
		for(CustomerContactPerson item : oldCustomer.getContactPersonItems()) {
			item.setCustomer(null);
			customerContactPersonDao.deleteCustomerContactPerson(item.getId());
		}
		for(CustomerProduct item : oldCustomer.getProductItems()) {
			item.setCustomer(null);
			customerProductDao.deleteCustomerProduct(item.getId());
		}
		oldCustomer.setContactPersonItems(null);
		oldCustomer.setProductItems(null);
		customerDao.updateCustomer(oldCustomer);
		
		customer.getCustomerHealthDep().setCustomer(customer);
		for(CustomerContactPerson item : customer.getContactPersonItems()) {
			item.setCustomer(customer);
		}
		for(CustomerProduct item : customer.getProductItems()) {
			item.setCustomer(customer);
		}
		
		customerDao.updateCustomer(customer);
		
		ret.setData(customerDao.getCustomerById(customer.getId()));
		
		logger.info("更新客户信息:"+customer.getCompanyName()+"("+customer.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteCustomer(Long customerId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		Customer customer = customerDao.getCustomerById(customerId);
		customer.getCustomerHealthDep().setCustomer(null);
		
		for(CustomerContactPerson item : customer.getContactPersonItems()) {
			item.setCustomer(null);
		}
		
		for(CustomerProduct item : customer.getProductItems()) {
			item.setCustomer(null);
		}
		
		customerDao.deleteCustomer(customerId);
		
		logger.info("删除客户信息:" + "(" + customerId + ")");
		
		return ret;
	}

	@Override
	public DataWrapper<List<Customer>> getCustomerList(int currPageNum,
			int numPerPage) {
		return customerDao.getCustomerList(currPageNum, numPerPage);
	}
	
}
