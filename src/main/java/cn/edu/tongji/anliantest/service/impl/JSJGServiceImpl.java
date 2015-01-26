package cn.edu.tongji.anliantest.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.JSJGTableDao;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.service.JSJGService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("jsjgService")
public class JSJGServiceImpl implements JSJGService{
	
	private static final Logger logger = LoggerFactory.getLogger(JSJGServiceImpl.class);
	
	@Autowired
	private JSJGTableDao jsjgTableDao;
	
	@Override
	public DataWrapper<JSJGTable> getJSJGTableById(Long jsjgTableId) {
		DataWrapper<JSJGTable> ret = new DataWrapper<JSJGTable>();
		
		ret.setData(jsjgTableDao.getJSJGTableById(jsjgTableId));
		
		return ret;
	}
	
	@Override
	public DataWrapper<JSJGTable> getJSJGTableByProjectId(Long projectId) {
		DataWrapper<JSJGTable> ret = new DataWrapper<JSJGTable>();
		
		ret.setData(jsjgTableDao.getJSJGTableByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<JSJGTable> addJSJGTable(JSJGTable jsjgTable) {
		return null;
	}

	@Override
	public DataWrapper<JSJGTable> updateJSJGTable(JSJGTable jsjgTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJSJGTable(Long jsjgTableId) {
		return null;
	}

	@Override
	public File getJSJGTmpFile(Long projectId) {
		JSJGTable jsjgTable = jsjgTableDao.getJSJGTableByProjectId(projectId);
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		Project project = jsjgTable.getProject();
		String filePath = context.getRealPath("tmp") + "\\" + project.getNumber() + "-" + project.getName() + "-" + "计算过程表" + ".xls";
		File file = new File(filePath);
		if(file.exists()){
			logger.info("下载" + project.getName() + "计算过程表");
			return file;
		}
		else
		    return null;
	}

	@Override
	public File getJSJGFile(Long projectId) {
		return null;
	}
}
