package com.chase.digital.user_registration.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chase.digital.user_registration.constants.AppConstants;
import com.chase.digital.user_registration.domain.UserAccount;
import com.chase.digital.user_registration.entities.CitiesMasterEntity;
import com.chase.digital.user_registration.entities.CountryMasterEntity;
import com.chase.digital.user_registration.entities.StatesMasterEntity;
import com.chase.digital.user_registration.entities.UserAccountsEntity;
import com.chase.digital.user_registration.props.AppProperties;
import com.chase.digital.user_registration.repository.CitiesMasterRepository;
import com.chase.digital.user_registration.repository.CountryMasterRepository;
import com.chase.digital.user_registration.repository.StatesMasterRepository;
import com.chase.digital.user_registration.repository.UserAccountRepository;
import com.chase.digital.user_registration.util.EmailUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private CountryMasterRepository countriesRepo;

	@Autowired
	private StatesMasterRepository statesRepo;

	@Autowired
	private CitiesMasterRepository citiesRepo;
	
	@Autowired
	private AppProperties approps;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountsEntity entity = userRepo.findByuserEmailAnduserPwd(email, pwd);
		if(entity == null) {
			return approps.getMessages().get(AppConstants.INVLDCREDENTIALS);
		}else if(entity.getAccStatus().equals(AppConstants.LOCKED)) {
			return approps.getMessages().get(AppConstants.ACCLOCKESTATE);
		}else {
			return AppConstants.VALID;
		}
	}

	@Override
	public Map<Integer, String> loadCountriesList() {
		Map<Integer, String> countryMap = new HashMap<Integer, String>();
		List<CountryMasterEntity> entities = countriesRepo.findAll();
		entities.forEach(entity -> {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer, String> statesMap = new HashMap<Integer, String>();
		List<StatesMasterEntity> entities = statesRepo.findBycountryId(countryId);
		entities.forEach(entity -> {
			statesMap.put(entity.getStateId(), entity.getStateName());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer, String> citiesMap = new HashMap<Integer, String>();
		List<CitiesMasterEntity> entities = citiesRepo.findBystateId(stateId);
		entities.forEach(entity -> {
			citiesMap.put(entity.getCityId(), entity.getCityName());
		});
		return citiesMap;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		UserAccountsEntity isUnqEmail = userRepo.findByuserEmail(email);
		return isUnqEmail != null ? false : true;
	}

	@Override
	public boolean saveUserAccount(UserAccount userAcc) {
		userAcc.setAccStatus(AppConstants.LOCKED);
		userAcc.setUserPwd(generateTempPwd());
		UserAccountsEntity entity = new UserAccountsEntity();
		BeanUtils.copyProperties(userAcc, entity);
		UserAccountsEntity save = userRepo.save(entity);
		if(save.getUserId() != null) {
			String to=userAcc.getUserEmail();
			String subject=AppConstants.REG_SUCC_EMAIL_SUB;
			String body=getSuccRegMailBody(userAcc);
			boolean sendSuccRegMail = sendSuccRegMail(to, subject, body);
			return sendSuccRegMail;
		}
		return false;
	}

	@Override
	public String generateTempPwd() {
		String lowerChars = AppConstants.LOWERCHARS;
		String capitalChars = AppConstants.CAPCHARS;
		String symbols = AppConstants.SYMBOLS;
		String numbers = AppConstants.NUMBERS;

		String values = lowerChars + capitalChars + numbers + symbols;

		// Using random() method
		Random rdm_method = new Random();

		// to store our final password
		char[] password = new char[6];

		for (int i = 0; i < 6; i++) {
			// Use of charAt() method to get character value
			// Use of nextInt() as we are scanning the value as int
			password[i] = values.charAt(rdm_method.nextInt(values.length()));

		}

		return password.toString();
	}

	@Override
	public String getSuccRegMailBody(UserAccount userAcc) {
		String filename = AppConstants.UNLOCK_EMAIL_FILE_PATH;
		String body = null;

		Path path = Paths.get(filename, AppConstants.EMP_STR);
		java.util.stream.Stream<String> lines;
		try {
			lines = Files.lines(path);
			List<String> replacedLines = lines.map(
					line -> line.replace(AppConstants.FNAME, userAcc.getFirstName()).replace(AppConstants.LNAME, userAcc.getLastName())
							.replace(AppConstants.TEMP_PWD, userAcc.getUserPwd()).replace(AppConstants.EMAILID, userAcc.getUserEmail()))
					.collect(Collectors.toList());
			body = String.join(AppConstants.EMP_STR, replacedLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	@Override
	public boolean sendSuccRegMail(String to, String subject, String body) {
		return emailUtil.sendMail(to, subject, body);
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		UserAccountsEntity entity = userRepo.findByuserEmailAnduserPwd(email, tempPwd);
		return entity!=null ? true : false;
	}

	@Override
	public boolean unlockAccount(String email, String newPwd) {
		UserAccountsEntity entity = userRepo.findByuserEmail(email);
		entity.setAccStatus(AppConstants.UNLOCKED);
		entity.setUserPwd(newPwd);
		UserAccountsEntity saveEntity = userRepo.save(entity);
		return saveEntity!=null ? true : false;
	}

	@Override
	public boolean recoverpassword(String email) {
		boolean IsPassFlag=false;
		UserAccount userAcc=new UserAccount();
		UserAccountsEntity entity = userRepo.findByuserEmail(email);
		if(entity!=null && entity.getUserId() != null) {
			BeanUtils.copyProperties(entity, userAcc);
			String to=userAcc.getUserEmail();
			String Subject=AppConstants.FRGT_PWD_EMAIL_SUB;
			String body=getRecoverPwdEmailBody(userAcc);
			IsPassFlag=sendPwdToEmail(to, Subject, body);
		}
		return IsPassFlag;
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccount userAcc) {
		String filename = AppConstants.FRGT_PWD_FILE_PATH;
		String body = null;

		Path path = Paths.get(filename, AppConstants.EMP_STR);
		java.util.stream.Stream<String> lines;
		try {
			lines = Files.lines(path);
			List<String> replacedLines = lines.map(
					line -> line.replace(AppConstants.FNAME, userAcc.getFirstName()).replace(AppConstants.LNAME, userAcc.getLastName())
							.replace(AppConstants.PWD, userAcc.getUserPwd()))
					.collect(Collectors.toList());
			body = String.join(AppConstants.EMP_STR, replacedLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	@Override
	public boolean sendPwdToEmail(String to, String subject, String body) {
		// TODO Auto-generated method stub
		return emailUtil.sendMail(to, subject, body);
	}

}
