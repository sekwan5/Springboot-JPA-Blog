package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


//DAO
//자동으로 bean등록이 돤다.
//@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
