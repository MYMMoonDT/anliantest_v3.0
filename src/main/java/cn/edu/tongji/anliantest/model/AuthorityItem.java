package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="authority_item")
public class AuthorityItem implements Serializable{
	
	private static final long serialVersionUID = 234278197807901809L;
	
	private Long id;
	private String name;
	
	private Set<AuthorityGroup> authorityGroups = new HashSet<AuthorityGroup>(0);
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "authorityItems")
	@JsonIgnore
	public Set<AuthorityGroup> getAuthorityGroups() {
		return authorityGroups;
	}

	public void setAuthorityGroups(Set<AuthorityGroup> authorityGroups) {
		this.authorityGroups = authorityGroups;
	}
}
