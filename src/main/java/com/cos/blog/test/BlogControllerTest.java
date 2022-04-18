package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
	
	 
    @GetMapping("/")
    public String root_test() {
        return "Hello Root(/)";
    }
 
    @RequestMapping("/demo")
    public String demo_test() throws Exception{
        return "Hello demo(/demo)";
    }


}
