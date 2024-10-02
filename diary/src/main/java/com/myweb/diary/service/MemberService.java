package com.myweb.diary.service;

import java.util.List;

import com.myweb.diary.dto.MemberDto;


public interface MemberService {
	boolean putMember(MemberDto dto);
	boolean checkId(String userid);
	MemberDto getMemberInfo(String userid);
	MemberDto editMemberInfo(MemberDto dto);
	void unregistUser(String userid);
	List<MemberDto> getMemberList();
	void deleteUser(String userid);
	void editUser(MemberDto dto);
	boolean checkMember(MemberDto dto);
	String getMemberById(MemberDto dto);
}
