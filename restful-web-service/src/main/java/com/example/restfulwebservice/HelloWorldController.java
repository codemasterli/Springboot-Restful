package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // Get방식
	// /hello-world (endpoint)
	// @RequestMapping() 보단 @GetMapping 이 더 많이쓰임
	// @RequestMapping(method=RequsestMethod.Get, path="/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	
	// alt + enter
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new  HelloWorldBean("Hello World");
	}
}
