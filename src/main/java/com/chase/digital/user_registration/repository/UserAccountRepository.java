package com.chase.digital.user_registration.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chase.digital.user_registration.entities.UserAccountsEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountsEntity, Serializable> {
	
	
	public UserAccountsEntity findByuserEmail(String email);
	
	@Query("from UserAccountsEntity where userEmail=:email and userPwd=:pwd")
	public UserAccountsEntity findByuserEmailAnduserPwd(@Param("email") String email,@Param("pwd") String pwd);
 
}
