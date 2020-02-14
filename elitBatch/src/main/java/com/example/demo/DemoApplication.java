package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private JavaMailSender mailSender;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private void sendByMail(User user, String url, String base_config) {
		String subject = messageSource.getMessage(base_config + ".subject", null, null);
		String body = String.format(messageSource.getMessage(base_config + ".body", null, null), user.getUsername(),
				url);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("noreply@quizzz.com");
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailSender.send(mailMessage);
	}
//	private void sendByMail(User user, String url, String base_config) {
//		String subject = messageSource.getMessage(base_config + ".subject", null, null);
//		String body = String.format(messageSource.getMessage(base_config + ".body", null, null), user.getUsername(),
//				url);
//
//
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//
//
//		mailMessage.setTo(user.getEmail());
//		mailMessage.setFrom("noreply@quizzz.com");
//		mailMessage.setSubject(subject);
//		mailMessage.setText(body);
//
//		mailSender.send(mailMessage);
//	}

}

