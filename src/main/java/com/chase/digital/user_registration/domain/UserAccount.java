package com.chase.digital.user_registration.domain;

import java.sql.Timestamp;
import java.util.Date;


import lombok.Data;

@Data
public class UserAccount {

	private String userId;
	private String accStatus;
	private Integer cityId;
	private Integer countryId;
	private Date createdDate;
	private Date dob;
	private String firstName;
	private String gender;
	private Integer stateId;
	private Timestamp updatedDate;
	private String userEmail;
	private String lastName;
	private String userPwd;
	private String userMobile;
}
