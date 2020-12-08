package com.chase.digital.user_registration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_MASTER")
public class CountryMasterEntity {
	
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	@Column(name="COUNTRY_NAME")
	private String countryName;

}
