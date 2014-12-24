package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.JCTZDTableDao;
import cn.edu.tongji.anliantest.model.JCTZDTable;

@Repository
public class JCTZDTableDaoImpl extends AbstractHibernateDao<JCTZDTable, Long> implements JCTZDTableDao{

	protected JCTZDTableDaoImpl() {
		super(JCTZDTable.class);
	}

	@Override
	public JCTZDTable getJCTZDTableById(Long jctzdTableId) {
		return findById(jctzdTableId);
	}

	@Override
	public JCTZDTable getJCTZDTableByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<JCTZDTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addJCTZDTable(JCTZDTable jctzdTable) {
		return add(jctzdTable);
	}

	@Override
	public void updateJCTZDTable(JCTZDTable jctzdTable) {
		saveOrUpdate(jctzdTable);
	}

	@Override
	public void deleteJCTZDTable(Long jctzdTableId) {
		delete(findById(jctzdTableId));
	}

}
