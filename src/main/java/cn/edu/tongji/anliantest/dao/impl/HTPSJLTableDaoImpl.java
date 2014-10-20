package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.HTPSJLTableDao;
import cn.edu.tongji.anliantest.model.HTPSJLTable;

@Repository
public class HTPSJLTableDaoImpl extends AbstractHibernateDao<HTPSJLTable, Long> implements HTPSJLTableDao{

	protected HTPSJLTableDaoImpl() {
		super(HTPSJLTable.class);
	}

	@Override
	public HTPSJLTable getHTPSJLById(Long htpsjlTableId) {
		return findById(htpsjlTableId);
	}
	
	@Override
	public HTPSJLTable getHTPSJLByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<HTPSJLTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
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
