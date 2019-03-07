package com.healthcare.utility;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigUtility {

    @Inject
    private Environment env;

    public String getProperty(String pPropertyKey) {
    	System.out.println("Property key : "+pPropertyKey);
        return env.getProperty(pPropertyKey);
    	
    }
} 
