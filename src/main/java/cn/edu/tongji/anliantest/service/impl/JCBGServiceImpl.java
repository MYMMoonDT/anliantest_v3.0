package cn.edu.tongji.anliantest.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.dao.JGPJTableDao;
import cn.edu.tongji.anliantest.dao.JSJGTableDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.document.JCBGDocument;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.experiment.JCBGGroup;
import cn.edu.tongji.anliantest.model.experiment.JCBGItem;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.model.experiment.JGPJItem;
import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.model.experiment.JSJGGroup;
import cn.edu.tongji.anliantest.model.experiment.JSJGItem;
import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.service.JCBGService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.CallStatusEnum;
import cn.edu.tongji.anliantest.util.DataRange;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.ErrorCodeEnum;
import cn.edu.tongji.anliantest.util.FileUtil;
import cn.edu.tongji.anliantest.util.TableNumEnum;

@Service("jcbgService")
public class JCBGServiceImpl implements JCBGService{
	private static final Logger logger = LoggerFactory.getLogger(JCBGServiceImpl.class);
	
	@Autowired
	private JCBGTableDao jcbgTableDao;
	@Autowired
	private JSJGTableDao jsjgTableDao;
	@Autowired
	private JGPJTableDao jgpjTableDao;
	@Autowired
	private ZYBWHYSItemDao zybwhysItemDao;
	@Autowired
	private ProjectDao projectDao;
	
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
		String errorMsg = "";
		
		DataWrapper<JCBGTable> ret = new DataWrapper<JCBGTable>();
		
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		
		Project project = projectDao.getProjectById(projectId);
		
		String fileName = project.getNumber() + "-" + project.getName() + "-" + "检测报告" + ".doc";
		File targetFile = FileUtil.saveFile(context.getRealPath("tmp"), file, fileName);
		
		JCBGTable jcbgTable = new JCBGTable();
		ArrayList<JCBGGroup> jcbgGroups = new ArrayList<>();
		
		try {
			JCBGDocument.getJCBGTableFromFile(targetFile, jcbgTable, jcbgGroups);
			
			logger.info("上传" + project.getName() + "项目检测报告");
			
			JCBGTable oldJCBGTable = jcbgTableDao.getJCBGTableByProjectId(projectId);
			JSJGTable oldJSJGTable = jsjgTableDao.getJSJGTableByProjectId(projectId);
			JGPJTable oldJGPJTable = jgpjTableDao.getJGPJTableByProjectId(projectId);
			if(oldJCBGTable != null) {
				jcbgTableDao.deleteJCBGTable(oldJCBGTable.getId());
				jsjgTableDao.deleteJSJGTable(oldJSJGTable.getId());
				jgpjTableDao.deleteJGPJTable(oldJGPJTable.getId());
			}
			
			JSJGTable jsjgTable = new JSJGTable();
			JGPJTable jgpjTable = new JGPJTable();
			
			JCBGGroup jcbgGroup = null;
			Iterator<JCBGGroup> jcbgGroupIter = jcbgGroups.iterator();
			while (jcbgGroupIter.hasNext()) {
				jcbgGroup = jcbgGroupIter.next();
				errorMsg = "<p>车间/岗位：" + jcbgGroup.getWorkshopPosition() + "</p>" +
						   "<p>检测项目：" + jcbgGroup.getZybwhysItem() + "处</p>";
				addJCBGGroupAndCalculate(jcbgGroup, jcbgTable, jsjgTable, jgpjTable);
			}
			
			jcbgTable.setProject(projectDao.getProjectById(projectId));
			jsjgTable.setProject(projectDao.getProjectById(projectId));
			jsjgTable.setTableNum(TableNumEnum.JSJG.getTableNum());
			jgpjTable.setProject(projectDao.getProjectById(projectId));
			jcbgTableDao.addJCBGTable(jcbgTable);
			jsjgTableDao.addJSJGTable(jsjgTable);
			jgpjTableDao.addJGPJTable(jgpjTable);
			
			String filePath = context.getRealPath("tmp") + "\\" + project.getNumber() + "-" + project.getName() + "-" + "计算过程表" + ".doc";
			JCBGDocument.generateJSJGFile(jsjgTable, filePath);
			logger.info("生成" + project.getName() + "计算过程表");
			filePath = context.getRealPath("tmp") + "\\" + project.getNumber() + "-" + project.getName() + "-" + "结果与判定表" + ".doc";
			JCBGDocument.generateJGPJFile(jgpjTable, filePath);
			logger.info("生成" + project.getName() + "结果与判定表");
			
		} catch (Exception e) {
			ret.setCallStatus(CallStatusEnum.FAILED);
			ret.setErrorCode(ErrorCodeEnum.File_Format_Wrong);
			if(e.getMessage() != null)
				ret.setErrorMsg("错误：" + errorMsg + e.getMessage().toString());
			else
				ret.setErrorMsg("错误：" + errorMsg);
		}
		
		return ret;
	}
	
	private void addJCBGGroupAndCalculate(JCBGGroup jcbgGroup, JCBGTable jcbgTable, JSJGTable jsjgTable, JGPJTable jgpjTable) throws Exception{
		DataRange<BigDecimal> range = new DataRange<BigDecimal>();
		ArrayList<JCBGItem> jcbgItemList = getJCBGItemListFromJCBGGroup(jcbgGroup, range);
		jcbgTable.getItems().addAll(jcbgItemList);
		
		ArrayList<Integer> dayCountList = getDayCountList(jcbgItemList);
		
		ArrayList<JSJGGroup> jsjgGroupList = calculateJSJGData(jsjgTable, jcbgItemList, dayCountList);
		
		calculateJGPJData(jgpjTable, jcbgItemList,  dayCountList, jsjgGroupList, range);
	}

	private ArrayList<JCBGItem> getJCBGItemListFromJCBGGroup(JCBGGroup jcbgGroup, DataRange<BigDecimal> range) throws Exception{
		ArrayList<JCBGItem> jcbgItemList = new ArrayList<JCBGItem>();
		
		if (Character.isDigit(jcbgGroup.getResult()[0][0].charAt(0))) {
			range.setStartType("=");
			range.setStart(BigDecimal.valueOf(Double.valueOf(jcbgGroup.getResult()[0][0])));			
		} else {
			range.setStartType("<");
			range.setStart(BigDecimal.valueOf(Double.valueOf(jcbgGroup.getResult()[0][0].substring(1))));
		}
		range.setEnd(range.getStart());
		range.setEndType(range.getStartType());
		
		for (int i = 0; i < 3; i++) {
			if (jcbgGroup.getTestDate()[i] != null) {
				if (i > 0 && jcbgGroup.getTestDate()[i].equals(jcbgGroup.getTestDate()[i-1]))
					throw new Exception("采样日期重复");
				for (int j = 0; j < 4; j++) {
					if (jcbgGroup.getResult()[i][j] != null && !jcbgGroup.getResult()[i][j].equals("")) {
						JCBGItem temp = new JCBGItem();
						try {
							temp.setTouchTime(BigDecimal.valueOf(Double.valueOf(jcbgGroup.getTouchTime()[i][j])));
						} catch(Exception e) {
							System.out.println(jcbgGroup.getSampleNum()[i][j]);
						}
						String touchTime = jcbgGroup.getTouchTime()[i][j];
						temp.setTouchTimeScale(getScaleFromNumStr(touchTime));
						temp.setCollectTime(jcbgGroup.getCollectTime()[i][j]);
						
						String result = jcbgGroup.getResult()[i][j];
	
						if (Character.isDigit(result.charAt(0))) {
							temp.setResult(BigDecimal.valueOf(Double.valueOf(result)));
							temp.setResultType("=");
						} else {
							temp.setResult(BigDecimal.valueOf(Double.valueOf(result.substring(1))));
							temp.setResultType("<");
						}
	
						if (range.getStart().compareTo(temp.getResult()) > 0) {
							range.setStart(temp.getResult());
							range.setStartType(temp.getResultType());
						} else if (range.getStart().compareTo(temp.getResult()) == 0
								&& range.getStartType().equals("=") && temp.getResultType().equals("<")) {
							range.setStartType("<");
						}
						if (range.getEnd().compareTo(temp.getResult()) < 0) {
							range.setEnd(temp.getResult());
							range.setEndType(temp.getResultType());
						} else if (range.getEnd().compareTo(temp.getResult()) == 0
								&& range.getEndType().equals("<") && temp.getResultType().equals("=")) {
							range.setEndType("=");
						}
						temp.setSampleNum(jcbgGroup.getSampleNum()[i][j]);
						
						temp.setZybwhysItem(zybwhysItemDao.getZYBWHYSItemByName(jcbgGroup.getZybwhysItem()));
						
						temp.setTestDate(jcbgGroup.getTestDate()[i]);
						temp.setWorkshopPosition(jcbgGroup.getWorkshopPosition());
						temp.setResultScale(getScaleFromNumStr(result));
						if (jcbgGroup.getZybwhysItemDetailName() != null && jcbgGroup.getZybwhysItemDetailName().length() != 0) {
							temp.setZybwhysItemDetailName(jcbgGroup.getZybwhysItemDetailName());
						}
						jcbgItemList.add(temp);
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		
		return jcbgItemList;
	}
	
	private ArrayList<Integer> getDayCountList(
			ArrayList<JCBGItem> jcbgItemList) {
		ArrayList<Integer> dayCount = new ArrayList<Integer>();
		Integer count = 0;
		Date prevDate = new Date(0);
		for (JCBGItem item : jcbgItemList) {
			if (!item.getTestDate().equals(prevDate)) {
				if (count != 0) {
					dayCount.add(count);
				}
				count = 0;
				prevDate = item.getTestDate();
			}
			count++;
						
		}
		dayCount.add(count);
		return dayCount;
	}
	
	private ArrayList<JSJGGroup> calculateJSJGData(JSJGTable jsjgTable, ArrayList<JCBGItem> jcbgItemList, ArrayList<Integer> dayCountList) {
		int offset = 0;
		ArrayList<JSJGGroup> jsjgGroupList = new ArrayList<JSJGGroup>();
		
		for (Integer cnt : dayCountList) {
			JSJGGroup jsjgGroup = new JSJGGroup();
			JCBGItem jcbgItem = jcbgItemList.get(offset);
			ZYBWHYSItem zybwhysItem = jcbgItem.getZybwhysItem();
			
			jsjgGroup.setZybwhysItem(zybwhysItem);
			jsjgGroup.setZybwhysItemDetailName(jcbgItem.getZybwhysItemDetailName());
			jsjgGroup.setTestDate(jcbgItem.getTestDate());
			jsjgGroup.setWorkshopPosition(jcbgItem.getWorkshopPosition());
			
			BigDecimal mac = null;
			BigDecimal ctwa = new BigDecimal(0);
			BigDecimal cstel = new BigDecimal(0);
			BigDecimal om = new BigDecimal(0);
			
			int temp = offset;
			boolean hasEqual = false, hasLess = false;
			for (int i = 0; i < cnt; i++) {
				jcbgItem = jcbgItemList.get(offset);
				if (jcbgItem.getResultType().equals("=")) {
					hasEqual = true;
				} else {
					hasLess = true;
				}
				offset++;
			}
			
			offset = temp;
			
			for (int i = 0; i < cnt; i++) {
				jcbgItem = jcbgItemList.get(offset);
				JSJGItem jsjgItem = new JSJGItem();
				jsjgItem.setResult(jcbgItem.getResult());
				jsjgItem.setResultScale(jcbgItem.getResultScale());
				jsjgItem.setResultType(jcbgItem.getResultType());
				jsjgItem.setSampleNum(jcbgItem.getSampleNum());
				jsjgItem.setTouchTime(jcbgItem.getTouchTime());
				jsjgItem.setTouchTimeScale(jcbgItem.getTouchTimeScale());
				jsjgGroup.getItems().add(jsjgItem);
				
				BigDecimal calculateResult = jcbgItem.getResult().multiply(BigDecimal.valueOf(jcbgItem.getCollectTime()/15.0));
				if (hasLess && hasEqual) {
					if (!jsjgItem.getResultType().equals("="))
						calculateResult = new BigDecimal(calculateResult.doubleValue()/2);
					jsjgGroup.setResultType("=");
				} else if (hasLess){
					jsjgGroup.setResultType("<");
				} else {
					jsjgGroup.setResultType("=");
				}
				ctwa = ctwa.add(calculateResult.multiply(jcbgItem.getTouchTime()));
				cstel = cstel.max(jcbgItem.getResult());
				offset++;
			}
			BigDecimal std_mac = zybwhysItem.getMAC();
			BigDecimal pc_twa = zybwhysItem.getPC_TWA();
			BigDecimal pc_stel = zybwhysItem.getPC_STEL();
			BigDecimal std_om = zybwhysItem.getOM();
			if (hasLess && !hasEqual) {
				ctwa = cstel = mac = jcbgItem.getResult();
			} else {
				mac = cstel;
				ctwa = new BigDecimal(ctwa.doubleValue()/8);				
			}
			if (pc_twa != null)
				om = new BigDecimal(cstel.doubleValue()/pc_twa.doubleValue());
			
			boolean passed = true;
			if (std_mac != null) {
				if (hasLess && !hasEqual) {
					jsjgGroup.setMAC_Scale(jcbgItem.getResultScale());
				} else {
					int macScale = zybwhysItem.getMAC_Scale()+1;
					mac = rounding(mac, macScale);
					jsjgGroup.setMAC_Scale(mac.scale());
				}
				jsjgGroup.setMAC(mac);
				if (mac.compareTo(std_mac) > 0) {
					passed = false;
				}
			}
			if (pc_twa != null) {
				if (hasLess && !hasEqual) {
					jsjgGroup.setPC_TWA_Scale(jcbgItem.getResultScale());
				} else {
					int ctwaScale = zybwhysItem.getPC_TWA_Scale()+1;
					ctwa = rounding(ctwa, ctwaScale);
					jsjgGroup.setPC_TWA_Scale(ctwa.scale());
				}
				jsjgGroup.setPC_TWA(ctwa);
				if (ctwa.compareTo(pc_twa) > 0) {
					passed = false;
				}
			}
				
			if (pc_stel != null) {
				if (hasLess && !hasEqual) {
					jsjgGroup.setPC_STEL_Scale(jcbgItem.getResultScale());
				} else {
					int cstelScale = zybwhysItem.getPC_STEL_Scale()+1;
					cstel = rounding(cstel, cstelScale);
					jsjgGroup.setPC_STEL_Scale(cstel.scale());
				}
				jsjgGroup.setPC_STEL(cstel);
				if (cstel.compareTo(pc_stel) > 0) {
					passed = false;
				}
			}
			if (std_om != null) {
				int omScale = zybwhysItem.getOM_Scale() + 1;
				om = rounding(om, omScale);
				jsjgGroup.setOM_Scale(om.scale());
				jsjgGroup.setOM(om);
				if (om.compareTo(std_om) > 0) {
					passed = false;
				}
			}
				
			if (passed) {
				jsjgGroup.setResult("合格");
			} else {
				jsjgGroup.setResult("不合格");
			}
			jsjgTable.getGroups().add(jsjgGroup);
			jsjgGroupList.add(jsjgGroup);
		}
		return jsjgGroupList;
	}
	
	private void calculateJGPJData(JGPJTable jgpjTable, ArrayList<JCBGItem> jcbgItemList, ArrayList<Integer> dayCountList, ArrayList<JSJGGroup> jsjgGroupList, DataRange<BigDecimal> range){
		int sampleCount = jcbgItemList.size();
		BigDecimal maxGroupTouchTime = new BigDecimal(0);
		int maxGroupTouchTimeScale = 0;
		int offset = 0;
		for (Integer cnt : dayCountList) {
			BigDecimal groupTouchTime = new BigDecimal(0);
			int touchTimeScale = 0;
			for (int i= offset; i < offset+cnt; i++) {
				groupTouchTime = groupTouchTime.add(jcbgItemList.get(i).getTouchTime());
				if (jcbgItemList.get(i).getTouchTimeScale() > touchTimeScale)
					touchTimeScale = jcbgItemList.get(i).getTouchTimeScale();
			}
			offset += cnt;
			if (maxGroupTouchTime.compareTo(groupTouchTime) < 0) {
				maxGroupTouchTime = groupTouchTime;
				maxGroupTouchTimeScale = touchTimeScale;
			}
		}
		
		JGPJItem jgpjItem = new JGPJItem();
		jgpjItem.setZybwhysItem(jsjgGroupList.get(0).getZybwhysItem());
		jgpjItem.setWorkshopPosition(jsjgGroupList.get(0).getWorkshopPosition());
		jgpjItem.setSampleCount(sampleCount);
		jgpjItem.setTouchTime(maxGroupTouchTime);
		jgpjItem.setTouchTimeScale(maxGroupTouchTimeScale);
		jgpjItem.setResultRangeScale(jcbgItemList.get(0).getResultScale());
		jgpjItem.setZybwhysItemDetailName(jcbgItemList.get(0).getZybwhysItemDetailName());
		jgpjItem.setResultRangeStart(range.getStart());
		jgpjItem.setResultRangeStartType(range.getStartType());
		jgpjItem.setResultRangeEnd(range.getEnd());
		jgpjItem.setResultRangeEndType(range.getEndType());
		
		BigDecimal mac = new BigDecimal(0);
		BigDecimal ctwa = new BigDecimal(0);
		BigDecimal cstel = new BigDecimal(0);
		BigDecimal om = new BigDecimal(0);
		
		int macScale = 0, ctwaScale = 0, cstelScale = 0, omScale = 0;
		String resultType = null;
		boolean passed = true;
		for (JSJGGroup group : jsjgGroupList) {
			if (group.getResult().equals("不合格")) {
				passed = false;
			}
			if (group.getMAC() != null) {
				if (mac.compareTo(group.getMAC()) < 0) {
					mac = group.getMAC();
					macScale = group.getMAC_Scale();
					resultType = group.getResultType();
				}
			} else {
				mac = null;
			}
			if (group.getPC_TWA() != null) {
				if (ctwa.compareTo(group.getPC_TWA()) < 0) {
					ctwa = group.getPC_TWA();
					ctwaScale = group.getPC_TWA_Scale();
					resultType = group.getResultType();
				}
			} else {
				ctwa = null;
			}
			if (group.getPC_STEL() != null) {
				if (cstel.compareTo(group.getPC_STEL()) < 0) {
					cstel = group.getPC_STEL();
					cstelScale = group.getPC_STEL_Scale();
					resultType = group.getResultType();
				}
			} else {
				cstel = null;
			}
			if (group.getOM() != null) {
				if (om.compareTo(group.getOM()) < 0) {
					om = group.getOM();
					omScale = group.getOM_Scale();
					resultType = group.getResultType();
				}
			} else {
				om = null;
			}
		}
		jgpjItem.setMAC(mac);
		jgpjItem.setPC_TWA(ctwa);
		jgpjItem.setPC_STEL(cstel);
		jgpjItem.setOM(om);
		jgpjItem.setMAC_Scale(macScale);
		jgpjItem.setPC_TWA_Scale(ctwaScale);
		jgpjItem.setPC_STEL_Scale(cstelScale);
		jgpjItem.setOM_Scale(omScale);
		jgpjItem.setResultType(resultType);
		
		if (passed) {
			jgpjItem.setResult("合格");
		} else {
			jgpjItem.setResult("不合格");
		}
		jgpjTable.getItems().add(jgpjItem);
	}
	
	@Override
	public DataWrapper<JCBGTable> updateJCBGTable(JCBGTable jcbgTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJCBGTable(Long jcbgTableId) {
		return null;
	}

	/*
	 * 解析字符串得到小数位。
	 */
	private int getScaleFromNumStr(String num) {
		int index = num.indexOf('.');
		if (index == -1)
			return 0;
		else
			return num.length() - index - 1;
	}
	
	/*
	 * 修约
	 * 四舍六入五成双，不能为零
	 */
	private BigDecimal rounding(BigDecimal v, int scale) {
		BigDecimal zero = new BigDecimal(0);
		v = v.setScale(8, RoundingMode.HALF_UP);
		if (v.doubleValue() == 0.0) {
			return zero.setScale(scale);
		}
		BigDecimal u = null;
		do {
			u = v.setScale(scale++, RoundingMode.HALF_EVEN);
		} while (u.compareTo(zero) == 0);
		return u;
	}

}
