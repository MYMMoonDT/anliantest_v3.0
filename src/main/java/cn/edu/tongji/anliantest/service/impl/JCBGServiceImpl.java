package cn.edu.tongji.anliantest.service.impl;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.document.JCBGDocument;
import cn.edu.tongji.anliantest.model.experiment.JCBGGroup;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.service.JCBGService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Service("jcbgService")
public class JCBGServiceImpl implements JCBGService{
	private static final Logger logger = LoggerFactory.getLogger(JCBGServiceImpl.class);
	
	@Autowired
	private JCBGTableDao jcbgTableDao;
	
	@Override
	public DataWrapper<JCBGTable> getJCBGTableById(Long jcbgTableId) {
		return null;
	}

	@Override
	public DataWrapper<JCBGTable> addJCBGTable(JCBGTable jcbgTable) {
		return null;
	}

	@Override
	public DataWrapper<JCBGTable> uploadJCBGTable(Long projectId,
			MultipartFile file) {
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		
		File targetFile = FileUtil.saveFile(context.getRealPath("tmp"),file);
		
		JCBGTable jcbgTable = new JCBGTable();
		ArrayList<JCBGGroup> jcbgGroups = new ArrayList<>();
		
		try {
			JCBGDocument.getJCBGTableFromFile(targetFile, jcbgTable, jcbgGroups);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public DataWrapper<JCBGTable> updateJCBGTable(JCBGTable jcbgTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJCBGTable(Long jcbgTableId) {
		return null;
	}
	
}
