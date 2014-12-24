package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 《采样及检测依据》条目-实体类  
 */

@Entity
@Table(name="cyjcyj_item")
public class CYJCYJItem implements Serializable{

	private static final long serialVersionUID = 895886196125373235L;

	private Long id;
	private String name;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
