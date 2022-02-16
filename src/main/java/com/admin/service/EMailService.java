package com.admin.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.util.TokenUtil;


@Component
public class EMailService {

	@Autowired
	TokenUtil tokenUtil;

	// all the content of the mail is taken as parameter to this given method of the
	// java mail service
	public  void send(String toEmail, String subject, String body) {
		final String fromEmail = "nithunkarur@gmail.com";
		// requires valid gmail id
		final String password = "nk@0112#KA"; // correct password for gmail id
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		// SMTP Host
		props.put("mail.smtp.port", "587");
		// TLS Port
		props.put("mail.smtp.auth", "true");
		// enable authentication
		props.put("mail.smtp.starttls.enable", "true");
		// enable STARTTLS
		// to check email sender credentials are valid or not
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);

			}
		};
		
		
		Session session = Session.getInstance(props, auth);
		//System.out.println(session);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("rknithun@gmail.com", "NoReply"));
			msg.setReplyTo(InternetAddress.parse("rknithun@gmail.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
			System.out.println("Email Sent Successfully.........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// body for the given user link is created here
	public String getLink(String link, long id) {
		return link + tokenUtil.createToken(id);
	}
	
	
}

