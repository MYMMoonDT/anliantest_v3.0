package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CYJCFFItemDao;
import cn.edu.tongji.anliantest.model.experiment.CYJCFFItem;

@Repository
public class CYJCFFItemDaoImpl extends AbstractHibernateDao<CYJCFFItem, Long> implements CYJCFFItemDao{

	protected CYJCFFItemDaoImpl() {
		super(CYJCFFItem.class);
	}

	@Override
	public CYJCFFItem getCYJCFFItemById(Long cyjcffItemId) {
		return findById(cyjcffItemId);
	}

	@Override
	public Long addCYJCFFItem(CYJCFFItem cyjcffItem) {
		return add(cyjcffItem);
	}

	@Override
	public void updateCYJCFFItem(CYJCFFItem cyjcffItem) {
		saveOrUpdate(cyjcffItem);
	}

	@Override
	public void deleteCYJCFFItem(Long cyjcffItemId) {
		delete(findById(cyjcffItemId));
	}
	
}
