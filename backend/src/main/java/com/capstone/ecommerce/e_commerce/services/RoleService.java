package com.capstone.ecommerce.e_commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Role;
import com.capstone.ecommerce.e_commerce.models.RoleType;
import com.capstone.ecommerce.e_commerce.repo.RoleRepository;

@Service
public class RoleService {

	 @Autowired
	    RoleRepository repository;

	    public Role getById(Long id) throws Exception {
	        Optional<Role> ba = repository.findById(id);
	        if ( ba.isEmpty() )
	            throw new Exception("Role not available");
	        return ba.get();
	    }

	    public Role getByRole( RoleType roleType) throws Exception {
	        Optional<Role> ba = repository.findByRoleType(roleType);
	        if ( ba.isEmpty() )
	            throw new Exception("Role not available");
	        return ba.get();
	    }

	    public List<Role> getAll() {
	        return repository.findAll();
	    }

	    // CREATE
	    public Role save( Role x) {
	        return repository.save(x);
	    }

	    // DELETE
	    public void deleteById(Long id) {
	        repository.deleteById(id);
	    }
}
