package cn.edu.tongji.anliantest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.CYJCFFItemDao;
import cn.edu.tongji.anliantest.dao.CYJCYJItemDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.document.CYFADocument;
import cn.edu.tongji.anliantest.document.JCBGDocument2;
import cn.edu.tongji.anliantest.document.JCTZDDocument2;
import cn.edu.tongji.anliantest.document.SYSYJLDocument;
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
//		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
//		File file = new File(context.getRealPath("data.docx"));
//		JCTZDDocument jctzdDocument = new JCTZDDocument();
//		jctzdDocument.getZYBWHYSDataFromFile(file, zybwhysItemDao, cyjcffItemDao, cyjcyjItemDao);
		CYFADocument.generate((long) 1);
		SYSYJLDocument.generate((long) 1);
		JCTZDDocument2.generate((long) 1);
		JCBGDocument2.generate((long) 8);
	}

}
