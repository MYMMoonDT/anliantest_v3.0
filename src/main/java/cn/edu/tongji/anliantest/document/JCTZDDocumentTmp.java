package cn.edu.tongji.anliantest.document;

import java.io.File;

import cn.edu.tongji.anliantest.dao.CYJCFFItemDao;
import cn.edu.tongji.anliantest.dao.CYJCYJItemDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.model.experiment.CYJCFFItem;
import cn.edu.tongji.anliantest.model.experiment.CYJCYJItem;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;

public class JCTZDDocumentTmp {
	public void getZYBWHYSDataFromFile(File file, ZYBWHYSItemDao zybwhysItemDao, CYJCFFItemDao cyjcffItemDao, CYJCYJItemDao cyjcyjItemDao) {
		if(file.exists()) {
			JacobTool jacob = new JacobTool();
			try {
				jacob.openWordDoc(file.getAbsolutePath());
				
				jacob.switchToMainDoc();
				
				jacob.getTable(2);
				int rowsCount = jacob.getRowsCount();
				for(int cellRowIdx = 2; cellRowIdx <= rowsCount; cellRowIdx++) {
					String chineseName = jacob.getCellString(cellRowIdx, 3);
					
					if(chineseName != null) {
						ZYBWHYSItem zybwhysItem = zybwhysItemDao.getZYBWHYSItemByName(chineseName.trim());
						if(zybwhysItem != null) {
							CYJCFFItem cyjcffItem = new CYJCFFItem();
							
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
											cyjcffItem.getSampleStandardList().add(cyjcyjItem);
										}else{
											cyjcffItem.getSampleStandardList().add(cyjcyjItem);
										}
									}
								}
							}
							
							String sampleInstrument = jacob.getCellString(cellRowIdx, 5);
							if(sampleInstrument != null) {
								cyjcffItem.setSampleInstrument(sampleInstrument.trim());
							}
							
							String sampleStrArr = jacob.getCellString(cellRowIdx, 6);
							if(sampleStrArr != null) {
								String[] sampleArr = sampleStrArr.split("\r");
								if(sampleArr.length == 1) {
									cyjcffItem.setSampleComment(sampleArr[0].trim());
								}else if(sampleArr.length == 2) {
									cyjcffItem.setSampleFlowRate(sampleArr[0].trim());
									cyjcffItem.setSampleTime(sampleArr[1].trim());
								}else if(sampleArr.length == 3) {
									cyjcffItem.setSampleFlowRate(sampleArr[0].trim());
									cyjcffItem.setSampleTime(sampleArr[1].trim());
									cyjcffItem.setSampleComment(sampleArr[2].trim());
								}
							}
							
							String sampleCollector = jacob.getCellString(cellRowIdx, 7);
							if(sampleCollector != null) {
								cyjcffItem.setSampleCollector(sampleCollector.trim());
							}
							
							String sampleStorage = jacob.getCellString(cellRowIdx, 8);
							if(sampleStorage != null) {
								cyjcffItem.setSampleStorage(sampleStorage.trim());
							}
							
							String sampleStatus = jacob.getCellString(cellRowIdx, 11);
							if(sampleStatus != null) {
								cyjcffItem.setSampleStatus(sampleStatus.trim());
							}
							
							String sampleMethodAndPeriod = jacob.getCellString(cellRowIdx, 12);
							if(sampleMethodAndPeriod != null) {
								cyjcffItem.setSampleMethodAndPeriod(sampleMethodAndPeriod.trim());
							}
							
							zybwhysItem.getItems().add(cyjcffItem);
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
