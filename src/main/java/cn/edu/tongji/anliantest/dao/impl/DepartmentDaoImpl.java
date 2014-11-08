package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Repository
public class DepartmentDaoImpl extends AbstractHibernateDao<Department, Long> implements DepartmentDao{

	protected DepartmentDaoImpl() {
		super(Department.class);
	}

	@Override
	public Department getDepartmentById(Long departmentId) {
		return findById(departmentId);
	}
	
	@Override
	public Department getDepartmentByType(DepartmentTypeEnum departmentType) {
		
		Department department = new Department();
		department.setType(departmentType);
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Example.create(department));
		
		List<Department> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addDepartment(Department department) {
		return add(department);
	}

	@Override
	public void updateDepartment(Department department) {
		saveOrUpdate(department);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		delete(findById(departmentId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<Department>> getAllDepartments() {
		DataWrapper<List<Department>> ret =  new DataWrapper<List<Department>>();
		ret.setData(this.getCurrentSession().createCriteria(Department.class)
					.addOrder(Order.asc("id"))
					.list());
		return ret;
	}
}
