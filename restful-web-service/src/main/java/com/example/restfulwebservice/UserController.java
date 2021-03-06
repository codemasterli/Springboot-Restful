package com.example.restfulwebservice;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
   private UserDaoService service;
   
   public UserController(UserDaoService service) {
	   this.service = service;
   }
   
   @GetMapping("/users")
   public List<User> retrieveAllUsers(){
	   return service.findAll();
   }
   // Get /users/1 or /users/10 -> String
   @GetMapping("/users/{id}")
   public User retrieveUser(@PathVariable int id) {
	   User user = service.findOne(id);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
	   return user;
   }
   
   @PostMapping("/users")
   public ResponseEntity<User> createUser(@Valid @RequestBody User user){
	   User savedUser = service.save(user);
	   
	  URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
	   .path("/{id}")
	   .buildAndExpand(savedUser.getId())
	   .toUri();
	  
	  return ResponseEntity.created(location).build();
   }
   @DeleteMapping("/users/{id}")
   public void deleteUser(@PathVariable int id) {
	   User user = service.deletebyId(id);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
   }
   @PutMapping("/users/{id}")
   public void update(@PathVariable int id,User user) {
	   User Updateuser = service.updateUser(id,user);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
   }
}
