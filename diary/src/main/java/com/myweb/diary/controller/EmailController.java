package com.myweb.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myweb.diary.dto.EmailDto;
import com.myweb.diary.service.EmailService;


@Controller
public class EmailController {
	@Autowired
	private EmailService email;
	
	@GetMapping("/inquire")
	public String inquireForm() {
		return "inquireForm";
	}
	
	@PostMapping("/sendMail")
		public String sendMail(EmailDto msg) {
		
		
			if(email.sendMailReject(msg))
				System.out.println("Email 전송 성공!!!");
			else System.out.println("Email 전송 실패!!!");
			
			return "redirect:/";
	}

}
