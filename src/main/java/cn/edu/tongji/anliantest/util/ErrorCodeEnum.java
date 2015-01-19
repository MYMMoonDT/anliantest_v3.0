package cn.edu.tongji.anliantest.util;

import java.io.Serializable;

public enum ErrorCodeEnum implements Serializable{
	No_Error("No Error", 0),
	
	Unknown_Error("Unknown Error", 1),

	Employee_Not_Exist("Employee not exist", 2),
	
	Password_Wrong("Password Wrong", 3),
	
	File_Format_Wrong("File Format Wrong", 4), 
	
	Authority_Group_Used("Authority Group Used", 5),
	
	ZYBWHYS_Not_Exist("ZYBWHYS Group Used", 6);
	
	private String label;
	private Integer code;
	
	ErrorCodeEnum() {}
	
	ErrorCodeEnum(String label, Integer code) {
		this.setLabel(label);
		this.setCode(code);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return code.toString();
	}
	
	public static ErrorCodeEnum parse(int code) {
		for (ErrorCodeEnum theEnum : ErrorCodeEnum.values()) {
			if (theEnum.getCode() == code) {
				return theEnum;
			}
		}
		return No_Error;
	}
}
