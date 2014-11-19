package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.JSJGTableDao;
import cn.edu.tongji.anliantest.model.experiment.JSJGTable;

@Repository
public class JSJGTableDaoImpl extends AbstractHibernateDao<JSJGTable, Long> implements JSJGTableDao{

	protected JSJGTableDaoImpl() {
		super(JSJGTable.class);
	}

	@Override
	public JSJGTable getJSJGTableById(Long jsjgTableId) {
		return findById(jsjgTableId);
	}

	@Override
	public Long addJSJGTable(JSJGTable jsjgTable) {
		return add(jsjgTable);
	}

	@Override
	public void updateJSJGTable(JSJGTable jsjgTable) {
		saveOrUpdate(jsjgTable);
	}

	@Override
	public void deleteJSJGTable(Long jsjgTableId) {
		delete(findById(jsjgTableId));
	}

	@Override
	public JSJGTable getJSJGTableByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<JSJGTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
}
