package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	
	//save 함수는 id 를 전달하지 않으면 insert 를 해주고 
	//save 함수는 id 를 전달하면 id에 대한 데이터가 잇으면 update를 해주고
	//save 함수는 id 를 전달하면 해당 id에 대한 데이터가 없으면 insert
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			
			return new IllegalArgumentException("수정실패");
			
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		//userRepository.save(user);
		return null;
		
	};
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll()	;
	}
	
	//한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	// user/4를 찾으면 내가 데이터에서 못찾게 되면 user가 null이 될것 아냐?
	//그럼 return null이 리턴됨 
	//Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 ruturn
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) throws IllegalArgumentException {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id" + id);
			}
		});
		return user;
		
	}
	
	protected User User() {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username : " + user.getUsername());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}

}
