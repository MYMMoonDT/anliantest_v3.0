package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;

@Repository
public class ZYBWHYSItemDaoImpl extends AbstractHibernateDao<ZYBWHYSItem, Long> implements ZYBWHYSItemDao{

	protected ZYBWHYSItemDaoImpl() {
		super(ZYBWHYSItem.class);
	}

	@Override
	public ZYBWHYSItem getZYBWHYSItemById(Long zybwhysItemId) {
		return findById(zybwhysItemId);
	}
	
	@Override
	public ZYBWHYSItem getZYBWHYSItemByIName(String zybwhysItemName) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("chineseName", zybwhysItemName));

		List<ZYBWHYSItem> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addZYBWHYSItem(ZYBWHYSItem zybwhysItem) {
		return add(zybwhysItem);
	}

	@Override
	public void updateZYBWHYSItem(ZYBWHYSItem zybwhysItem) {
		saveOrUpdate(zybwhysItem);
	}

	@Override
	public void deleteZYBWHYSItem(Long zybwhysItemId) {
		delete(findById(zybwhysItemId));
	}

}
