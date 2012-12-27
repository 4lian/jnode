package com.lcm.jnode.utils;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtils {
	
	private static String HOST_NAME = "mails.pearlinpalm.com";
	private static String SENDER = "techadmin";
	private static String PASSWORD = "tech101122";
	private static String from = "techadmin@pearlinpalm.com";
	public static void config(Email email){
		email.setHostName(HOST_NAME);
		email.setAuthentication(SENDER, PASSWORD);
		try {
			email.setFrom(from);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setCharset("UTF-8");
	}
	
	/**
	 * email send
	 * @param subject
	 * @param msg
	 * @param receiver
	 * @throws EmailException
	 */
	public static void sendSimpleEmail(String subject , String msg , String receiver) throws EmailException{
		HtmlEmail email = new HtmlEmail();
		config(email);
		email.setSubject(subject);
		email.addTo(receiver);
		email.setMsg(msg);
		email.send();
	}
}
