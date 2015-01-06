package cn.edu.tongji.anliantest.model.experiment;

import java.util.ArrayList;
import java.util.List;

public class JCBGTableInput implements java.io.Serializable{
	private static final long serialVersionUID = -4632602657730228087L;
	
	private List<JCBGGroup> list = new ArrayList<JCBGGroup>(0);

	public List<JCBGGroup> getList() {
		return list;
	}

	public void setList(List<JCBGGroup> list) {
		this.list = list;
	}
	
}
