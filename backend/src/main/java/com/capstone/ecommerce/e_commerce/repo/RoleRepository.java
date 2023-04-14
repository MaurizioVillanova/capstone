package com.capstone.ecommerce.e_commerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.ecommerce.e_commerce.models.Role;
import com.capstone.ecommerce.e_commerce.models.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleType(RoleType roleType);
}
