package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.PJFASHJLItemDao;
import cn.edu.tongji.anliantest.model.PJFASHJLItem;

@Repository
public class PJFASHJLItemDaoImpl extends AbstractHibernateDao<PJFASHJLItem, Long> implements PJFASHJLItemDao{

	protected PJFASHJLItemDaoImpl() {
		super(PJFASHJLItem.class);
	}

	@Override
	public PJFASHJLItem getPJFASHJLById(Long pjfashjlItemId) {
		return findById(pjfashjlItemId);
	}

	@Override
	public Long addPJFASHJL(PJFASHJLItem pjfashjlItem) {
		return add(pjfashjlItem);
	}

	@Override
	public void updatePJFASHJL(PJFASHJLItem pjfashjlItem) {
		saveOrUpdate(pjfashjlItem);
	}

	@Override
	public void deletePJFASHJL(Long pjfashjlItemId) {
		delete(findById(pjfashjlItemId));
	}
	
}
