package com.hsa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.hsa.entity.HsaAccountMasterEntity;

@Component
public class EmailUtils {
	
	private JavaMailSender  mailSender;

	public EmailUtils(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
	public boolean sendMail(HsaAccountMasterEntity masterEntity) {
		
		    MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
		    
		     try {
				messageHelper.setTo("tnprasad.emailid9@gmail.com");
				messageHelper.setSubject("Account Activation Link.....");
				messageHelper.setText(getMailDescBody(masterEntity));
				
				mailSender.send(mimeMessage);
				return true;
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		    
		    
		return false;
	}
	
	private static String getMailDescBody(HsaAccountMasterEntity masterEntity) {
		StringBuilder sb=new StringBuilder("");
		String mailBody="";
		String filePath="D:\\UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		
		try {
			
			FileReader fr=new FileReader(new File(filePath));
			
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			
			while (line !=null) {
				sb.append(line);
				line=br.readLine();
			}
			br.close();
			
			mailBody=sb.toString();
			mailBody=mailBody.replace("{FNAME}", masterEntity.getFirstName());
			mailBody=mailBody.replace("{LNAME}", masterEntity.getLastName());
			mailBody=mailBody.replace("{TEMP-PWD}", masterEntity.getTempPassword());
			mailBody=mailBody.replace("{EMAIL}", masterEntity.getEmailId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("mailbody:::   "+mailBody);
		return mailBody;
	}
	
	

}
