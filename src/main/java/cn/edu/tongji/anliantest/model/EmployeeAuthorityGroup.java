package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "employee_authority_group")
public class EmployeeAuthorityGroup implements Serializable{
	private static final long serialVersionUID = 5133855493118256373L;

	private Long id;
	
	private AuthorityGroup authorityGroup;
	
	private List<EmployeeAuthorityItem> items = new ArrayList<EmployeeAuthorityItem>(0);

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "authorityGroupId")
	public AuthorityGroup getAuthorityGroup() {
		return authorityGroup;
	}

	public void setAuthorityGroup(AuthorityGroup authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "groupId")
	public List<EmployeeAuthorityItem> getItems() {
		return items;
	}

	public void setItems(List<EmployeeAuthorityItem> items) {
		this.items = items;
	}
	
}
