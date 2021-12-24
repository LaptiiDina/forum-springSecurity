package telran.b7a.security.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.UserAccountRepository;
import telran.b7a.accounting.model.UserAccount;
import telran.b7a.forum.dao.PostRepository;
import telran.b7a.forum.model.Post;
@Service("customSecurity")
public class CustomWebSecurity {
	PostRepository repository;
	UserAccountRepository userRepository;
	@Autowired
public CustomWebSecurity(PostRepository repository,UserAccountRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

public boolean checkPostAuthority(String postId,String userName) {
	Post post = repository.findById(postId).orElse(null);
	return post!=null && userName.equals(post.getAuthor());
	
}
public boolean checkExpiredDatePassword(String login) {
	
	boolean result = false;
	UserAccount user = userRepository.findById(login).orElse(null);
	if(user.getExpiredPassword().isAfter(LocalDateTime.now())) {
		result = true;
	}
	else {
	System.out.println("Please change password");}
	return result;
}
}
