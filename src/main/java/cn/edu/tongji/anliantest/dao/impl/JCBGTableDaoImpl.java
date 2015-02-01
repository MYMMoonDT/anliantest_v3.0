package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;

@Repository
public class JCBGTableDaoImpl extends AbstractHibernateDao<JCBGTable, Long> implements JCBGTableDao{

	protected JCBGTableDaoImpl() {
		super(JCBGTable.class);
	}

	@Override
	public JCBGTable getJCBGTableById(Long jcbgTableId) {
		return findById(jcbgTableId);
	}
	
	@Override
	public Long addJCBGTable(JCBGTable jcbgTable) {
		return add(jcbgTable);
	}

	@Override
	public void updateJCBGTable(JCBGTable jcbgTable) {
		saveOrUpdate(jcbgTable);
	}

	@Override
	public void deleteJCBGTable(Long jcbgTableId) {
		delete(findById(jcbgTableId));
	}

	@Override
	public JCBGTable getJCBGTableByProjectId(Long projectId) {
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<JCBGTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
		
	}

}
