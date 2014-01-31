package com.ole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Role {

	@Id
	@Column(name="ROLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName = "ROLE_USER";
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID", nullable = false)
	private User user;
	
	public Role() {
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
