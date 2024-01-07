package com.khan.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
