package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.service.DepartmentService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public DataWrapper<Department> getDepartmentById(Long departmentId) {
		DataWrapper<Department> ret = new DataWrapper<>();
        
		Department department = departmentDao.getDepartmentById(departmentId);
        ret.setData(department);
        
        return ret;
	}

	@Override
	public DataWrapper<Department> addDepartment(Department department) {
		DataWrapper<Department> ret = new DataWrapper<>();
		
		departmentDao.addDepartment(department);
		
		ret.setData(departmentDao.getDepartmentById(department.getId()));
		
		logger.info("添加部门信息:"+department.getName()+"("+department.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Department> updateDepartment(Department department) {
		DataWrapper<Department> ret = new DataWrapper<>();
		
		departmentDao.updateDepartment(department);
		
		ret.setData(departmentDao.getDepartmentById(department.getId()));
		
		logger.info("更新部门信息:"+department.getName()+"("+department.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteDepartment(Long departmentId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		departmentDao.deleteDepartment(departmentId);
		
		logger.info("删除部门信息:" + "(" + departmentId + ")");
		
		return ret;
	}

	@Override
	public DataWrapper<List<Department>> getAllDepartments() {
		return departmentDao.getAllDepartments();
	}
}
