package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.HTPSJLItemDao;
import cn.edu.tongji.anliantest.model.HTPSJLItem;

@Repository
public class HTPSJLItemDaoImpl extends AbstractHibernateDao<HTPSJLItem, Long> implements HTPSJLItemDao{

	protected HTPSJLItemDaoImpl() {
		super(HTPSJLItem.class);
	}

	@Override
	public HTPSJLItem getHTPSJLById(Long htpsjlItemId) {
		return findById(htpsjlItemId);
	}

	@Override
	public Long addHTPSJL(HTPSJLItem htpsjlItem) {
		return add(htpsjlItem);
	}

	@Override
	public void updateHTPSJL(HTPSJLItem htpsjlItem) {
		saveOrUpdate(htpsjlItem);
	}

	@Override
	public void deleteHTPSJL(Long htpsjlItemId) {
		delete(findById(htpsjlItemId));
	}
	
}
