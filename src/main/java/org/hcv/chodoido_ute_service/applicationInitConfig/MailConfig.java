package org.hcv.chodoido_ute_service.applicationInitConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${spring.mail.port}")
    private int MAIL_POST;
    @Value("${spring.mail.host}")
    private String MAIL_HOST ;
    @Value("${spring.mail.username}")
    private String MAIL_USERNAME;
    @Value("${spring.mail.password}")
    private String MAIL_PASS ;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MAIL_HOST);
        mailSender.setPort(MAIL_POST);
        mailSender.setUsername(MAIL_USERNAME);
        mailSender.setPassword(MAIL_PASS);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return mailSender;
    }
}
