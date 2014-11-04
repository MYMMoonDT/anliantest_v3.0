package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.CustomerProduct;

public interface CustomerProductDao {
	public CustomerProduct getCustomerProductById(Long customerProductId);
	
	public Long addCustomerProduct(CustomerProduct customerProduct);
	
	public void updateCustomerProduct(CustomerProduct customerProduct);
	
	public void deleteCustomerProduct(Long customerProductId);
}
