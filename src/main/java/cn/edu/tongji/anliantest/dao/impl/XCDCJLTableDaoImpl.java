package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.XCDCJLTableDao;
import cn.edu.tongji.anliantest.model.XCDCJLTable;

@Repository
public class XCDCJLTableDaoImpl extends AbstractHibernateDao<XCDCJLTable, Long> implements XCDCJLTableDao{

	protected XCDCJLTableDaoImpl() {
		super(XCDCJLTable.class);
	}

	@Override
	public XCDCJLTable getXCDCJLById(Long xcdcjlTableId) {
		return findById(xcdcjlTableId);
	}

	@Override
	public Long addXCDCJL(XCDCJLTable xcdcjlTable) {
		return add(xcdcjlTable);
	}

	@Override
	public void updateXCDCJL(XCDCJLTable xcdcjlTable) {
		saveOrUpdate(xcdcjlTable);
	}

	@Override
	public void deleteXCDCJL(Long xcdcjlTableId) {
		delete(findById(xcdcjlTableId));
	}

	@Override
	public XCDCJLTable getXCDCJLByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));

		List<XCDCJLTable> list = findByCriteria(criterions);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
