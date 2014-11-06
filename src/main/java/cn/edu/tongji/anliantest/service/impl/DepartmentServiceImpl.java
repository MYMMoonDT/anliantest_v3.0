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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWrapper<Department> addDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWrapper<Department> updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWrapper<Void> deleteDepartment(Long departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWrapper<List<Department>> getDepartmentList(int currPageNum,
			int numPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWrapper<List<Department>> getAllDepartments() {
		return departmentDao.getAllDepartments();
	}
}
