package only.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import only.dao.MemberDao;
import only.model.Member;
import only.model.Role;
import only.model.User;

@Service("userService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDao md;
	
	@Override
	public User loadUserByUsername(String userid) throws UsernameNotFoundException {
		System.out.println("LoadUserByUsername called");
		Member member = md.getMemberById(userid);
		// 회원 정보 dao 에서 데이터를 읽어 옴.
		// test 값을 암호화함.
		if(member == null) {
			System.out.println("멤버를 찾을 수 없음");
			throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");
		}
		User user = new User();
		user.setUsername(member.getUserid());
		user.setPassword(member.getPassword());
		Role role = new Role();
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setAuthorities(roles);
		return user;
	}
}
