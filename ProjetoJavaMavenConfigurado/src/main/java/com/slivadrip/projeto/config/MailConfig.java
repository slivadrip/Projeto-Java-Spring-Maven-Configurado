package com.slivadrip.projeto.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
//@ComponentScan(basePackageClasses = Mailer.class)
@PropertySource({ "classpath:env/mail-${ambiente:local}.properties" })
//@PropertySource(value = { "file://${HOME}/.speed-mail.properties" }, ignoreResourceNotFound = true)
public class MailConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setUsername("posse.ueg.speed.system");
		mailSender.setPassword(env.getProperty("password"));
		
		System.out.println(">>>>>>>>>"+env.getProperty("username"));
		System.out.println(">>>>>>>>"+env.getProperty("password"));

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", false);
		props.put("mail.smtp.connectiontimeout", 10000); // miliseconds

		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}
	
}
