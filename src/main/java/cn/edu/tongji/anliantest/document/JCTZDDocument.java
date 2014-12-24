package cn.edu.tongji.anliantest.document;

import java.io.File;

import cn.edu.tongji.anliantest.dao.CYJCYJItemDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.model.experiment.CYJCYJItem;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;

public class JCTZDDocument {
	public void getZYBWHYSDataFromFile(File file, ZYBWHYSItemDao zybwhysItemDao, CYJCYJItemDao cyjcyjItemDao) {
		if(file.exists()) {
			JacobTool jacob = new JacobTool();
			try {
				jacob.openWordDoc(file.getAbsolutePath());
				
				jacob.switchToMainDoc();
				
				jacob.getTable(1);
				int rowsCount = jacob.getRowsCount();
				for(int cellRowIdx = 2; cellRowIdx <= rowsCount; cellRowIdx++) {
					String chineseName = jacob.getCellString(cellRowIdx, 3);
					
					if(chineseName != null) {
						ZYBWHYSItem zybwhysItem = zybwhysItemDao.getZYBWHYSItemByName(chineseName.trim());
						if(zybwhysItem != null) {
							String cyjcyjStrArr = jacob.getCellString(cellRowIdx, 4);
							if(cyjcyjStrArr != null) {
								String[] cyjcyjArr = cyjcyjStrArr.split("\r");
								if(cyjcyjArr.length == 2) {
									for(int cyjcyjIndex = 0; cyjcyjIndex < 2; cyjcyjIndex++) {
										CYJCYJItem cyjcyjItem = cyjcyjItemDao.getCYJCYJItemByName(cyjcyjArr[cyjcyjIndex].trim());
										if(cyjcyjItem == null) {
											cyjcyjItem = new CYJCYJItem();
											cyjcyjItem.setName(cyjcyjArr[cyjcyjIndex].trim());
											cyjcyjItemDao.addCYJCYJItem(cyjcyjItem);
											zybwhysItem.getSampleStandardList().add(cyjcyjItem);
										}else{
											zybwhysItem.getSampleStandardList().add(cyjcyjItem);
										}
									}
								}
							}
							
							String sampleInstrument = jacob.getCellString(cellRowIdx, 5);
							if(sampleInstrument != null) {
								zybwhysItem.setSampleInstrument(sampleInstrument.trim());
							}
							
							String sampleStrArr = jacob.getCellString(cellRowIdx, 6);
							if(sampleStrArr != null) {
								String[] sampleArr = sampleStrArr.split("\r");
								if(sampleArr.length == 1) {
									zybwhysItem.setSampleComment(sampleArr[0].trim());
								}else if(sampleArr.length == 2) {
									zybwhysItem.setSampleFlowRate(sampleArr[0].trim());
									zybwhysItem.setSampleTime(sampleArr[1].trim());
								}else if(sampleArr.length == 3) {
									zybwhysItem.setSampleFlowRate(sampleArr[0].trim());
									zybwhysItem.setSampleTime(sampleArr[1].trim());
									zybwhysItem.setSampleComment(sampleArr[2].trim());
								}
							}
							
							String sampleCollector = jacob.getCellString(cellRowIdx, 7);
							if(sampleCollector != null) {
								zybwhysItem.setSampleCollector(sampleCollector.trim());
							}
							
							String sampleStorage = jacob.getCellString(cellRowIdx, 8);
							if(sampleStorage != null) {
								zybwhysItem.setSampleStorage(sampleStorage.trim());
							}
							
							String sampleStatus = jacob.getCellString(cellRowIdx, 11);
							if(sampleStatus != null) {
								zybwhysItem.setSampleStatus(sampleStatus.trim());
							}
							
							String sampleMethodAndPeriod = jacob.getCellString(cellRowIdx, 12);
							if(sampleMethodAndPeriod != null) {
								zybwhysItem.setSampleMethodAndPeriod(sampleMethodAndPeriod.trim());
							}
							
							zybwhysItemDao.updateZYBWHYSItem(zybwhysItem);
						}else{
							System.out.println(chineseName);
						}
					}
				}
				
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				jacob.closeWordDoc();
				jacob.closeWord();
			}
		}
	}
}
