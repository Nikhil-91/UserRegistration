package com.chase.digital.user_registration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="states_master")
public class StatesMasterEntity {
	@Id
	@GeneratedValue
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="state_name")
	private String stateName;

}
