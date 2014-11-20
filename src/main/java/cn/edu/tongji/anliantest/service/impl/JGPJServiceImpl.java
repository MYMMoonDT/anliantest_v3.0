package cn.edu.tongji.anliantest.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.JGPJTableDao;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.service.JGPJService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("jgpjService")
public class JGPJServiceImpl implements JGPJService{

	private static final Logger logger = LoggerFactory.getLogger(JGPJServiceImpl.class);
	
	@Autowired
	private JGPJTableDao jgpjTableDao;
	
	@Override
	public DataWrapper<JGPJTable> getJGPJTableById(Long jgpjTableId) {
		return null;
	}

	@Override
	public DataWrapper<JGPJTable> addJGPJTable(JGPJTable jgpjTable) {
		return null;
	}

	@Override
	public DataWrapper<JGPJTable> updateJGPJTable(JGPJTable jgpjTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJGPJTable(Long jgpjTableId) {
		return null;
	}

	@Override
	public File getJGPJFile(Long projectId) {
		JGPJTable jgpjTable = jgpjTableDao.getJGPJTableByProjectId(projectId);
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		Project project = jgpjTable.getProject();
		String filePath = context.getRealPath("tmp") + "\\" + project.getNumber() + "-" + project.getName() + "-" + "结果与判定表" + ".doc";
		File file = new File(filePath);
		if(file.exists()){
			logger.info("下载" + project.getName() + "结果与判定表");
			return file;
		}
		else
		    return null;
	}

}
