package com.chase.digital.user_registration.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chase.digital.user_registration.entities.StatesMasterEntity;

public interface StatesMasterRepository extends JpaRepository<StatesMasterEntity, Serializable> {
	
	
	public List<StatesMasterEntity> findBycountryId(Integer countryId);

}
