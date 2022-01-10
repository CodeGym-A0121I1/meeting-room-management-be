package vn.codegym.meetingroommanagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class MeetingRoomManagementBeApplication {

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // host & port of GOOGLE
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        // get username & password (password of application in GoogleManagement) of admin or me
        mailSender.setUsername("trungtrongcr21@gmail.com");
        mailSender.setPassword("jztxeayxitjsifso");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomManagementBeApplication.class, args);
    }

}