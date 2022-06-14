package com.example.restfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 


@Data  // 게터 세터 메소드
@AllArgsConstructor //생성자 만드는거
@NoArgsConstructor
public class HelloWorldBean {
    private String message;
    
	/* 중복되서 오류임
	 * public HelloWorldBean(String message) { this.message = message; }
	 */
}
