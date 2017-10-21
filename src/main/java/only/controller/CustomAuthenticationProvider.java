package only.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
	
import only.model.User;
import only.service.CustomUserDetailsService;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService userservice;
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("test");
		String user_id = (String) authentication.getPrincipal();
		String user_pw = (String) authentication.getCredentials();

		System.out.println("Welcome authenticate! {}"+ user_id + "/" + user_pw);

		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User member = null;
        Collection<GrantedAuthority> authorities = null;
        try {
            member =  userservice.loadUserByUsername(username);
            System.out.println(username + password +"done");
            
            // �씠�슜�옄媛� 濡쒓렇�씤 �뤌�뿉�꽌 �엯�젰�븳 鍮꾨�踰덊샇�� DB濡쒕��꽣 媛��졇�삩 �븫�샇�솕�맂 鍮꾨�踰덊샇瑜� 鍮꾧탳�븳�떎
            System.out.println("member pw: "+ member.getPassword() + user_pw);
            if (!member.getPassword().equals(user_pw)) {
            	throw new BadCredentialsException("鍮꾨�踰덊샇 遺덉씪移�");
            }
            authorities = (Collection<GrantedAuthority>) member.getAuthorities();
        } catch(UsernameNotFoundException e) {
            System.out.println("�뾾�뒗 �븘�씠�뵒�엯�땲�떎");
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            System.out.println("鍮꾨�踰덊샇 遺덉씪移�");
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
        	System.out.println("Login error : " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Add new Auth Token: " + username + ", " + password + ", " + authorities);
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

}
