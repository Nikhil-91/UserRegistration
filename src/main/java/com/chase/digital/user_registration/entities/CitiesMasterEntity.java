package com.chase.digital.user_registration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="cities_master")
public class CitiesMasterEntity {
	
	@Id
	@GeneratedValue
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="city_name")
	private String cityName;
	@Column(name="state_id")
	private Integer stateId;
	

}
