package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.AuthorityGroupDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityGroupDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityItemDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityItem;
import cn.edu.tongji.anliantest.service.EmployeeService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.ErrorCodeEnum;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private AuthorityGroupDao authorityGroupDao;
	@Autowired
	private EmployeeAuthorityGroupDao employeeAuthorityGroupDao;
	@Autowired
	private EmployeeAuthorityItemDao employeeAuthorityItemDao;

	@Override
	public DataWrapper<Employee> login(String number, String password) {
		DataWrapper<Employee> ret = new DataWrapper<Employee>();

		Employee employee = employeeDao.getEmployeeByNum(number);
		if (employee == null) {
			ret.setErrorCode(ErrorCodeEnum.Employee_Not_Exist);
		} else if (!employee.getPassword().equals(password)) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			ret.setData(employee);

			logger.info("员工登录:" + employee.getName() + "(" + employee.getId()
					+ ")");
		}

		return ret;
	}

	@Override
	public DataWrapper<Void> logout() {
		return null;
	}

	@Override
	public DataWrapper<Employee> getEmployeeById(Long employeeId) {
		DataWrapper<Employee> ret = new DataWrapper<>();

		Employee employee = employeeDao.getEmployeeById(employeeId);
		ret.setData(employee);

		return ret;
	}

	@Override
	public DataWrapper<Employee> addEmployee(Employee employee) {
		DataWrapper<Employee> ret = new DataWrapper<>();

		employeeDao.addEmployee(employee);

		ret.setData(employeeDao.getEmployeeById(employee.getId()));

		logger.info("添加用户信息:" + employee.getName() + "(" + employee.getId()
				+ ")");

		return ret;
	}

	@Override
	public DataWrapper<Employee> updateEmployee(Employee employee) {
		DataWrapper<Employee> ret = new DataWrapper<>();

		employeeDao.updateEmployee(employee);

		ret.setData(employeeDao.getEmployeeById(employee.getId()));

		logger.info("更新用户信息:" + employee.getName() + "(" + employee.getId()
				+ ")");

		return ret;
	}

	@Override
	public DataWrapper<Void> deleteEmployee(Long employeeId) {
		DataWrapper<Void> ret = new DataWrapper<>();

		employeeDao.deleteEmployee(employeeId);

		logger.info("删除用户信息:" + "(" + employeeId + ")");

		return ret;
	}

	@Override
	public DataWrapper<List<Employee>> getEmployeeList(int currPageNum,
			int numPerPage) {
		return employeeDao.getEmployeeList(currPageNum, numPerPage);
	}

	@Override
	public DataWrapper<List<Employee>> getAllEmployeeList() {
		return employeeDao.getAllEmployeeList();
	}

	@Override
	public DataWrapper<Employee> updateEmployeeAuthorityGroup(Long employeeId,
			Long authorityGroupId) {
		DataWrapper<Employee> ret = new DataWrapper<>();
		
		Employee employee = employeeDao.getEmployeeById(employeeId);
		AuthorityGroup authorityGroup = authorityGroupDao.getAuthorityGroupById(authorityGroupId);
		
		EmployeeAuthorityGroup employeeAuthorityGroup = employee.getEmployeeAuthorityGroup();
		employeeAuthorityGroup.setAuthorityGroup(authorityGroup);
		
		employeeAuthorityGroup.getItems().clear();
		for(AuthorityItem item : authorityGroup.getAuthorityItems()) {
			EmployeeAuthorityItem employeeAuthorityItem = new EmployeeAuthorityItem();
			employeeAuthorityItem.setAuthorityItem(item);
			employeeAuthorityItem.setIsActive(true);
			employeeAuthorityGroup.getItems().add(employeeAuthorityItem);
		}
		
		employeeAuthorityGroupDao.updateEmployeeAuthorityGroup(employeeAuthorityGroup);
		
		logger.info("更新用户权限信息:" + "(" + employeeId + ")");
		
		ret.setData(employee);
		
		return ret;
	}

	@Override
	public DataWrapper<Void> updateEmployeeAuthorityItem(
			EmployeeAuthorityItem employeeAuthorityItem) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		employeeAuthorityItemDao.updateEmployeeAuthorityItem(employeeAuthorityItem);
		
		if(employeeAuthorityItem.getIsActive()) {
			logger.info("开启用户权限:" + employeeAuthorityItem.getAuthorityItem().getName());
		}else {
			logger.info("关闭用户权限:" + employeeAuthorityItem.getAuthorityItem().getName());
		}
		
		return ret;
	}
}
