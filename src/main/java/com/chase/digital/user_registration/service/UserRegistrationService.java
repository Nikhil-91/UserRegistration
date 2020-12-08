package com.chase.digital.user_registration.service;

import java.util.Map;

import com.chase.digital.user_registration.domain.UserAccount;

public interface UserRegistrationService {
	
	public String loginCheck(String email, String pwd);
	
	public Map<Integer,String> loadCountriesList();
	public Map<Integer,String> loadStatesByCountryId(Integer countryId);
	public Map<Integer,String> loadCitiesByStateId(Integer stateId);
	public boolean isUniqueEmail(String email);
	public boolean saveUserAccount(UserAccount userAcc);
	public String generateTempPwd();
	public String getSuccRegMailBody(UserAccount userAcc);
	public boolean sendSuccRegMail(String to,String subject,String body);
	
	public boolean isTempPwdValid(String email,String tempPwd);
	public boolean unlockAccount(String email,String newPwd);
	
	public boolean recoverpassword(String email);
	public String getRecoverPwdEmailBody(UserAccount userAcc);
	public boolean sendPwdToEmail(String to,String subject,String body);

}
