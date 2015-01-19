package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.SYSYJLTableDao;
import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;

@Repository
public class SYSYJLTableDaoImpl extends AbstractHibernateDao<SYSYJLTable, Long> implements SYSYJLTableDao{

	protected SYSYJLTableDaoImpl() {
		super(SYSYJLTable.class);
	}

	@Override
	public SYSYJLTable getSYSYJLTableById(Long sysyjlTableId) {
		return findById(sysyjlTableId);
	}

	@Override
	public SYSYJLTable getSYSYJLTableByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<SYSYJLTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addSYSYJLTable(SYSYJLTable sysyjlTable) {
		return add(sysyjlTable);
	}

	@Override
	public void updateSYSYJLTable(SYSYJLTable sysyjlTable) {
		saveOrUpdate(sysyjlTable);
	}

	@Override
	public void deleteSYSYJLTable(Long sysyjlTableId) {
		delete(findById(sysyjlTableId));
	}

}
