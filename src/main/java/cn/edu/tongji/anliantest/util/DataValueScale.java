package cn.edu.tongji.anliantest.util;

import java.math.BigDecimal;

public class DataValueScale {
	private BigDecimal value;
	private Integer scale;

	public DataValueScale() {
	}

	public DataValueScale(BigDecimal value, Integer scale) {
		this.value = value;
		this.setScale(scale);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		if (value == null || scale == null)
			return null;
		return value.setScale(scale).toString();
	}
	
	public String toTypeString(String type) {
		if (value == null || scale == null)
			return null;
		return (type.equals("=") ? "" : "＜") + value.setScale(scale).toString();
	}
}
