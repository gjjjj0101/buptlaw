package edu.bupt.buptlaw.constants;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConstants {

    @Value("${jwt.secret}")
    public static String SECRET_KEY;
    @Value("${jwt.expiration-delay}")
    public static String EXPIRATION_TIME;
}
