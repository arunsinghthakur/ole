package com.ole.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="USER")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="USERNAME", unique=true, nullable=false)
	private String userName;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	@Type(type="true_false")
	@Column(name="ACTIVE")
	private boolean active;
	
	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL_ADDRESS")
	private String email;
	
	@Column(name="MOBILE")
	private String mobile ;
	
	@Column(name="ADDRESS")
	private String address;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Role role;
	
	public User(){
	}

	public Long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

			
}
