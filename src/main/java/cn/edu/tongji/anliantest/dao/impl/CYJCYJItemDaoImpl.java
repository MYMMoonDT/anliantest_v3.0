package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CYJCYJItemDao;
import cn.edu.tongji.anliantest.model.experiment.CYJCYJItem;

@Repository
public class CYJCYJItemDaoImpl extends AbstractHibernateDao<CYJCYJItem, Long> implements CYJCYJItemDao{

	protected CYJCYJItemDaoImpl() {
		super(CYJCYJItem.class);
	}

	@Override
	public CYJCYJItem getCYJCYJItemById(Long cyjcyjItemId) {
		return findById(cyjcyjItemId);
	}
	
	@Override
	public CYJCYJItem getCYJCYJItemByName(String cyjcyjItemName) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("name", cyjcyjItemName));

		List<CYJCYJItem> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addCYJCYJItem(CYJCYJItem cyjcyjItem) {
		return add(cyjcyjItem);
	}

	@Override
	public void updateCYJCYJItem(CYJCYJItem cyjcyjItem) {
		saveOrUpdate(cyjcyjItem);
	}

	@Override
	public void deleteCYJCYJItem(Long cyjcyjItemId) {
		delete(findById(cyjcyjItemId));
	}

}
