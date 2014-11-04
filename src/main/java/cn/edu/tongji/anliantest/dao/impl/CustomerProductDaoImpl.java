package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CustomerProductDao;
import cn.edu.tongji.anliantest.model.CustomerProduct;

@Repository
public class CustomerProductDaoImpl extends AbstractHibernateDao<CustomerProduct, Long> implements CustomerProductDao{

	protected CustomerProductDaoImpl() {
		super(CustomerProduct.class);
	}

	@Override
	public CustomerProduct getCustomerProductById(Long customerProductId) {
		return findById(customerProductId);
	}

	@Override
	public Long addCustomerProduct(CustomerProduct customerProduct) {
		return add(customerProduct);
	}

	@Override
	public void updateCustomerProduct(CustomerProduct customerProduct) {
		saveOrUpdate(customerProduct);
	}

	@Override
	public void deleteCustomerProduct(Long customerProductId) {
		delete(findById(customerProductId));
	}

}
