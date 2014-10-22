package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.KHZLDJDTableDao;
import cn.edu.tongji.anliantest.model.KHZLDJDTable;

@Repository
public class KHZLDJDTableDaoImpl extends AbstractHibernateDao<KHZLDJDTable, Long> implements KHZLDJDTableDao{

	protected KHZLDJDTableDaoImpl() {
		super(KHZLDJDTable.class);
	}

	@Override
	public KHZLDJDTable getKHZLDJDById(Long khzldjdTableId) {
		return findById(khzldjdTableId);
	}

	@Override
	public void deleteKHZLDJD(Long khzldjdTableId) {
		delete(findById(khzldjdTableId));
	}

	@Override
	public Long addKHZLDJD(KHZLDJDTable khzldjdTable) {
		return add(khzldjdTable);
	}

	@Override
	public void updateKHZLDJD(KHZLDJDTable khzldjdTable) {
		updateKHZLDJD(khzldjdTable);
	}

}
