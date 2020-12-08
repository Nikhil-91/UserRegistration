package com.chase.digital.user_registration.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="user_accounts")
public class UserAccountsEntity {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	@Column(name="acc_status")
	private String accStatus;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="created_Date")
	@CreationTimestamp
	private Date createdDate;
	@Column(name="DOB")
	private Date dob;
	@Column(name="first_name")
	private String firstName;
	@Column(name="gender")
	private String gender;
	@Column(name="state_id")
	private Integer stateId;
	@UpdateTimestamp
	@Column(name="updated_Date")
	private Timestamp updatedDate;
	@Column(name="user_email")
	private String userEmail;
	@Column(name="last_name")
	private String lastName;
	@Column(name="user_pwd")
	private String userPwd;
	@Column(name="user_mobile")
	private String userMobile;
	

}
