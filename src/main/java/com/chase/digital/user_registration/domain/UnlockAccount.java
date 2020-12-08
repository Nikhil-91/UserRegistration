package com.chase.digital.user_registration.domain;

import lombok.Data;

@Data
public class UnlockAccount {
	private String email;
	private String tmpPwd;
	private String newPwd;
	private String cnfrmPwd;
}
