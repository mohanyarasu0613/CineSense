package com.spring.CineSense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
