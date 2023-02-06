package com.example.crossFit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "fitness.mail.enabled", havingValue = "true")
public class MailConfig {

    @Value("${fitness.mail.host}")
    private String host;
    @Value("${fitness.mail.username}")
    private String username;
    @Value("${fitness.mail.password}")
    private String password;
    @Value("${fitness.mail.port}")
    private Integer port;
    @Value("${fitness.mail.protocol}")
    private String protocol;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setPort(port);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", protocol);

        return mailSender;
    }

}
