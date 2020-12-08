package com.chase.digital.user_registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chase.digital.user_registration.constants.AppConstants;
import com.chase.digital.user_registration.domain.UnlockAccount;
import com.chase.digital.user_registration.entities.UserAccountsEntity;
import com.chase.digital.user_registration.props.AppProperties;
import com.chase.digital.user_registration.repository.UserAccountRepository;
import com.chase.digital.user_registration.service.UserRegistrationService;

@Controller
public class UnlockAccountController {
	
	
	@Autowired
	private UserRegistrationService service;
	
	@Autowired
	private UserAccountRepository userRepo;
	
	@Autowired
	private AppProperties approps;
	
	/**
	 * To Load Unlock Account Form
	 * @param email
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/unlockAccount","loadUnlockForm"})
	public String loadUnlockAccountForm(@RequestParam("email") String email,Model model) {
		UnlockAccount unlockAcc=new UnlockAccount();
		unlockAcc.setEmail(email);
		model.addAttribute(AppConstants.UNLOCK_ACCOUNT, unlockAcc);
		return AppConstants.UNLOCK_VIEW_NAME;
	}
	
	/**
	 * To save unlock Account Details
	 * @param unlockAcc
	 * @param model
	 * @return
	 */
	@PostMapping("/unlockAccount")
	public String saveUnlockAccount(@ModelAttribute("unlockAcc") UnlockAccount unlockAcc,RedirectAttributes raddr) {
		UserAccountsEntity userEntity = userRepo.findByuserEmail(unlockAcc.getEmail());
		if(userEntity.getAccStatus().equals(AppConstants.LOCKED)) {
			boolean tempPwdValid = service.isTempPwdValid(unlockAcc.getEmail(), unlockAcc.getTmpPwd());
			if(tempPwdValid) {
				if(unlockAcc.getNewPwd().equals(unlockAcc.getCnfrmPwd())){
					
					boolean unlockAccount = service.unlockAccount(unlockAcc.getEmail(), unlockAcc.getNewPwd());
					if(unlockAccount) {
						raddr.addFlashAttribute(AppConstants.SUCC_MSG, approps.getMessages().get(AppConstants.UNLOCKSUCC));
					}else {
						raddr.addFlashAttribute(AppConstants.FAIL_MSG, approps.getMessages().get(AppConstants.PASSFAILUPDATE));
					}
				}else {
					raddr.addFlashAttribute(AppConstants.FAIL_MSG, approps.getMessages().get(AppConstants.CNFRMPWDNOTMATCH));
				}
				
			}else {
				raddr.addFlashAttribute(AppConstants.FAIL_MSG, approps.getMessages().get(AppConstants.TMPINVALID));
			}
		}else {
			raddr.addFlashAttribute(AppConstants.SUCC_MSG, approps.getMessages().get(AppConstants.ACCUNLOCKEDSTATE));
		}
		 
		
		return AppConstants.REDDR_UNLOCK_VIEW_NAME+unlockAcc.getEmail();
		
	}

}
