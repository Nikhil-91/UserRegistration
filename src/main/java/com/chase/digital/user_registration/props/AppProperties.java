package com.chase.digital.user_registration.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix="user-reg-app")
public class AppProperties {
	
	private Map<String,String> messages=new HashMap<>();

}
