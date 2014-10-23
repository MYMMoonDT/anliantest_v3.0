package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CustomerDao;
import cn.edu.tongji.anliantest.model.Customer;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.PageResult;

@Repository
public class CustomerDaoImpl extends AbstractHibernateDao<Customer, Long> implements CustomerDao{

	protected CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		return findById(customerId);
	}

	@Override
	public Long addCustomer(Customer customer) {
		return add(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		delete(findById(customerId));
	}

	@Override
	public DataWrapper<List<Customer>> getCustomerList(int currPageNum,
			int numPerPage) {
		
		DataWrapper<List<Customer>> ret = new DataWrapper<List<Customer>>();
		
		PageResult<Customer> pageResult = findByCriteriaByPage(null, currPageNum, numPerPage);
		
		ret.setData(pageResult.getData());
		ret.setNumPerPage(pageResult.getNumPerPage());
		ret.setCurrPageNum(pageResult.getCurrPageNum());
		ret.setTotalItemNum(pageResult.getTotalItemNum());
		ret.setTotalPageNum(pageResult.getTotalPageNum());
		
		return ret;
	}

}
