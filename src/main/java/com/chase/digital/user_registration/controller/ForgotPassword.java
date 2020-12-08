package com.chase.digital.user_registration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chase.digital.user_registration.constants.AppConstants;
import com.chase.digital.user_registration.props.AppProperties;
import com.chase.digital.user_registration.service.UserRegistrationService;

@Controller
public class ForgotPassword {
	
	@Autowired
	private UserRegistrationService service;
	
	@Autowired
	private AppProperties approps;
	
	@GetMapping(value= {"/recoverPwd","loadrecoverpwd"})
	public String loadRecoverPwdForm() {
		
		return AppConstants.RECOVERPWD_VIEW_NAME;
	}
	
	@PostMapping("/recoverPwd")
	public String handleForgotPwdSubmtBtn(HttpServletRequest req,RedirectAttributes raddr) {
		String email=req.getParameter(AppConstants.EMAIL);
		System.out.println(email);
		boolean recoverpassword = service.recoverpassword(email);
		System.out.println(recoverpassword);
		if(recoverpassword) {
			raddr.addFlashAttribute(AppConstants.SUCC_MSG,approps.getMessages().get(AppConstants.RECVRPWDSUCC));
		}else {
			raddr.addFlashAttribute(AppConstants.FAIL_MSG,approps.getMessages().get(AppConstants.RECVRPWDFAIL));
		}
		return AppConstants.REDDR_RECOVERPWD_VIEW_NAME;
	}

}
