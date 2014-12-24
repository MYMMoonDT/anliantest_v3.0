package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.PJFADao;
import cn.edu.tongji.anliantest.model.PJFA;

@Repository
public class PJFADaoImpl extends AbstractHibernateDao<PJFA, Long> implements PJFADao{

	protected PJFADaoImpl() {
		super(PJFA.class);
	}

	@Override
	public PJFA getPJFAById(Long pjfaId) {
		return findById(pjfaId);
	}

	@Override
	public Long addPJFA(PJFA pjfa) {
		return add(pjfa);
	}

	@Override
	public void updatePJFA(PJFA pjfa) {
		saveOrUpdate(pjfa);
	}

	@Override
	public void deletePJFA(Long pjfaId) {
		delete(findById(pjfaId));
	}

	@Override
	public PJFA getPJFAByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<PJFA> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
