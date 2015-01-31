package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.AuthorityItemDao;
import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Repository
public class AuthorityItemDaoImpl extends AbstractHibernateDao<AuthorityItem, Long> implements AuthorityItemDao{

	protected AuthorityItemDaoImpl() {
		super(AuthorityItem.class);
	}

	@Override
	public AuthorityItem getAuthorityItemById(Long authorityItemId) {
		return findById(authorityItemId);
	}

	@Override
	public Long addAuthorityItem(AuthorityItem authorityItem) {
		return add(authorityItem);
	}

	@Override
	public void updateAuthorityItem(AuthorityItem authorityItem) {
		saveOrUpdate(authorityItem);
	}

	@Override
	public void deleteAuthorityItem(Long authorityItemId) {
		delete(findById(authorityItemId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems() {
		DataWrapper<List<AuthorityItem>> ret =  new DataWrapper<List<AuthorityItem>>();
		ret.setData(this.getCurrentSession().createCriteria(AuthorityItem.class)
					.addOrder(Order.asc("id"))
					.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
					.list());
		return ret;
	}
}
