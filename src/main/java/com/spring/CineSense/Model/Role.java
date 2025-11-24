package com.spring.CineSense.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "role_id")
private Integer roleId;

@Column(name = "role_name", nullable = false, unique = true)
private String roleName;

@OneToMany(mappedBy = "role")
private List<User> users;

public Integer getRoleId() {
	return roleId;
}

public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}



}
