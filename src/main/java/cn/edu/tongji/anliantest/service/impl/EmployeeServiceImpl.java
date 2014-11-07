package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroupItem;
import cn.edu.tongji.anliantest.service.EmployeeService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.ErrorCodeEnum;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
    private EmployeeDao employeeDao;

	@Override
	public DataWrapper<Employee> login(String number, String password) {
		DataWrapper<Employee> ret = new DataWrapper<>();
		
		Employee employee = employeeDao.getEmployeeByNum(number);
		if (employee == null) {
			ret.setErrorCode(ErrorCodeEnum.Employee_Not_Exist);
		} else if(!employee.getPassword().equals(password)){
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			ret.setData(employee);
			
			logger.info("员工登录:"+employee.getName()+"("+employee.getId()+")");
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
		
		logger.info("添加用户信息:"+employee.getName()+"("+employee.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Employee> updateEmployee(Employee employee) {
		DataWrapper<Employee> ret = new DataWrapper<>();
		
//		for (EmployeeAuthorityGroup authGrp : employee.getEmployeeAuthorityGroups()) {
//			authGrp.setEmployee(employee);
//			for (EmployeeAuthorityGroupItem authGrpItem : authGrp.getEmployeeAuthorityGroupItems()) {
//				authGrpItem.setEmployeeAuthorityGroup(authGrp);
//			}
//		}

		//e.getEmployeeAuthorityGroups().retainAll(employee.getEmployeeAuthorityGroups());

//		Employee e = employeeDao.getEmployeeById(employee.getId());
//		for (EmployeeAuthorityGroup authGrp : e.getEmployeeAuthorityGroups()) {
//			if (!employee.getEmployeeAuthorityGroups().contains(authGrp)) {
//				authGrp.setEmployee(null);
//				for (EmployeeAuthorityGroupItem authGrpItem : authGrp.getEmployeeAuthorityGroupItems()) {
//					authGrpItem.setEmployeeAuthorityGroup(null);
//				}
//			}
//		}
	
		employeeDao.updateEmployee(employee);
//		employee.setName("hack");
		
//		e.setName("hack2");
		
		ret.setData(employeeDao.getEmployeeById(employee.getId()));
		
		logger.info("更新用户信息:"+employee.getName()+"("+employee.getId()+")");
		
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
	
}
