package com.myweb.diary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.diary.dao.MemberDao;
import com.myweb.diary.dto.MemberDto;
import com.myweb.diary.role.UserRole;

@Service
public class UserSecurityService implements UserDetailsService {
	@Autowired
	private final MemberDao dao;
	private final PasswordEncoder passwordEncoder;
	
	public UserSecurityService(MemberDao dao, PasswordEncoder passwordEncoder) {
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
		}
	
	public MemberDto create(MemberDto dto) {
		dto.setUserpw(passwordEncoder.encode(dto.getUserpw()));
		this.dao.insertMember(dto);
		return dto;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException{
		MemberDto member = this.dao.getByUserId(userid);
		if(member == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(member.getPermit() == 9) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}
		else if(member.getPermit() == 8) {
			authorities.add(new SimpleGrantedAuthority(UserRole.MANAGER.getValue()));
		}
		else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(member.getUserid(), member.getUserpw(),authorities);
	}
}
