package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.model.Log;

@Repository
public class LogDaoImpl extends AbstractHibernateDao<Log, Long> implements LogDao{

	protected LogDaoImpl() {
		super(Log.class);
	}

	@Override
	public Log getLogById(Long logId) {
		return findById(logId);
	}

	@Override
	public Long addLog(Log log) {
		return add(log);
	}

	@Override
	public void updateLog(Log log) {
		saveOrUpdate(log);
	}

	@Override
	public void deleteLog(Long logId) {
		delete(findById(logId));
	}
}
