package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.CustomerContactPerson;

public interface CustomerContactPersonDao {
	public CustomerContactPerson getCustomerContactPersonById(Long customerContactPersonId);
	
	public Long addCustomerContactPerson(CustomerContactPerson customerContactPerson);
	
	public void updateCustomerContactPerson(CustomerContactPerson customerContactPerson);
	
	public void deleteCustomerContactPerson(Long customerContactPersonId);
}
