package com.capstone.ecommerce.e_commerce.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Role;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.models.UserResponse;
import com.capstone.ecommerce.e_commerce.repo.UserRepository;
import com.capstone.ecommerce.e_commerce.utils.UserRequest;

@Service
public class UserService {
	  @Autowired
	   PasswordEncoder encoder;

	    @Autowired
	    private UserRepository userRepository;


	    @Autowired
	    private RoleService roleService;

	    public User getById(Long id ) throws Exception {
	        Optional<User> user = userRepository.findById(id);
	        if( user.isEmpty() )
	            throw new Exception( "User not available" );
	        return user.get();
	    }

	  
	    
	   
      
	    // GET ALL
	    public List<User> getAll() {
	        return userRepository.findAll();
	    }

	    // GET ALL PAGEABLE
	    public Page<User> getAllPaginate(Pageable p ) {
	        return userRepository.findAll( p );
	    }

	    // CREATE
	    public User save( User u ) {
	        String psw = u.getPassword();
	        u.setPassword( encoder.encode( psw ) );
	        return userRepository.save( u );
	    }

	    // CREATE AND SAVE
	    public UserResponse createAndSave( UserRequest userRequest ) throws Exception {
	        User u = new User();
	        u.setNomeCompleto( userRequest.getNomeCompleto() );
	        u.setEmail( userRequest.getEmail() );
	        u.setUsername( userRequest.getUsername() );
	        u.setPassword( encoder.encode( userRequest.getPassword() ) );
	        Set<Role> roles = new HashSet<>();
	        roles.add( roleService.getById( 1L ) );
	        u.setRoles( roles );

	        userRepository.save( u );
	        return UserResponse.parseUser( u );
	    }

	    // UPDATE
	    public void update( User u ) {
	        userRepository.save( u );
	    }

	    // UPDATE AND SAVE
	    public UserResponse updateUserById(UserRequest userRequest, Long id ) throws Exception {
	        Optional<User> userFind = userRepository.findById( id );

	        if( userFind.isPresent() ) {
	     //       User u = new User();
	        //    u.setIdUser( userFind.get().getIdUser() );
	           // u.setNomeCompleto( userRequest.getNomeCompleto() == null ? userFind.get().getNomeCompleto()
	             //       : userRequest.getNomeCompleto() );
	          //  u.setEmail( userRequest.getEmail() == null ? userFind.get().getEmail() : userRequest.getEmail() );
	          //  u.setUsername( userRequest.getUsername() == null ? userFind.get().getUsername() :
	            //        userRequest.getUsername() );
	           // u.setPassword( userFind.get().getPassword() );
	           // u.setRoles( userFind.get().getRoles() );
	           // u.setActive( userFind.get().getActive() );

	           // userRepository.save( u );
	             userRepository.updateUserById(  userRequest.getNomeCompleto(),  userRequest.getUsername(),  userRequest.getEmail(),  id);
	            return UserResponse.parseUser( userFind.get() );
	        } else {
	            return null;
	        }


	    }

	    // DELETE
	    public void delete( Long id ) throws Exception {
	        Optional<User> u = userRepository.findById( id );
	        if( u.isPresent() ) {
	            userRepository.delete( u.get() );
	        } else {
	            throw new Exception( "Utente non trovato" );
	        }
	    }

		public Optional<User> findByEmail(String email) {
			// TODO Auto-generated method stub
			return userRepository.findByEmail( email );
			
		}
		  // GET BY USERNAME
	    public Optional<User> findByUsername( String username ) {

	        return userRepository.findByUsername( username );
	    }

}
