package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("adfd").build();
		System.out.println(TAG+"getter:"+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter:"+m.getId());
		return "lombok test 완료";
		
		
	}
	
	//인터넷 브라우저 요청은 get 요청밖에 할수 없다.
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청" ;
	}
	
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
