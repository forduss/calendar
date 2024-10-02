package com.myweb.diary.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.diary.dto.BoardDto;
import com.myweb.diary.dto.MemberDto;
import com.myweb.diary.service.BoardService;
import com.myweb.diary.service.EmailService;
import com.myweb.diary.service.MemberService;
import com.myweb.diary.service.UserSecurityService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserSecurityService userService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private EmailService mailService;

	// 로그인 페이지 요청
	@GetMapping("/login")
	public String loginPage(Model model) {
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		model.addAttribute("menu", menu);
		
		return "login";
	}
	
	// 회원가입 페이지 요청
	@GetMapping("/join")
	public String joinPage(Model model) {
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		model.addAttribute("menu", menu);
		
		return "join";
	}
	
	// 회원가입 요청
	@PostMapping("/join")
	public String joinAply(Model model, MemberDto dto) {
		// 회원가입 정보 저장
		userService.create(dto);
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		model.addAttribute("menu", menu);
		
		return "index";
	}
	
	// 중복아이디 체크
	@GetMapping("/checkid")
	@ResponseBody
	public String checkId(@RequestParam(value="data") String userid) {
		return String.valueOf(memberService.checkId(userid));
	}
	
	// 회원정보 요청
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member")
	public ModelAndView getMemberInfo(Principal principal) {
		ModelAndView mav = new ModelAndView("member");
		MemberDto dto = new MemberDto();
		dto = memberService.getMemberInfo(principal.getName());
		mav.addObject("member",dto);
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		mav.addObject("menu", menu);
		
		return mav;
	}
	
	//회원정보 수정
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/member")
	public String editMemberInfo(Model model, MemberDto dto) {
		// 회원정보 수정 저장
		dto = memberService.editMemberInfo(dto);
		model.addAttribute("member", dto);
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		model.addAttribute("menu", menu);
		
		return "member";
	}
	//회원탈퇴
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/unregist")
	public String unregistUser(Principal principal) {
		// 회원탈퇴 서비스 요청
		memberService.unregistUser(principal.getName());
		// 로그아웃 리다이렉트
		return "redirect:/logout";
	}
	// 회원관리 요청
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/manage")
	public ModelAndView getMemberList() {
		ModelAndView mav = new ModelAndView("manage");
		List<MemberDto> list = new ArrayList<>();
		list = memberService.getMemberList();
		mav.addObject("memberList",list);
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		mav.addObject("menu", menu);
		
		return mav;
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/editUser")
	public String editUserPw(MemberDto dto) {
		memberService.editUser(dto);
		
		return "redirect:/manage";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deleteUser/{userid}")
	public String deleteUser(@PathVariable("userid") String userid) {
		memberService.deleteUser(userid);
		
		return "redirect:/manage";
	}
	
	@GetMapping("/findpw")
	public String findPw() {
		return "findPwForm";
	}
	
	@PostMapping("/findpw")
	public String findpw(Model model, MemberDto dto) {
		String msg = "";
		
		boolean ck = memberService.checkMember(dto);
		if(ck) msg = "ok";
		else {
			msg = "error";
			model.addAttribute("msg" ,msg);
			return "findPwForm";
		}
		
		// 권한 사용자 설정
		dto.setPermit(0);
		// 임시 패스워드 생성
		String tmppw = UUID.randomUUID().toString().substring(0,8);
		// 임시 패스워드 설정
		dto.setUserpw(tmppw);
		memberService.editUser(dto);
		
		ck = mailService.makeMsgTmpPw(dto);
		if(!ck) {
			msg = "메일전송에 실패했습니다. 잠시후 다시 시도해 주세요.";
		}
		msg = "메일을 통해 임시 비밀번호를 전송했습니다. 메일을 확인해 주세요.";
		model.addAttribute("msg" ,msg);
		return "sendmsg";
	}
	
	@GetMapping("/findId")
	public String findId() {
		return "findIdForm";
	}
	
	@PostMapping("/findId")
	public String findId(Model model, MemberDto dto) {
		String msg = "";
		
		String userid = memberService.getMemberById(dto);
		
		if(userid == null) msg = "해당하는 아이디가 존재하지 않습니다.!!!";
		else msg = "당신의 아이디는 " + userid + "입니다.";
		model.addAttribute("msg" ,msg);
		return "sendmsg";
	}
}
