package cn.edu.tongji.anliantest.document;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobTool {
	
	// word运行程序对象
	private ActiveXComponent wordApp = null;
	
	// word文档
	private Dispatch wordDoc = null;
	// word文档集合
	private Dispatch wordDocs = null;
	
	// 选定的范围或插入点
	private Dispatch selection = null;
	
	// 设置是否保存后才退出的标志
	private boolean saveOnExit = true;
	
	
	private Dispatch table;
	
	private Dispatch rows;
	private Dispatch row;
	
	private Dispatch columns;
	private Dispatch column;
	
	private Dispatch cell;
	
	private Dispatch font;
	
	private Dispatch shapes;
	private Dispatch shape;
	private Dispatch textframe;
	private Dispatch range;
	private Dispatch paragraphs;
	private Dispatch paragraph;
	
	public int pageNum;
	public int tableNum;
	public int rowNum;
	public int colNum;
	public int rowNumInCurPage;
	
	public JacobTool() {
		if (wordApp == null) {
			wordApp = new ActiveXComponent("Word.Application");
			wordApp.setProperty("Visible", new Variant(true));
		}
		if (wordDocs == null) {
			wordDocs = wordApp.getProperty("Documents").toDispatch();
		}
	}
	
	/**
	 * 创建一个新的word文档
	 */
	public void createNewWordDoc() {
		wordDoc = Dispatch.call(wordDocs, "Add").toDispatch();
		selection = Dispatch.get(wordApp, "Selection").toDispatch();
	}
	
	/**
	 * 打开一个已存在的文档
	 * @param filePath
	 */
	public void openWordDoc(String filePath) {
		if (this.wordDoc != null) {
			this.closeWordDoc();
		}
		wordDoc = Dispatch.call(wordDocs, "Open", filePath).toDispatch();
		selection = Dispatch.get(wordApp, "Selection").toDispatch();
	}
	
	/**
	 * 关闭当前word文档
	 */
	public void closeWordDoc() {
		if (wordDoc != null) {
			Dispatch.call(wordDoc, "Save");
			Dispatch.call(wordDoc, "Close", new Variant(saveOnExit));
			wordDoc = null;
		}
	}
	
	/**
	 * 关闭全部应用
	 */
	public void closeWord() {
		closeWordDoc();
		if (wordApp != null) {
			Dispatch.call(wordApp, "Quit");
			wordApp = null;
		}
		selection = null;
		wordDocs = null;
	}
	
	public Dispatch getWordDoc(){
		return wordDoc;
	}
	
	public void switchToHeader() {
		switchToView(1);
	}
	
	public void switchToMainDoc() {
		switchToView(0);
	}
	
	public void switchToView(int seekViewIdx) {
		Dispatch activeWindow = Dispatch.get(wordDoc, "ActiveWindow").toDispatch();
		Dispatch activePane = Dispatch.get(activeWindow, "ActivePane").toDispatch();
		Dispatch view = Dispatch.get(activePane, "View").toDispatch();
		Dispatch.put(view, "SeekView", seekViewIdx);
	}
	
	public void setPageOrientation(int orientation) {
		Dispatch pageSetup = Dispatch.get(wordDoc, "PageSetup").toDispatch();
		Dispatch.put(pageSetup, "Orientation", new Variant(orientation));
	}
	
	public Dispatch getPageSetup() {
		return Dispatch.get(wordDoc, "PageSetup").toDispatch();
	}
	
	public Dispatch getSelection() {
		return selection;
	}
	
	/**
	 * 把插入点移动到文件首位置
	 */
	public void moveStart() {
		if (selection == null)
			selection = Dispatch.get(wordApp, "Selection").toDispatch();
		Dispatch.call(selection, "HomeKey", new Variant(6));
	}
	
	public void breakHeaderFooterLink() {
		Dispatch.call(selection, "InsertBreak", 2);
		switchToHeader();
		Dispatch headerFooter = Dispatch.get(selection, "HeaderFooter").toDispatch();
		Dispatch.put(headerFooter, "LinkToPrevious", new Variant(false));
		switchToMainDoc();
	}
	
	public boolean isCellStrExisted(int cellRowIdx, int cellColIdx) {
		try {
			getCellString(cellRowIdx, cellColIdx);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 在当前插入点插入字符串
	 * @param newText 要插入的新字符串
	 */
	public void insertText(String newText) {
		if (newText == null) return;
		Dispatch.call(selection, "TypeText", newText);
	}

	public void insertSpace(int spaceCount) {
		for (int i = 0; i < spaceCount; i++) {
			insertText(" ");
		}
	}
	
	public void insertBoldText(String newText) {
		getFont();
		Dispatch.put(font, "Bold", new Variant(true));
		insertText(newText);
		Dispatch.put(font, "Bold", new Variant(false));	
	}
	
	public void insertSuperText(String newText) {
		getFont();
		Dispatch.put(font, "Superscript", new Variant(true));
		insertText(newText);
		Dispatch.put(font, "Superscript", new Variant(false));		
	}
	
	public void insertSubText(String newText) {
		getFont();
		Dispatch.put(font, "Subscript", new Variant(true));
		insertText(newText);
		Dispatch.put(font, "Subscript", new Variant(false));		
	}
	
	public void insertPageNum() {
		Dispatch range = Dispatch.get(selection, "Range").toDispatch();
		Dispatch fields = Dispatch.get(selection, "Fields").toDispatch();
		Dispatch.call(fields, "Add", range, -1, "PAGE  \\* Arabic  \\* MERGEFORMAT", new Variant(false));
	}
	
	public void insertNumPages() {
		Dispatch range = Dispatch.get(selection, "Range").toDispatch();
		Dispatch fields = Dispatch.get(selection, "Fields").toDispatch();
		Dispatch.call(fields, "Add", range, -1, "NUMPAGES  \\* Arabic  \\* MERGEFORMAT", new Variant(false));
	}
	
	/**
	 * 在当前插入点插入图片
	 * @param imagePath 图片路径        
	 */
	public void insertImage(String imagePath) {
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
	}
	
	/**
	 * 把选定的内容或者插入点向下移动
	 * @param pos 移动的距离
	 */
	public void moveDown(int pos) {
		if (selection == null)
			selection = Dispatch.get(wordApp, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveDown");
	}
	
	/**
	 * 把选定的内容或插入点向上移动
	 * @param pos 移动的距离
	 */
	public void moveUp(int pos) {
		if (selection == null)
			selection = Dispatch.get(wordApp, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveUp");
	}

	/**
	 * 把选定的内容或者插入点向左移动
	 * @param pos 移动的距离
	 */
	public void moveLeft(int pos) {
		if (selection == null)
			selection = Dispatch.get(wordApp, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveLeft");
		}
	}

	/**
	 * 把选定的内容或者插入点向右移动 
	 * @param pos 移动的距离
	 */
	public void moveRight(int pos) {
		if (selection == null)
			selection = Dispatch.get(wordApp, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveRight");
	}
	
	/**
	 * 文件保存或另存为
	 * @param savePath 一定要记得加上扩展名 .doc 保存或另存为路径
	 */
	public void save(String savePath) {
		Dispatch.call(
				(Dispatch) Dispatch.call(wordApp, "WordBasic").getDispatch(),
				"FileSaveAs", savePath);
	}
	
	/**
	 * 从第tIndex个Table中取出值第row行，第col列的值
	 * 
	 * @param tableIndex 文档中的第tIndex个Table，即tIndex为索引取
	 * @param cellRowIdx cell在Table第row行
	 * @param cellColIdx cell在Talbe第col列
	 * @return cell单元值
	 * @throws Exception
	 */
	public String getCellString(int tableIndex, int cellRowIdx, int cellColIdx)
			throws Exception {
		getTable(tableIndex);
		return getCellString(cellRowIdx, cellColIdx);
	}
	
	public int getCellPageNum(int cellRowIdx, int cellColIdx) {
		Dispatch.call(getCell(cellRowIdx, cellColIdx), "Select");
		Dispatch.call(selection, "MoveLeft");
		return Dispatch.call(selection, "Information", 3).getInt();
	}
	
	public String getCellString(int cellRowIdx, int cellColIdx)
			throws Exception {
		if (cellColIdx == 3) {
			int curPageNum = getCellPageNum(cellRowIdx, cellColIdx);
			if (curPageNum != this.pageNum) {
				this.rowNumInCurPage = 0;
			}
				this.rowNumInCurPage++;
			this.pageNum = curPageNum;
		}
		this.colNum = cellColIdx;
		this.rowNum = cellRowIdx;
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch cellRange = Dispatch.get(cell, "Range").toDispatch();
		Dispatch cellListFormat = Dispatch.get(cellRange, "ListFormat").toDispatch();
		boolean isNumbered = Dispatch.call(cellListFormat, "CountNumberedItems").getInt() > 0;
		if (isNumbered) {
			return Dispatch.get(cellListFormat, "ListValue").toString();
		} else {
			String temp = Dispatch.get(cellRange, "Text").getString();
			temp = temp.substring(0, temp.length()-2);
			if (temp.length() == 0) throw new Exception("值为空");
			return temp;
		}
	}
	
	/**
	 * 从第tableIndex个Table中取出值第cellRowIdx行，第cellColIdx列的值
	 * @param tIndex 文档中的第tIndex个Table，即tIndex为索引取
	 * @param cellRowIdx cell在Table第row行
	 * @param cellColIdx cell在Talbe第col列
	 * @return cell单元值
	 * @throws Exception
	 */
	public void getCellValue(int tableIndex, int cellRowIdx, int cellColIdx)
			throws Exception {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要取数据的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.call(selection, "Copy");
	}
	
	/**
	 * 在当前光标处做粘贴
	 */
	public void paste() {
		Dispatch.call(selection, "Paste");
	}
	
	/**
	 * 在当前光标处添加图片
	 * @param imgPath 图片的地址
	 */
	public void addImage(String imgPath) {
		if (imgPath != "" && imgPath != null) {
			Dispatch image = Dispatch.get(selection, "InLineShapes")
					.toDispatch();
			Dispatch.call(image, "AddPicture", imgPath);
		}
	}
	
	/**
	 * 在指定的单元格里填写数据
	 * @param tableIndex 文档中的第tIndex个Table，即tIndex为索引取
	 * @param cellRowIdx cell在Table第row行
	 * @param cellColIdx cell在Talbe第col列
	 * @param txt 填写的数据
	 */
	public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,
			String txt) {
		getTable(tableIndex);
		putTxtToCell(cellRowIdx, cellColIdx, txt);
	}

	public void putTxtToCell(int cellRowIdx, int cellColIdx,
			String txt) {
		if (txt == null) txt = "-";
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.put(font, "Bold", new Variant(false));	
		Dispatch.call(selection, "TypeText", txt);
	}
	
	public void putBoldTxtToCell(int cellRowIdx, int cellColIdx,
			String txt) {
		if (txt == null) txt = "-";
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		getFont();
		Dispatch.put(font, "Bold", new Variant(true));
		Dispatch.call(selection, "TypeText", txt);
		Dispatch.put(font, "Bold", new Variant(false));	
	}
	
	public void deleteCellTxt(int cellRowIdx, int cellColIdx) {
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.call(selection, "Delete");
	}
	
	public Dispatch getFont() {
		font = Dispatch.get(selection, "Font").toDispatch();
		return font;
	}
	
	/**
	 * 得到当前文档的tables集合
	 */
	public Dispatch getTables() throws Exception {
		if (wordDoc == null) {
			throw new Exception("there is not a document can't be operate!!!");
		}
		return Dispatch.get(wordDoc, "Tables").toDispatch();
	}
	
	/**
	 * 得到当前文档的表格数
	 * @param Dispatch
	 */
	@SuppressWarnings("deprecation")
	public int getTablesCount(Dispatch tables) throws Exception {
		int count = 0;
		try {
			this.getTables();
		} catch (Exception e) {
			throw new Exception("there is not any table!!");
		}
		count = Dispatch.get(tables, "Count").toInt();
		return count;
	}
	
	/**
	 * 在当前文档指定的位置拷贝表格
	 * @param pos 当前文档指定的位置
	 * @param tableIndex 被拷贝的表格在word文档中所处的位置
	 */
	public void copyTable(String pos, int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		Dispatch range = Dispatch.get(table, "Range").toDispatch();
		Dispatch.call(range, "Copy");
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	}
	
	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的表格
	 * @param anotherDocPath 另一个文档的磁盘路径
	 * @param tableIndex 被拷贝的表格在另一格文档中的位置
	 * @param pos 当前文档指定的位置
	 */
	public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex,
			String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(wordDocs, "Open", anotherDocPath)
					.toDispatch();
			// 所有表格
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			// 要填充的表格
			Dispatch table = Dispatch.call(tables, "Item",
					new Variant(tableIndex)).toDispatch();
			Dispatch range = Dispatch.get(table, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			moveDown(1);
			Dispatch.call(textRange, "Paste");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}
	
	/**
	 * 在当前文档拷贝剪贴板数据
	 * @param pos
	 */
	public void pasteExcelSheet(String pos) {
		moveStart();
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	}
	
	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的图片
	 * 
	 * @param anotherDocPath 另一个文档的磁盘路径
	 * @param shapeIndex 被拷贝的图片在另一格文档中的位置
	 * @param pos 当前文档指定的位置
	 * @throws com.jacob.com.ComFailException
	 *             Invoke of: Item Source: Microsoft Word 若shapeIndex不存在
	 */
	public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex,
			String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(wordDocs, "Open", anotherDocPath)
					.toDispatch();
			Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch();
			Dispatch shape = Dispatch.call(shapes, "Item",
					new Variant(shapeIndex)).toDispatch();
			Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch();
			Dispatch.call(imageRange, "Copy");
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			moveDown(4);
			Dispatch.call(textRange, "Paste");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}
	
	/**
	 * 打印当前word文档
	 * @throws Exception
	 *             com.jacob.com.ComFailException: Invoke of: PrintOut Source:
	 *             Microsoft Word 若无打印机
	 */
	public void printFile() {
		if (wordDoc != null) {
			Dispatch.call(wordDoc, "PrintOut");
		}
	}
	
	/**
	 * 打印文本，反选，在文本后换行<br>
	 * 警告：使用了Home, End来取消选中
	 * @param s
	 */
	public void println(String s) {
		write(s);
		goToEnd();
		cmd("TypeParagraph");
	}
	
	/**
	 * 执行某条宏指令
	 * @param cmd
	 */
	private void cmd(String cmd) {
		Dispatch.call(selection, cmd);
	}
	
	/**
	 * 按下Ctrl + End键
	 */
	public void goToEnd() {
		Dispatch.call(selection, "EndKey", "6");
	}
	
	/**
	 * 反选，再打印文本<br>
	 * 警告：使用了Home, End来取消选中
	 */
	public void print(String s) {
		goToEnd();
		write(s);
	}
	
	/**
	 * 不反选, 直接输出文本
	 * @param s
	 */
	public void write(String s) {
		Dispatch.put(selection, "Text", s);
	}
	
	/**
	 * 反选，在文本后换行<br>
	 * 警告：使用了Home, End来取消选中
	 */
	public void println() {
		home();
		end();
		cmd("TypeParagraph");
	}
	
	/**
	 * 按下Home键
	 */
	public void home() {
		Dispatch.call(selection, "HomeKey", "5");
	}
	
	/**
	 * 按下End键
	 */
	public void end() {
		Dispatch.call(selection, "EndKey", "5");
	}
	
	/**
	 * 按下Ctrl + Home键
	 */
	public void goToBegin() {
		Dispatch.call(selection, "HomeKey", "6");
	}
	
	/**
	 * 设置指定表格指定列的列宽
	 * @param tableIndex
	 * @param columnWidth
	 * @param columnIndex
	 */
	public void setColumnWidth(int tableIndex, float columnWidth,
			int columnIndex) {
		this.getTable(tableIndex);
		this.setColumnWidth(columnWidth, columnIndex);
	}
	
	/**
	 * 查找表
	 * @param tableIndex
	 * @return
	 */
	public Dispatch getTable(int tableIndex) {
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		this.table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		this.tableNum = tableIndex;
		this.colNum = 0;
		this.rowNum = 0;
		this.rowNumInCurPage = 0;
		return this.table;
	}
	
	public Dispatch getShapes() throws Exception {
		return Dispatch.get(wordDoc, "Shapes").toDispatch();
	}
	
	@SuppressWarnings("deprecation")
	public int getShapesCount() throws Exception {
		int count = 0;
		count = Dispatch.get(shapes, "Count").toInt();
		return count;
	}
	
	public Dispatch getShape(int tIndex) throws Exception {
		return Dispatch.call(shapes, "Item", new Variant(tIndex)).toDispatch();
	}
	
	public Dispatch getTextFrame() throws Exception {
		return Dispatch.get(shape, "TextFrame").toDispatch();
	}

	public Dispatch getTextRange() throws Exception {
		return Dispatch.get(textframe, "TextRange").toDispatch();
	}
	
	/**
	 * 设置当前表格指定列的列宽
	 * 
	 * @param columnWidth
	 * @param columnIndex
	 * @throws 如果不是整齐的表格不能使用
	 */
	public void setColumnWidth(double columnWidth, int columnIndex) {
		if (columnWidth < 0) {
			columnWidth = 0;
		}
		this.getColumns();
		this.getColumn(columnIndex);
		Dispatch.put(column, "PreferredWidthType", new Variant(3));
		Variant points = centimetersToPoints(columnWidth);
		Dispatch.put(column, "Width", points);
	}
	
	public Variant centimetersToPoints(double centimeters) {
		return  Dispatch.call(wordApp, "CentimetersToPoints", new Variant(centimeters));
	}
	
	/**
	 * 设置指定表格指定列的背景色
	 * @param tableIndex
	 * @param columnIndex
	 * @param color 取值范围 0 < color < 17 默认：16 浅灰色 1：黑色 2：蓝色 3：浅蓝 ...............
	 */
	public void setColumnBgColor(int tableIndex, int columnIndex, int color) {
		this.getTable(tableIndex);
		this.setColumnBgColor(columnIndex, color);
	}
	
	/**
	 * 设置当前表格指定列的背景色
	 * @param columnIndex
	 * @param color 取值范围 0 < color < 17 默认：16 浅灰色 1：黑色 2：蓝色 3：浅蓝 ...............
	 */
	public void setColumnBgColor(int columnIndex, int color) {
		this.getColumn(columnIndex);
		Dispatch shading = Dispatch.get(column, "Shading").toDispatch();
		if (color > 16 || color < 1)
			color = 16;
		Dispatch.put(shading, "BackgroundPatternColorIndex", new Variant(color));
	}
	
	/**
	 * 初始化 com 线程
	 */
	public void initCom() {
		ComThread.InitSTA();
	}
	
	/**
	 * 释放 com 线程资源 com 的线程回收不由 java 垃圾回收机制回收
	 */
	public void releaseCom() {
		ComThread.Release();
	}
	
	/**
	 * 设置当前表格指定行的背景色
	 * @param rowIndex
	 * @param color 取值范围 0 < color < 17 默认：16 浅灰色 1：黑色 2：蓝色 3：浅蓝 ...............
	 */
	public void setRowBgColor(int rowIndex, int color) {
		this.getRow(rowIndex);
		Dispatch shading = Dispatch.get(row, "Shading").toDispatch();
		if (color > 16 || color < 1)
			color = 16;
		Dispatch.put(shading, "BackgroundPatternColorIndex", new Variant(color));
	}
	
	/**
	 * 设置指定表格的指定行的背景色
	 * @param tableIndex
	 * @param rowIndex
	 * @param color 取值范围 0 < color < 17 默认：16 浅灰色 1：黑色 2：蓝色 3：浅蓝 
	 */
	public void setRowBgColor(int tableIndex, int rowIndex, int color) {
		this.getTable(tableIndex);
		this.setRowBgColor(rowIndex, color);
	}
	
	/**
	 * 设置当前选定内容的字体
	 * @param isBold 是否为粗体
	 * @param isItalic 是否为斜体
	 * @param isUnderLine 是否带下划线
	 * @param color rgb 字体颜色 例如：红色 255,0,0
	 * @param size 字体大小 12:小四 16:三号
	 * @param name 字体名称 例如：宋体，新宋体，楷体，隶书
	 */
	public void setFont(boolean isBold, boolean isItalic, boolean isUnderLine,
			String color, String size, String name) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(isBold));
		Dispatch.put(font, "Italic", new Variant(isItalic));
		Dispatch.put(font, "Underline", new Variant(isUnderLine));
		Dispatch.put(font, "Color", color);
		Dispatch.put(font, "Size", size);
	}
	
	/**
	 * 恢复默认字体 不加粗，不倾斜，没下划线，黑色，小四号字，宋体
	 */
	public void clearFont() {
		this.setFont(false, false, false, "0,0,0", "12", "宋体");
	}
	
	/**
	 * 对当前段落进行格式化
	 * @param align 设置排列方式 默认：居左 0:居左 1:居中 2:居右 3:两端对齐 4:分散对齐
	 * @param lineSpace 设置行间距 默认：1.0 0：1.0 1：1.5 2：2.0 3：最小值 4：固定值
	 */
	public void setParaFormat(int align, int lineSpace) {
		if (align < 0 || align > 4) {
			align = 0;
		}
		if (lineSpace < 0 || lineSpace > 4) {
			lineSpace = 0;
		}
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
				.toDispatch();
		Dispatch.put(alignment, "Alignment", align);
		Dispatch.put(alignment, "LineSpacingRule", new Variant(lineSpace));
	}
	
	/**
	 * 还原段落默认的格式 左对齐,行间距：1.0
	 */
	public void clearParaFormat() {
		this.setParaFormat(0, 0);
	}
	
	/**
	 * 创建表格
	 * @param pos 位置
	 * @param cols 列数
	 * @param rows 行数
	 */
	public void createTable(String pos, int numCols, int numRows) {
		if (find(pos)) {
			Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
			Dispatch range = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(tables, "Add", range,
					new Variant(numRows), new Variant(numCols)).toDispatch();
			Dispatch.call(selection, "MoveRight");
		}
	}
	
	public Dispatch insertTable(int numCols, int numRows) {
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		Dispatch range = Dispatch.get(selection, "Range").toDispatch();
		table = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols)).toDispatch();
		Dispatch.call(selection, "MoveRight");
		return table;
	}
	
	/**
	 * 在指定行前面增加行
	 * @param tableIndex word文件中的第N张表(从1开始)
	 * @param rowIndex 指定行的序号(从1开始)
	 */
	public void addTableRow(int tableIndex, int rowIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex))
				.toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
	/**
	 * 在第1行前增加一行
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addFirstTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
	/**
	 * 在最后1行前增加一行
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addLastTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
	/**
	 * 增加一行
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}
	
	public void addRow() {
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}
	
	public void deleteRow(int rowIdx) {
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIdx)).toDispatch();
		Dispatch.call(row, "Delete");
	}
	
	public void deleteCell(int cellRow, int cellColumn) {
		getCell(cellRow, cellColumn);
		Dispatch.call(cell, "Delete");
	}
	
	/**
	 * 增加一列
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addCol(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	public void addCol() {
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	
	/**
	 * 在指定列前面增加表格的列
	 * @param tableIndex word文档中的第N张表(从1开始)
	 * @param colIndex 制定列的序号 (从1开始)
	 */
	public void addTableCol(int tableIndex, int colIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		System.out.println(Dispatch.get(cols, "Count"));
		Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex))
				.toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	
	/**
	 * 在第1列前增加一列
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addFirstTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	
	/**
	 * 在最后一列前增加一列
	 * @param tableIndex word文档中的第N张表(从1开始)
	 */
	public void addLastTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(wordDoc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "Last").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	
	/**
	 * 从选定内容或插入点开始查找文本
	 * @param toFindText 要查找的文本
	 * @return boolean true-查找到并选中该文本，false-未查找到文本
	 */
	public boolean find(String toFindText) {
		if (toFindText == null || toFindText.equals(""))
			return false;
		// 从selection所在位置开始查询
		Dispatch find = Dispatch.call(selection, "Find").toDispatch();
		// 设置要查找的内容
		Dispatch.put(find, "Text", toFindText);
		// 向前查找
		Dispatch.put(find, "Forward", "True");
		// 设置格式
		Dispatch.put(find, "Format", "True");
		// 大小写匹配
		Dispatch.put(find, "MatchCase", "True");
		// 全字匹配
		Dispatch.put(find, "MatchWholeWord", "True");
		// 查找并选中
		return Dispatch.call(find, "Execute").getBoolean();
	}
	
	/**
	 * 把选定选定内容设定为替换文本
	 * @param toFindText 查找字符串
	 * @param newText 要替换的内容
	 * @return
	 */
	public boolean replaceText(String toFindText, String newText) {
		if (!find(toFindText))
			return false;
		Dispatch.put(selection, "Text", newText);
		return true;
	}
	
	/**
	 * 全局替换文本
	 * @param toFindText 查找字符串
	 * @param newText 要替换的内容
	 */
	public void replaceAllText(String toFindText, String newText) {
		while (find(toFindText)) {
			Dispatch.put(selection, "Text", newText);
			Dispatch.call(selection, "MoveRight");
		}
	}
	
	/**
	 * @param toFindText 要查找的字符串
	 * @param imagePath 图片路径
	 * @return 此函数将字符串替换成图片
	 */
	public boolean replaceImage(String toFindText, String imagePath) {
		if (!find(toFindText))
			return false;
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
		return true;
	}
	
	/**
	 * 全局替换图片
	 * 
	 * @param toFindText 查找字符串
	 * @param imagePath 图片路径
	 */
	public void replaceAllImage(String toFindText, String imagePath) {
		while (find(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
					"AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}
	
	public Dispatch getRow(int rowIndex) {
		this.getRows();
		row = Dispatch.invoke(rows, "item", Dispatch.Method,
				new Object[] { new Integer(rowIndex) }, new int[1])
				.toDispatch();
		return row;
	}

	public int getRowsCount() {
		this.getRows();
		return Dispatch.get(rows, "Count").getInt();
	}
	
	/**
	 * 得到当前表格的所有的列
	 */
	public Dispatch getColumns() {
		return this.columns = Dispatch.get(this.table, "Columns").toDispatch();
	}
	
	/**
	 * 得到当前表格的某一列
	 * @param index 列索引
	 * @return
	 */
	public Dispatch getColumn(int columnIndex) {
		this.getColumns();
		return this.column = Dispatch.call(columns, "Item",
				new Variant(columnIndex)).toDispatch();
	}
	
	/**
	 * 得到当前表格的列数
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getColumnsCount() {
		this.getColumns();
		return Dispatch.get(columns, "Count").toInt();
	}
	
	/**
	 * 得到指定表格的列数
	 * @param tableIndex
	 * @return
	 */
	public int getColumnsCount(int tableIndex) {
		this.getTable(tableIndex);
		return this.getColumnsCount();
	}
	
	/**
	 * 得到表的行数
	 * @param tableIndex
	 * @return
	 */
	public int getRowsCount(int tableIndex) {
		this.getTable(tableIndex);
		return this.getRowsCount();
	}
	
	/**
	 * 设置当前表格的所有行的行高
	 * @param rowHeight
	 */
	public void setRowsHeight(double rowHeight) {
		if (rowHeight > 0) {
			this.getRows();
			Dispatch.put(rows, "Height", centimetersToPoints(rowHeight));
		}
	}
	
	public void setRowsHeightRuleExactly() {
		this.getRows();
		Dispatch.put(rows, "HeightRule", 2);
	}
	
	/**
	 * 设置指定表格的所有行的行高
	 * @param tableIndex
	 * @param rowHeight
	 */
	public void setRowsHeight(int tableIndex, double rowHeight) {
		this.getRows(tableIndex);
		this.setRowsHeight(rowHeight);
	}

	public void setRowBold(int rowIndex) {
		this.getRow(rowIndex);
		Dispatch.call(row, "Select");
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Bold", new Variant(true));
	}
	
	/**
	 * 设置当前表格指定行的行高
	 * @param rowHeight
	 * @param rowIndex
	 */
	public void setRowHeight(double rowHeight, int rowIndex) {
		if (rowHeight > 0) {
			this.getRows();	
			this.getRow(rowIndex);
			Dispatch.put(row, "Height", centimetersToPoints(rowHeight));
		}
	}

	public void setRowHeadingFormat(int rowIndex) {
		this.getRows();
		this.getRow(rowIndex);
		Dispatch.put(row, "HeadingFormat", true);
	}
	
	/**
	 * 设置指定表格的指定行的行高
	 * @param tableIndex
	 * @param rowHeight
	 * @param rowIndex
	 */
	public void setRowHeight(int tableIndex, float rowHeight, int rowIndex) {
		this.getTable(tableIndex);
		this.setRowHeight(rowHeight, rowIndex);
	}

	/**
	 * 设置当前表格的所有列的列宽
	 * @param columnWidth 列宽 取值范围：10<columnWidth 默认值：120
	 */
	public void setColumnsWidth(float columnWidth) {
		if (columnWidth < 11) {
			columnWidth = 120;
		}
		this.getColumns();
		Dispatch.put(columns, "Width", new Variant(columnWidth));
	}
	
	/**
	 * 设置指定表格的所有列的列宽
	 * @param tableIndex
	 * @param columnWidth
	 */
	public void setColumnsWidth(int tableIndex, float columnWidth) {
		this.getColumns(tableIndex);
		this.setColumnsWidth(columnWidth);
	}
	
	/**
	 * 得到指定表格的多有列
	 * @param tableIndex
	 * @return
	 */
	public Dispatch getColumns(int tableIndex) {
		getTable(tableIndex);
		return this.getColumns();
	}
	
	/**
	 * 复制表的某一行
	 * @param tableIndex
	 * @param rowIndex
	 */
	public void copyRow(int tableIndex, int rowIndex) {
		getTable(tableIndex);
		getRows();
		row = getRow(rowIndex);
		Dispatch.call(row, "Select");
		Dispatch.call(selection, "Copy");
	}
	
	/**
	 * 查找表的全部行
	 * @param tableIndex
	 * @return
	 */
	public Dispatch getRows(int tableIndex) {
		getTable(tableIndex);
		return this.getRows();
	}
	
	/**
	 * 查找当前表的全部行
	 */
	public Dispatch getRows() {
		rows = Dispatch.get(table, "rows").toDispatch();
		return rows;
	}
	
	/**
	 * 查找指定表格的单元格
	 * @param tableIndex
	 * @param cellRow
	 * @param cellColumn
	 * @return
	 */
	public Dispatch getCell(int tableIndex, int cellRow, int cellColumn) {
		getTable(tableIndex);
		return getCell(cellRow, cellColumn);
	}
	
	/**
	 * 查找当前所在表的某单元格
	 * @param cellRow
	 * @param cellColumn
	 * @return
	 * @throws Dispatch object expected
	 */
	public Dispatch getCell(int cellRow, int cellColumn) {
		return cell = Dispatch.call(table, "Cell", new Variant(cellRow),
				new Variant(cellColumn)).toDispatch();
	}
	
	/**
	 * 合并当前表格指定的单元格 如果需要一次合并几个单元格只需要指出第一个单元格和最后一个单元格
	 * @param fstCellRowIndex 第一个单元格的行索引
	 * @param fstCellColIndex 第一个单元格的列索引
	 * @param secCellRowIndex 第二个单元格的行索引
	 * @param secCellColIndex 第二个单元格的列索引
	 */
	public void mergeCell(int fstCellRowIndex, int fstCellColIndex,
			int secCellRowIndex, int secCellColIndex) {
		if (fstCellRowIndex == secCellRowIndex && fstCellColIndex == secCellColIndex)
			return;
		Dispatch fstCell = getCell(fstCellRowIndex, fstCellColIndex);
		Dispatch secCell = getCell(secCellRowIndex, secCellColIndex);
			Dispatch.call(fstCell, "Merge", secCell);
	}
	
	public boolean isInOnePage(int fstCellRowIndex, int fstCellColIndex,
			int secCellRowIndex, int secCellColIndex) {
		Dispatch.call(getCell(fstCellRowIndex, fstCellColIndex), "Select");
		Dispatch.call(selection, "MoveLeft");
		int fstPage = Dispatch.call(selection, "Information", 3).getInt();
		Dispatch.call(getCell(secCellRowIndex, secCellColIndex), "Select");
		Dispatch.call(selection, "MoveLeft");
		int secPage = Dispatch.call(selection, "Information", 3).getInt();
		if (fstPage == secPage) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 合并当前表格指定的列
	 * @param columnIndex 列索引
	 */
	public void mergeColumn(int columnIndex) {
		this.getColumn(columnIndex);
		Dispatch cells = Dispatch.get(column, "Cells").toDispatch();
		Dispatch.call(cells, "Merge");
	}
	
	/**
	 * 合并当前表格的指定的行
	 * @param rowIndex
	 */
	public void mergeRow(int rowIndex) {
		this.getRow(rowIndex);
		Dispatch cells = Dispatch.get(row, "Cells").toDispatch();
		Dispatch.call(cells, "Merge");
	}
	
	/**
	 * 合并指定表格的指定的行
	 * @param tableIndex
	 * @param rowIndex 行索引
	 */
	public void mergeRow(int tableIndex, int rowIndex) {
		this.getTable(tableIndex);
		this.mergeRow(rowIndex);
	}
	
	/**
	 * 合并指定表格的指定的列
	 * @param tableIndex
	 * @param columnIndex
	 */
	public void mergeColumn(int tableIndex, int columnIndex) {
		this.getTable(tableIndex);
		this.mergeColumn(columnIndex);
	}
	
	/**
	 * 合并指定表格的指定的单元格
	 * @param tableIndex
	 * @param fstCellRowIndex
	 * @param fstCellColIndex
	 * @param secCellRowIndex
	 * @param secCellColIndex
	 */
	public void mergeCell(int tableIndex, int fstCellRowIndex,
			int fstCellColIndex, int secCellRowIndex, int secCellColIndex) {
		this.getTable(tableIndex);
		this.mergeCell(fstCellRowIndex, fstCellColIndex, secCellRowIndex,
				secCellColIndex);
	}

	public Dispatch getRangeParagraphs() throws Exception {
		return Dispatch.get(range, "Paragraphs").toDispatch();
	}

	public Dispatch getParagraph(int tIndex) throws Exception {
		return Dispatch.call(paragraphs, "Item", new Variant(tIndex))
				.toDispatch();
	}

	public Dispatch getParagraphRange() throws Exception {
		return Dispatch.get(paragraph, "range").toDispatch();
	}

	public String getRangeText() throws Exception {
		return Dispatch.get(range, "Text").toString();
	}

	@SuppressWarnings("deprecation")
	public int getParagraphsCount() throws Exception {
		int count = 0;
		count = Dispatch.get(paragraphs, "Count").toInt();
		return count;
	}
	
	public void setTableAlignCenter() {
		Dispatch.call(table, "Select");
		Dispatch format = Dispatch.get(selection, "ParagraphFormat").toDispatch();
		Dispatch.put(format, "Alignment", new Variant(1));
		Dispatch cells = Dispatch.get(selection, "Cells").toDispatch();
		Dispatch.put(cells, "VerticalAlignment", new Variant(1));
	}
}
