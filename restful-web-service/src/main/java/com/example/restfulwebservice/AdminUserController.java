package com.example.restfulwebservice;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
   private UserDaoService service;
   
   public AdminUserController(UserDaoService service) {
	   this.service = service;
   }
   
   @GetMapping("/users")
   public MappingJacksonValue retrieveAllUsers(){
	   List<User> users = service.findAll();
	   
	   SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
			   filterOutAllExcept("id","name","joinDate","password","ssn");
	   FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
	   
	   MappingJacksonValue mapping = new MappingJacksonValue(users);
	   mapping.setFilters(filters);
	   return mapping;
	
   }
   
   // Get /admin/users/1 or /admin/v1/users/10 -> String
 //  @GetMapping("/v1/users/{id}")
//   @GetMapping(value = "/users/{id}/" , params = "version=1")
//   @GetMapping(value = "/users/{id}", headers="X-API-VERSION=1")
   @GetMapping(value="/users/{id}" ,produces = "application/vnd.company.appv1+json")
   public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
	   User user = service.findOne(id);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
	   SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
			   filterOutAllExcept("id","name","joinDate","password","ssn");
	   FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
	   
	   MappingJacksonValue mapping = new MappingJacksonValue(user);
	   mapping.setFilters(filters);
	   return mapping;
   }
 //  @GetMapping("/v2/users/{id}")
 //  @GetMapping(value = "/users/{id}/" , params = "version=2")
 //  @GetMapping(value = "/users/{id}", headers="X-API-VERSION=2")
   @GetMapping(value="/users/{id}" ,produces = "application/vnd.company.appv2+json")
   public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
	   User user = service.findOne(id);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
	   
	   // User -> User2
	   UserV2 userV2 = new UserV2();
	   BeanUtils.copyProperties(user, userV2); // id, name,joinDate,password, ssn
	   userV2.setGrade("VIP");
	   
	   SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
			   filterOutAllExcept("id","name","joinDate","ssn","grade");
	   FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);
	   
	   MappingJacksonValue mapping = new MappingJacksonValue(userV2);
	   mapping.setFilters(filters);
	   return mapping;
   }
   
 
   
   @PutMapping("/users/{id}")
   public void update(@PathVariable int id,User user) {
	   User Updateuser = service.updateUser(id,user);
	   
	   if(user == null) {
		   throw new UserNotFoundException(String.format("id[%s] not found",id));
	   }
   }
}