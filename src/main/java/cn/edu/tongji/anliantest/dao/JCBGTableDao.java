package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;

public interface JCBGTableDao {
	public Log getJCBGTableById(Long jcbgTableId);
	
	public Long addJCBGTable(JCBGTable jcbgTable);
	
	public void updateJCBGTable(JCBGTable jcbgTable);
	
	public void deleteJCBGTable(Long jcbgTableId);
}
