package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.AuthorityGroupDao;
import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Repository
public class AuthorityGroupDaoImpl extends AbstractHibernateDao<AuthorityGroup, Long> implements AuthorityGroupDao{

	protected AuthorityGroupDaoImpl() {
		super(AuthorityGroup.class);
	}

	@Override
	public AuthorityGroup getAuthorityGroupById(Long authorityGroupId) {
		return findById(authorityGroupId);
	}
	
	@Override
	public AuthorityGroup getAuthorityGroupByDepartment(Department department) {
		List<?> list =  getCurrentSession().createCriteria(AuthorityGroup.class)
				.add(Restrictions.eq("department", department))
				.list();
		
		if(list != null && !list.isEmpty()) {
			return (AuthorityGroup) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long addAuthorityGroup(AuthorityGroup authorityGroup) {
		return add(authorityGroup);
	}

	@Override
	public void updateAuthorityGroup(AuthorityGroup authorityGroup) {
		saveOrUpdate(authorityGroup);
	}

	@Override
	public void deleteAuthorityGroup(Long authorityGroupId) {
		delete(findById(authorityGroupId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		DataWrapper<List<AuthorityGroup>> ret =  new DataWrapper<List<AuthorityGroup>>();
		ret.setData(this.getCurrentSession().createCriteria(AuthorityGroup.class)
					.addOrder(Order.asc("id"))
					.list());
		return ret;
	}
}
