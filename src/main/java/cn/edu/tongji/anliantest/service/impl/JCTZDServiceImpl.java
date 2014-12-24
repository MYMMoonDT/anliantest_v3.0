package cn.edu.tongji.anliantest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.JCTZDTableDao;
import cn.edu.tongji.anliantest.model.JCTZDTable;
import cn.edu.tongji.anliantest.service.JCTZDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("jctzdService")
public class JCTZDServiceImpl implements JCTZDService{

	@Autowired
	public JCTZDTableDao jctzdTableDao;
	
	@Override
	public DataWrapper<JCTZDTable> getJCTZDTableById(Long jctzdTableId) {
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		ret.setData(jctzdTableDao.getJCTZDTableById(jctzdTableId));
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> getJCTZDTableProjectId(Long projectId) {
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		ret.setData(jctzdTableDao.getJCTZDTableByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> addJCTZDTable(JCTZDTable jctzdTable,
			Long taskId, Long employeeId) {
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> updateJCTZDTable(JCTZDTable jctzdTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJCTZDTable(Long jctzdTableId) {
		return null;
	}
	
}
