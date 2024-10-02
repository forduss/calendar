package com.myweb.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.myweb.diary.dto.EmailDto;
import com.myweb.diary.dto.MemberDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	private final String FROM_MAIL_KAKAO = "xpdlfys5032@kakao.com";
	
	@Async
	public boolean sendMailReject(EmailDto mail){
		boolean msg = true;
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			System.out.println(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setFrom(FROM_MAIL_KAKAO);
			helper.setText(mail.getText(), true);
			
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			msg = false;
		}
		
		return msg;
	}
	
	public boolean makeMsgTmpPw(MemberDto dto) {
		boolean result = false;
		EmailDto mail = new EmailDto();
		mail.setSubject("임시 비밀번호를 발행했습니다.");
		mail.setTo(dto.getEmail());
		String txt = "로그인을 위한 임시 비밀번호를 발행했습니다.<br><br>"
				   + "임시 비밀번호 : " + dto.getUserpw() + "<br><br>"
				   + "로그인 후 반드시 비밀번호 변경을 해주세요.<br>";
		mail.setText(txt);
		
		result = sendMailReject(mail);
		
		return result;
	}

	public boolean makeMsgTmpId(MemberDto dto) {
		boolean result = false;
		EmailDto mail = new EmailDto();
		mail.setSubject("아이디를 발행했습니다.");
		mail.setTo(dto.getEmail());
		String txt = "아이디를 발행했습니다.<br><br>"
				   + "아이디 : " + dto.getUserid() + "<br><br>"
				   + "로그인해주세요.<br>";
		mail.setText(txt);
		
		result = sendMailReject(mail);
		
		return result;
	}

}
