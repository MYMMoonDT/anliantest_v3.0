package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.PJFASHJLTableDao;
import cn.edu.tongji.anliantest.model.PJFASHJLTable;

@Repository
public class PJFASHJLTableDaoImpl extends AbstractHibernateDao<PJFASHJLTable, Long> implements PJFASHJLTableDao{

	protected PJFASHJLTableDaoImpl() {
		super(PJFASHJLTable.class);
	}

	@Override
	public PJFASHJLTable getPJFASHJLById(Long pjfashjlTableId) {
		return findById(pjfashjlTableId);
	}

	@Override
	public PJFASHJLTable getPJFASHJLByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<PJFASHJLTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long addPJFASHJL(PJFASHJLTable pjfashjlTable) {
		return add(pjfashjlTable);
	}

	@Override
	public void updatePJFASHJL(PJFASHJLTable pjfashjlTable) {
		saveOrUpdate(pjfashjlTable);
	}

	@Override
	public void deletePJFASHJL(Long pjfashjlTableId) {
		delete(findById(pjfashjlTableId));
	}
	
}
