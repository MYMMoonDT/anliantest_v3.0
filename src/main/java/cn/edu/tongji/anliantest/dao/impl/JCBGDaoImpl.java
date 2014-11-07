package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;

@Repository
public class JCBGDaoImpl implements JCBGTableDao{

	@Override
	public Log getJCBGTableById(Long jcbgTableId) {
		return null;
	}

	@Override
	public Long addJCBGTable(JCBGTable jcbgTable) {
		return null;
	}

	@Override
	public void updateJCBGTable(JCBGTable jcbgTable) {
	}

	@Override
	public void deleteJCBGTable(Long jcbgTableId) {
	}

}
