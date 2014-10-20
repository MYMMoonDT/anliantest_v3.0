package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.HTPSJLDao;
import cn.edu.tongji.anliantest.model.HTPSJLTable;

@Repository
public class HTPSJLDaoImpl extends AbstractHibernateDao<HTPSJLTable, Long> implements HTPSJLDao{

	protected HTPSJLDaoImpl() {
		super(HTPSJLTable.class);
	}

	@Override
	public HTPSJLTable getHTPSJLById(Long htpsjlTableId) {
		return findById(htpsjlTableId);
	}

	@Override
	public Long addHTPSJL(HTPSJLTable htpsjlTable) {
		return add(htpsjlTable);
	}

	@Override
	public void updateHTPSJL(HTPSJLTable htpsjlTable) {
		saveOrUpdate(htpsjlTable);
	}

	@Override
	public void deleteHTPSJL(Long htpsjlTableId) {
		delete(findById(htpsjlTableId));
	}
	
}
