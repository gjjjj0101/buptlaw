package edu.bupt.law.security.constants;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SecurityConstants {

    public static String SECRET_KEY = "secret";
    public static String EXPIRATION_TIME = "86400000";


}
