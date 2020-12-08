package com.chase.digital.user_registration.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chase.digital.user_registration.entities.CitiesMasterEntity;

public interface CitiesMasterRepository extends JpaRepository<CitiesMasterEntity, Serializable> {
	
	public List<CitiesMasterEntity> findBystateId(Integer stateId);

}
