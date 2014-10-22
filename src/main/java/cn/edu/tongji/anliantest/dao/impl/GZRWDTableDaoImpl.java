package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.GZRWDTableDao;
import cn.edu.tongji.anliantest.model.GZRWDTable;

@Repository
public class GZRWDTableDaoImpl extends AbstractHibernateDao<GZRWDTable, Long> implements GZRWDTableDao{

	protected GZRWDTableDaoImpl() {
		super(GZRWDTable.class);
	}

	@Override
	public GZRWDTable getGZRWDById(Long gzrwdTableId) {
		return findById(gzrwdTableId);
	}

	@Override
	public Long addGZRWD(GZRWDTable gzrwdTable) {
		return add(gzrwdTable);
	}

	@Override
	public void updateGZRWD(GZRWDTable gzrwdTable) {
		saveOrUpdate(gzrwdTable);
	}

	@Override
	public void deleteGZRWD(Long gzrwdTableId) {
		delete(findById(gzrwdTableId));
	}

}
