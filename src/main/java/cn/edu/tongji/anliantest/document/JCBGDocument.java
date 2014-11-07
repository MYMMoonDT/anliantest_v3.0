package cn.edu.tongji.anliantest.document;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.edu.tongji.anliantest.model.experiment.JCBGGroup;
import cn.edu.tongji.anliantest.model.experiment.JCBGSiO2Item;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;

import com.jacob.com.Dispatch;

public class JCBGDocument {
	private static int[] ColsToCheck = {7, 2, 1};
	
	
	public static void getJCBGTableFromFile(File file, JCBGTable jcbgTable, ArrayList<JCBGGroup> itemDataList) throws Exception {
		int cellRowIdx = -1;
		JacobTool jacob = new JacobTool();
		
		try {
			jacob.openWordDoc(file.getAbsolutePath());
			
			String temp;
			jacob.switchToHeader();
			Dispatch selection = jacob.getSelection();
			Dispatch.call(selection, "WholeStory");
			temp = Dispatch.get(selection, "Text").getString();
			jcbgTable.setTableNum(temp.substring(temp.indexOf('：')+1, temp.indexOf('\r')));
			jacob.switchToMainDoc();
			
			jacob.getTable(2);
			Date[] dates = null;
			
			dates = getDate(jacob.getCellString(jacob.getRowsCount(), 1));
			jcbgTable.setSampleStartDate(dates[0]);
			if (dates.length == 2) {
				jcbgTable.setSampleEndDate(dates[1]);
			}
			dates = getDate(jacob.getCellString(jacob.getRowsCount(), 3));
			jcbgTable.setTestStartDate(dates[0]);
			if (dates.length == 2) {
				jcbgTable.setTestEndDate(dates[1]);
			}
			dates = getDate(jacob.getCellString(jacob.getRowsCount(), 4));
			jcbgTable.setReportDate(dates[0]);
			
			jacob.getTable(4);
			int tableRow = jacob.getRowsCount();
			ArrayList<JCBGSiO2Item> sio2List = new ArrayList<JCBGSiO2Item>();
			for (cellRowIdx = 2; cellRowIdx <= tableRow; cellRowIdx++) {
				if (jacob.getCellString(cellRowIdx, 2).equals("游离二氧化硅")) {
					JCBGSiO2Item item = new JCBGSiO2Item();
					item.setWorkshopPosition(jacob.getCellString(cellRowIdx, 1));
					item.setSampleNum(jacob.getCellString(cellRowIdx, 3));
					item.setResult(Double.valueOf(jacob.getCellString(cellRowIdx, 4)));
					sio2List.add(item);
				}
			}
			
			jacob.getTable(3);
			Date[] testTime = new Date[3];
			String[][] testSampleNum = new String[3][4];
			String[][] testResult = new String[3][4];
			String[][] testTouchTime = new String[3][4];
			Integer[][] testCollectTime = new Integer[3][4];
			tableRow = jacob.getRowsCount();
			int i = -1, j = 0, prevRowIdx = -1 ;
			int prevTestTime = 1, prevSubstance = 1, prevWorkshopJob = 2;
			int[] prevRows = {prevTestTime, prevSubstance, prevWorkshopJob};
			JCBGGroup itemData = new JCBGGroup();
			for (cellRowIdx = 2; cellRowIdx <= tableRow; cellRowIdx++) {
				if (jacob.isCellStrExisted(cellRowIdx, 7)) {
					//DONE use prev to judge if time is same
					if (!isSameCells(jacob, cellRowIdx, prevRows, ColsToCheck, 0)) {
						i++;
						j = 0;
						prevRows[0] = cellRowIdx;
						//prevTestTime = cellRowIdx;
						if (jacob.isCellStrExisted(cellRowIdx, 2)) {
							//DONE use prev to judge if time is same
							if (!isSameCells(jacob, cellRowIdx, prevRows, ColsToCheck, 1)) {
								if (prevRowIdx != -1) {
									itemData = new JCBGGroup();
									i = 0;
									j = 0;
									testTime = new Date[3];
									testSampleNum = new String[3][4];
									testResult = new String[3][4];
									testTouchTime = new String[3][4];
									testCollectTime = new Integer[3][4];
								}
								prevRowIdx=1;
								
								itemData.setTestDate(testTime);
								itemData.setSampleNum(testSampleNum);
								itemData.setResult(testResult);
								itemData.setTouchTime(testTouchTime);
								itemData.setCollectTime(testCollectTime);
								
								if (jacob.isCellStrExisted(cellRowIdx, 1)) {
									itemData.setWorkshopPosition(jacob.getCellString(cellRowIdx, 1));
									prevRows[2] = cellRowIdx;
								} else {
									itemData.setWorkshopPosition(jacob.getCellString(prevRows[2], 1));
								}
								String sub = jacob.getCellString(cellRowIdx, 2);
								String[] r = splitSubName(sub);
								if (r[0].equals("矽尘（总尘）")) {
									double percent = sio2List.get(sio2List.indexOf(new JCBGSiO2Item(itemData.getWorkshopPosition()))).getResult();
									if (percent > 10 && percent <= 50) {
										itemData.setZybwhysItem("矽尘（总尘）（10%≤游离SiO2含量≤50%）");
									} else if (percent > 50 && percent <=80) {
										itemData.setZybwhysItem("矽尘（总尘）（50%＜游离SiO2含量≤80%）");
									} else if (percent > 80) {
										itemData.setZybwhysItem("矽尘（总尘）（游离SiO2含量＞80%）");
									} else {
										System.err.println("矽尘（总尘）w% < 10%");
										throw new Exception("矽尘（总尘）w% < 10%");
									}
								} else if (r[0].equals("矽尘（呼尘）")) {
									double percent = sio2List.get(sio2List.indexOf(new JCBGSiO2Item(itemData.getWorkshopPosition()))).getResult();
									if (percent > 10 && percent <= 50) {
										itemData.setZybwhysItem("矽尘（呼尘）（10%≤游离SiO2含量≤50%）");
									} else if (percent > 50 && percent <=80) {
										itemData.setZybwhysItem("矽尘（呼尘）（50%＜游离SiO2含量≤80%）");
									} else if (percent > 80) {
										itemData.setZybwhysItem("矽尘（呼尘）（游离SiO2含量＞80%）");
									} else {
										System.err.println("矽尘（呼尘）w% < 10%");
										throw new Exception("矽尘（总尘）w% < 10%");
									}
								} else {
									itemData.setZybwhysItem(r[0]);
									itemData.setZybwhysItemDetailName(r[1]);
								}
								
								itemDataList.add(itemData);
								prevRows[1] = cellRowIdx;
							}
						}
						// DONE Deal with different years
						testTime[i] = string2Date(jacob.getCellString(cellRowIdx, 7));
						
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(jcbgTable.getSampleStartDate().getTime());
						int year = c.get(Calendar.YEAR);
						c.setTime(testTime[i]);
						if (c.get(Calendar.YEAR) == 1970) {
							c.set(Calendar.YEAR, year);
							testTime[i] = c.getTime();
						}
					}
				}
				testSampleNum[i][j] = jacob.getCellString(cellRowIdx, 3);
				testResult[i][j] = jacob.getCellString(cellRowIdx, 4);
				testTouchTime[i][j] = jacob.getCellString(cellRowIdx, 5);
				temp = jacob.getCellString(cellRowIdx, 6);
				testCollectTime[i][j] = Integer.valueOf(getNumString(temp));
				j++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			StringBuffer message = new StringBuffer();
			message.append("表");
			message.append(jacob.tableNum);
			message.append(" 第");
			message.append(jacob.pageNum);
			message.append("页 第");
			if (jacob.tableNum < 3) 
				message.append(jacob.rowNum);
			else {
				if (jacob.colNum == ColsToCheck[0]
						|| jacob.colNum == ColsToCheck[1]
						|| jacob.colNum == ColsToCheck[2])
					jacob.rowNumInCurPage++;
				message.append(jacob.rowNumInCurPage);
			}
			message.append("行 第");
			message.append(jacob.colNum);
			message.append("列处\n");
			if (IndexOutOfBoundsException.class.isInstance(e))
				message.append("没有找到对应游离二氧化硅含量");
			else if (e.getMessage() != null) {
				message.append(e.getMessage());
			}
			System.err.println(message);
			throw new Exception(message.toString());
		} finally {
			jacob.closeWordDoc();
			jacob.closeWord();
		}
	}
	
	/**
	 * 将substabce name 与 detailed name 分开。
	 * @param sub
	 * @return substabce name 与 detailed name组成的数组
	 */
	private static String[] splitSubName(String sub) {
		sub = sub.replace('(', '（');
		sub = sub.replace(')', '）');
		sub = sub.replace(" ", "");
		// DONE Deal with other detailedName
		String subDetail;
		if (sub.contains("其他粉尘")) {
			String[] strs = sub.split("[（\\(）\\)]");
			sub = strs[0];
			subDetail = strs[1];
		} else {
			subDetail = null;
		}
		String[] r = {sub, subDetail};
		return r;
	}
	
	/**
	 * 判断是否是同一个项目的单元格，主要目的是判断跨页拆分单元格导致的拆分复制情况。
	 * 
	 * @param jacob
	 * @param rowToCheck
	 * @param prevRows
	 * @param cols
	 * @param compareBegin
	 * @return
	 * @throws Exception
	 */
	private static boolean isSameCells(JacobTool jacob, int rowToCheck, int prevRows[], int[] cols, int compareBegin) throws Exception {
		for (int i = compareBegin; i < cols.length; i++) {
			if (!isSameCell(jacob, rowToCheck, prevRows[i], cols[i]))
				return false;
		}
		return true;
	}

	/**
	 * [仅为isSameCells调用，不能直接使用]判断是否是同一个项目的单元格，仅以名称是否相同作为最后判断的依据，
	 * 没有考虑下一项目恰好与其相同的情况，所以需要使用isSameCells来判断。
	 * 
	 * @param jacob
	 * @param row1
	 * @param row2
	 * @param col
	 * @return
	 * @throws Exception
	 */
	private static boolean isSameCell(JacobTool jacob, int row1, int row2, int col) throws Exception {
		if (!jacob.isCellStrExisted(row1, col)) return true;
		if (jacob.isInOnePage(row1, col, row2, col)) return false;
		String s1 = jacob.getCellString(row1, col);
		String s2 = jacob.getCellString(row2, col);
		if (!s1.equals(s2)) return false;
		return true;
	}
	
	private static Date string2Date(String s) throws Exception {
		s = s.trim();
		String formatStr = null;
		if (s.contains("月")) {
			if (s.contains("年")) {
				if (s.contains("日")) {
					formatStr = "yyyy年MM月dd日";
				} else if (s.contains("号")) {
					formatStr = "yyyy年MM月dd号";
				} else {
					throw new Exception("未支持的日期格式");
				}
			} else if (s.contains("日")) {
				formatStr = "MM月dd日";
			} else if (s.contains("号")) {
				formatStr = "MM月dd号";
			} else {
				throw new Exception("未支持的日期格式");
			}
		} else if (s.contains("-")) {
			if (s.indexOf('-') != s.lastIndexOf('-')) {
				formatStr = "yyyy-MM-dd";
			} else {
				formatStr = "MM-dd";
			}
		} else {
			throw new Exception("未支持的日期格式");
		}
		return new SimpleDateFormat(formatStr).parse(s);
	}
	
	/**
	 * 将带有'~'的时间段字符串解析为Date[]。
	 * 
	 * @param s
	 * @return 起始结束时间组成的数组
	 * @throws Exception 
	 */
	private static Date[] getDate(String s) throws Exception {
		Date[] dates = new Date[2];
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				int wavyIndex = s.indexOf('~');
				if (wavyIndex != -1) {
					dates[0] = string2Date(s.substring(i, wavyIndex));
					String temp = s.substring(wavyIndex + 1, s.length());
					if (temp.indexOf('-') == temp.lastIndexOf('-')) {
						if (temp.indexOf('-') == -1) {
							dates[1] = string2Date(s.substring(i, s.lastIndexOf('-') + 1) + temp);
						} else {
							dates[1] = string2Date(s.substring(i, s.indexOf('-') + 1) + temp);
						}
					} else {
						dates[1] = string2Date(temp);
					}
				} else {
					dates[0] = string2Date(s.substring(i, s.length()));
				}
				break;
			}
		}
		return dates;
	}
	
	/**
	 * 解析字符串得到数字部分
	 * @param s
	 * @return
	 */
	private static String getNumString(String s) {
		int i;
		for (i = 0; i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.'); i++);
		return s.substring(0, i);
	}
}
