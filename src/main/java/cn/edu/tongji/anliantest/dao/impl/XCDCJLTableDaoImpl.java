package cn.edu.tongji.anliantest.dao.impl;

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
		updateXCDCJL(xcdcjlTable);
	}

	@Override
	public void deleteXCDCJL(Long xcdcjlTableId) {
		delete(findById(xcdcjlTableId));
	}

}
