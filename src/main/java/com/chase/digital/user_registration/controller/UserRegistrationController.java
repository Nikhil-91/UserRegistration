package com.chase.digital.user_registration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chase.digital.user_registration.constants.AppConstants;
import com.chase.digital.user_registration.domain.UserAccount;
import com.chase.digital.user_registration.props.AppProperties;
import com.chase.digital.user_registration.service.UserRegistrationService;

@Controller
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationService userService;
	
	@Autowired
	private AppProperties appprops;
	
	@ModelAttribute
	public void loadFormModel(Model model) {
		UserAccount user=new UserAccount();
		model.addAttribute(AppConstants.USER, user);
		model.addAttribute(AppConstants.COUNTRIES, userService.loadCountriesList());
	}
	
	/**
	 * To Load Register Form
	 * @param model
	 * @return
	 */
	@GetMapping("/loadRegForm")
	public String loadRegForm(Model model) {
		model.addAttribute(AppConstants.COUNTRIES, userService.loadCountriesList());
		return AppConstants.REG_VIEW_NAME;
	}
	
	/**
	 * To validate email
	 * @param email
	 * @param model
	 * @return string
	 */
	@GetMapping("/uniqueMailCheck")
	@ResponseBody
	public String isEmailUnique(@RequestParam("email") String email) {
		return userService.isUniqueEmail(email) ? AppConstants.UNIQUE : AppConstants.DUPLICATE;
	}
	
	/**
	 * To Load states by CountryId
	 * @param countryId
	 * @param model
	 * @return string
	 */
	@GetMapping("/countryChange")
	@ResponseBody
	public Map<Integer,String> loadStatesByCountryId(@RequestParam("countryId") Integer countryId){
		return userService.loadStatesByCountryId(countryId);
	}
	
	/**
	 * To Load cities based on StateId
	 * @param StateId
	 * @param model
	 * @return string
	 */
	@GetMapping("/stateChange")
	@ResponseBody
	public Map<Integer,String>  loadCitiesByStateId(@RequestParam("stateId") Integer stateId){
		return userService.loadCitiesByStateId(stateId);
	}
	
	/**
	 * To save User Account details
	 * @param userAcc
	 * @param model
	 * @return viewName
	 */
	@PostMapping("/register")
	public String handleRegisterBtn(UserAccount userAcc,RedirectAttributes redattr) {
		boolean saveUserAccount = userService.saveUserAccount(userAcc);
		if(saveUserAccount) {
			redattr.addFlashAttribute(AppConstants.SUCC_MSG, appprops.getMessages().get(AppConstants.regSucc));
		}else {
			redattr.addFlashAttribute(AppConstants.FAIL_MSG, appprops.getMessages().get(AppConstants.regFail));
		}
		return AppConstants.REDDR_REG_VIEW_NAME;
	}


}
