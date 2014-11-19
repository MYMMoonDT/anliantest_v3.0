package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.JSJGTableDao;
import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.service.JSJGService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("jsjgService")
public class JSJGServiceImpl implements JSJGService{
	
	private static final Logger logger = LoggerFactory.getLogger(JSJGServiceImpl.class);
	
	@Autowired
	private JSJGTableDao jsjgTableDao;
	
	@Override
	public DataWrapper<JSJGTable> getJSJGTableById(Long jsjgTableId) {
		return null;
	}

	@Override
	public DataWrapper<JSJGTable> addJSJGTable(JSJGTable jsjgTable) {
		return null;
	}

	@Override
	public DataWrapper<JSJGTable> updateJSJGTable(JSJGTable jsjgTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJSJGTable(Long jsjgTableId) {
		return null;
	}
	
}
