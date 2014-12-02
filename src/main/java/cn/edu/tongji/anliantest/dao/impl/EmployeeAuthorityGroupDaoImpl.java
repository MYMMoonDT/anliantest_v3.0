package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityGroupDao;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Repository
public class EmployeeAuthorityGroupDaoImpl extends AbstractHibernateDao<EmployeeAuthorityGroup, Long> implements EmployeeAuthorityGroupDao{

	protected EmployeeAuthorityGroupDaoImpl() {
		super(EmployeeAuthorityGroup.class);
	}

	@Override
	public EmployeeAuthorityGroup getEmployeeAuthorityGroupById(Long employeeAuthorityGroupId) {
		return findById(employeeAuthorityGroupId);
	}
	
	@Override
	public EmployeeAuthorityGroup getEmployeeAuthorityGroupByDepartment(Department department) {
		List<?> list =  getCurrentSession().createCriteria(EmployeeAuthorityGroup.class)
				.add(Restrictions.eq("department", department))
				.list();
		
		if(list != null && !list.isEmpty()) {
			return (EmployeeAuthorityGroup) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long addEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeAuthorityGroup) {
		return add(employeeAuthorityGroup);
	}

	@Override
	public EmployeeAuthorityGroup updateEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeAuthorityGroup) {
		return saveOrUpdate(employeeAuthorityGroup);
	}

	@Override
	public void deleteEmployeeAuthorityGroup(Long employeeAuthorityGroupId) {
		delete(findById(employeeAuthorityGroupId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<EmployeeAuthorityGroup>> getAllEmployeeAuthorityGroups() {
		DataWrapper<List<EmployeeAuthorityGroup>> ret =  new DataWrapper<List<EmployeeAuthorityGroup>>();
		ret.setData(this.getCurrentSession().createCriteria(EmployeeAuthorityGroup.class)
					.addOrder(Order.asc("id"))
					.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
					.list());
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<EmployeeAuthorityGroup>> getEmployeeAuthorityGroupsByAuthorityGroupId(
			Long authorityGroupId) {
		DataWrapper<List<EmployeeAuthorityGroup>> ret =  new DataWrapper<List<EmployeeAuthorityGroup>>();
		ret.setData(this.getCurrentSession().createCriteria(EmployeeAuthorityGroup.class)
					.add(Restrictions.eq("authorityGroup.id", authorityGroupId))
					.addOrder(Order.asc("id"))
					.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
					.list());
		return ret;
	}
	
	
}
