package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.JGPJTableDao;
import cn.edu.tongji.anliantest.model.experiment.JGPJTable;

@Repository
public class JGPJTableDaoImpl extends AbstractHibernateDao<JGPJTable, Long> implements JGPJTableDao{

	protected JGPJTableDaoImpl() {
		super(JGPJTable.class);
	}

	@Override
	public JGPJTable getJGPJTableById(Long jgpjTableId) {
		return findById(jgpjTableId);
	}
	
	@Override
	public JGPJTable getJGPJTableByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<JGPJTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addJGPJTable(JGPJTable jgpjTable) {
		return add(jgpjTable);
	}

	@Override
	public void updateJGPJTable(JGPJTable jgpjTable) {
		saveOrUpdate(jgpjTable);
	}

	@Override
	public void deleteJGPJTable(Long jgpjTableId) {
		delete(findById(jgpjTableId));
	}

}
