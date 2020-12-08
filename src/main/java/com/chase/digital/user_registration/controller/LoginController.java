package com.chase.digital.user_registration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chase.digital.user_registration.constants.AppConstants;
import com.chase.digital.user_registration.service.UserRegistrationService;

@Controller
public class LoginController {
	
	@Autowired
	private UserRegistrationService service;
	
	/**
	 * This Method is used to load Login Form
	 * @return LoginPage
	 */
	@GetMapping(value= {"/","index"})
	public String loadHomeForm() {
		
		return AppConstants.LOGIN_VIEW_NAME;
	}
	
	/**
	 * This is used to validate login Form credentials
	 * @param model
	 * @return viewName
	 */
	@PostMapping(value="/saveLogin")
	public String handleSubmitSignInBtn(HttpServletRequest request, RedirectAttributes rddr) {
		String viewName=AppConstants.EMP_STR;
		String email=request.getParameter(AppConstants.EMAIL);
		String pwd=request.getParameter(AppConstants.PAZZWORD);
		String status = service.loginCheck(email,pwd);
		if(status.equals(AppConstants.VALID)) {
			viewName=AppConstants.DASHBOARD_VIEW_NAME;
		}else {
			viewName=AppConstants.REDDR_LOGIN_VIEW_NAME;
			rddr.addFlashAttribute(AppConstants.MSG, status);
			rddr.addFlashAttribute(AppConstants.NAME, email);
		}
		return viewName;
	}

}
