package com.chase.digital.user_registration.constants;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	
	public static final String LOGIN_VIEW_NAME="login";
	public static final String DASHBOARD_VIEW_NAME="dashboard";
	public static final String REG_VIEW_NAME="registration";
	public static final String UNLOCK_VIEW_NAME="unlockAccount";
	public static final String RECOVERPWD_VIEW_NAME="recoverPwd";
	
	public static final String REDDR_LOGIN_VIEW_NAME="redirect:index";
	public static final String REDDR_REG_VIEW_NAME="redirect:loadRegForm";
	public static final String REDDR_UNLOCK_VIEW_NAME="redirect:loadUnlockForm?email=";
	public static final String REDDR_RECOVERPWD_VIEW_NAME="redirect:loadrecoverpwd";
	
	public static final String EMAIL="email";
	public static final String PAZZWORD="password";
	public static final String VALID="valid";
	public static final String EMP_STR="";
	public static final String COUNTRIES="countries";
	public static final String USER="user";
	public static final String UNIQUE="UNIQUE";
	public static final String DUPLICATE="DUPLICATE";
	public static final String UNLOCK_ACCOUNT="unlockAcc";
	public static final String LOCKED="LOCKED";
	public static final String UNLOCKED="UNLOCKED";
	public static final String LOWERCHARS="abcdefghijklmnopqrstuvwxyz";
	public static final String CAPCHARS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String SYMBOLS="!@#$%^&*_=+-/.?<>)";
	public static final String NUMBERS="0123456789";
	public static final String UNLOCK_EMAIL_FILE_PATH="User_Account_Unlock_Email.txt";
	public static final String FRGT_PWD_FILE_PATH="Forgot_Password_Email.txt";
	
	
	public static final String FNAME="{fname}";
	public static final String LNAME="{lname}";
	public static final String EMAILID="{emailId}";
	public static final String TEMP_PWD="{tempPwd}";
	public static final String PWD="{Pwd}";
	public static final String REG_SUCC_EMAIL_SUB="Registration Completed || Welcome to ABC Solutions";
	public static final String FRGT_PWD_EMAIL_SUB="Forgot Password | ABC Family";
	
   public static final String MSG="msg";
   public static final String NAME="name";
   public static final String SUCC_MSG="succMsg";
   public static final String FAIL_MSG="errMsg";
   
//   app messages
   public static final String regSucc="regSucc";
   public static final String regFail="regFail";
   
   public static final String UNLOCKSUCC="unLockSucc";
   public static final String PASSFAILUPDATE="passFailUpdate";
   public static final String CNFRMPWDNOTMATCH="cnfrmPwdNotMatch";
   public static final String TMPINVALID="tmpInvalid";
   public static final String ACCUNLOCKEDSTATE="accUnlockedState";
   
   public static final String RECVRPWDSUCC="recvrPwdSucc";
   public static final String RECVRPWDFAIL="recvrPwdFail";
   
   public static final String INVLDCREDENTIALS="invaidCredentials";
   public static final String ACCLOCKESTATE="accLockedState";
   
   
   
	

}
