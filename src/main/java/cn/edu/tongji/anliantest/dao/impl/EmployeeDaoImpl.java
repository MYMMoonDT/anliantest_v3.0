package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.PageResult;

@Repository
public class EmployeeDaoImpl extends AbstractHibernateDao<Employee, Long> implements EmployeeDao{

	protected EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		return findById(employeeId);
	}

	@Override
	public Employee getEmployeeByNum(String employeeNum) {
		Criteria criteria = getCurrentSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("number", employeeNum));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Employee)ret.get(0);
		}
		return null;
	}

	@Override
	public Long addEmployee(Employee employee) {
		return add(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		saveOrUpdate(employee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		delete(findById(employeeId));
	}

	@Override
	public DataWrapper<List<Employee>> getEmployeeList(int currPageNum, int numPerPage) {
		DataWrapper<List<Employee>> ret = new DataWrapper<List<Employee>>();
		
		PageResult<Employee> pageResult = findByCriteriaByPage(null, currPageNum, numPerPage);
		
		ret.setData(pageResult.getData());
		ret.setNumPerPage(pageResult.getNumPerPage());
		ret.setCurrPageNum(pageResult.getCurrPageNum());
		ret.setTotalItemNum(pageResult.getTotalItemNum());
		ret.setTotalPageNum(pageResult.getTotalPageNum());
		
		return ret;
	}
	
}
