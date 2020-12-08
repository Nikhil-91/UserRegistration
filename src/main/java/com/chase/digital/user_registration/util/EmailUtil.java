package com.chase.digital.user_registration.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendMail(String to,String subject, String body) {
		
		boolean isMailSent=false;
		
		MimeMessage createMimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(createMimeMessage);
		
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(createMimeMessage);
			isMailSent=true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return isMailSent;
	}

}
