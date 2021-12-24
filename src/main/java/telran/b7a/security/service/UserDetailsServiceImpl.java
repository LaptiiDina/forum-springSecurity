package telran.b7a.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.UserAccountRepository;
import telran.b7a.accounting.model.UserAccount;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
UserAccount userAccount = repository.findById(username)
.orElseThrow(()-> new UsernameNotFoundException(username));
String[]roles = userAccount.getRoles()
.stream()
.map(r->"ROLE_"+r.toUpperCase())
.toArray(String[]::new);
		return new User(username,userAccount.getPassword(),
				AuthorityUtils.createAuthorityList(roles));
	}

}
