package cn.edu.tongji.anliantest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.CYJCFFItemDao;
import cn.edu.tongji.anliantest.dao.CYJCYJItemDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.document.JCTZDDocument;
import cn.edu.tongji.anliantest.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	private ZYBWHYSItemDao zybwhysItemDao;
	@Autowired
	private CYJCFFItemDao cyjcffItemDao;
	@Autowired
	private CYJCYJItemDao cyjcyjItemDao;
	
	@Override
	public void test() {
		JCTZDDocument.generate((long)1);
	}
}
