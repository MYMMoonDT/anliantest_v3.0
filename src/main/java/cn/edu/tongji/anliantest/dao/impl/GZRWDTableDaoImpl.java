package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.GZRWDTableDao;
import cn.edu.tongji.anliantest.model.GZRWDTable;

@Repository
public class GZRWDTableDaoImpl extends AbstractHibernateDao<GZRWDTable, Long> implements GZRWDTableDao{

	protected GZRWDTableDaoImpl() {
		super(GZRWDTable.class);
	}

	@Override
	public GZRWDTable getGZRWDById(Long gzrwdTableId) {
		return findById(gzrwdTableId);
	}

	@Override
	public Long addGZRWD(GZRWDTable gzrwdTable) {
		return add(gzrwdTable);
	}

	@Override
	public void updateGZRWD(GZRWDTable gzrwdTable) {
		saveOrUpdate(gzrwdTable);
	}

	@Override
	public void deleteGZRWD(Long gzrwdTableId) {
		delete(findById(gzrwdTableId));
	}

	@Override
	public GZRWDTable getGZRWDByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<GZRWDTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
