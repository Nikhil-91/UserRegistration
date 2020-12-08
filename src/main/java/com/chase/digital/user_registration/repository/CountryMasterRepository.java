package com.chase.digital.user_registration.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chase.digital.user_registration.entities.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Serializable> {

}
