package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CustomerContactPersonDao;
import cn.edu.tongji.anliantest.model.CustomerContactPerson;

@Repository
public class CustomerContactPersonDaoImpl extends AbstractHibernateDao<CustomerContactPerson, Long> implements CustomerContactPersonDao {

	protected CustomerContactPersonDaoImpl() {
		super(CustomerContactPerson.class);
	}

	@Override
	public CustomerContactPerson getCustomerContactPersonById(
			Long customerContactPersonId) {
		return findById(customerContactPersonId);
	}

	@Override
	public Long addCustomerContactPerson(
			CustomerContactPerson customerContactPerson) {
		return add(customerContactPerson);
	}

	@Override
	public void updateCustomerContactPerson(
			CustomerContactPerson customerContactPerson) {
		saveOrUpdate(customerContactPerson);
	}

	@Override
	public void deleteCustomerContactPerson(Long customerContactPersonId) {
		delete(findById(customerContactPersonId));
	}

}
