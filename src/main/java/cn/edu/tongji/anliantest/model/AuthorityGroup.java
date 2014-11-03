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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="authority_group")
public class AuthorityGroup implements Serializable{

	private static final long serialVersionUID = 2138841368692103872L;

	private Long id;
	private String name;
	private Set<AuthorityItem> authorityItems = new HashSet<AuthorityItem>(0);
	
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

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "authority_group_item",
				joinColumns = {@JoinColumn(name = "authorityGroupId")},
				inverseJoinColumns = {@JoinColumn(name = "authorityItemId")})
	public Set<AuthorityItem> getAuthorityItems() {
		return authorityItems;
	}

	public void setAuthorityItems(Set<AuthorityItem> authorityItems) {
		this.authorityItems = authorityItems;
	}
}
