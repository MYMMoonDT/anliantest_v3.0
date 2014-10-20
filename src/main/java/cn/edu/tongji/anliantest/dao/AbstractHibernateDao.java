package cn.edu.tongji.anliantest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.tongji.anliantest.util.PageResult;

public abstract class AbstractHibernateDao<E, I extends Serializable> {

	private final Class<E> entityClass;

	protected AbstractHibernateDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public I add(E e) {
		return (I) getCurrentSession().save(e);
	}

	@SuppressWarnings("unchecked")
	public E findById(I id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	public void saveOrUpdate(E e) {
        getCurrentSession().merge(e);
	}

	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(List<Criterion> criterions) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (criterions != null && criterions.size() > 0) {
			for(int i = 0; i < criterions.size(); i++) {
				criteria.add(criterions.get(i));
			}
		}
		return criteria.list();
	}
	
	public PageResult<E> findByDefaultPage() {		
		return findByCriteriaByPage(null, PageResult.DEFAULT_CURR_PAGE_NUM, PageResult.DEFAULT_NUM_PER_PAGE);
	}
	
	public PageResult<E> findByPage(int currPageNum, int numPerPage) {
		return findByCriteriaByPage(null, currPageNum, numPerPage);
	}
	
	@SuppressWarnings("unchecked")
	public PageResult<E> findByCriteriaByPage(List<Criterion> criterions, int currPageNum, int numPerPage) {
		PageResult<E> pageResult = new PageResult<E>(currPageNum, numPerPage);
		
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		
		if (criterions != null && criterions.size() < 0) {
			for(int i = 0; i < criterions.size(); i++) {
				criteria.add(criterions.get(i));
			}
		}
		
		criteria.setProjection(Projections.rowCount());
		pageResult.setTotalItemNum(((Long)criteria.uniqueResult()).intValue());
		
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		criteria.setFirstResult(PageResult.getStartOfPage(pageResult.getCurrPageNum(), pageResult.getNumPerPage()));
		criteria.setMaxResults(pageResult.getNumPerPage());
		pageResult.setData(criteria.list());
		
		return pageResult;
	}
}