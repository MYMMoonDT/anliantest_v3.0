package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.CYFATableDao;
import cn.edu.tongji.anliantest.model.experiment.CYFATable;

@Repository
public class CYFATableDaoImpl extends AbstractHibernateDao<CYFATable, Long> implements CYFATableDao{

	protected CYFATableDaoImpl() {
		super(CYFATable.class);
	}

	@Override
	public CYFATable getCYFATableById(Long cyfaTableId) {
		return findById(cyfaTableId);
	}

	@Override
	public Long addCYFATable(CYFATable cyfaTable) {
		return add(cyfaTable);
	}

	@Override
	public void updateCYFATable(CYFATable cyfaTable) {
		saveOrUpdate(cyfaTable);
	}

	@Override
	public void deleteCYFATable(Long cyfaTableId) {
		delete(findById(cyfaTableId));
	}

	@Override
	public CYFATable getCYFATableByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<CYFATable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
}
