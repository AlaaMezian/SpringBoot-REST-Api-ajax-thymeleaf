package com.appcom.waffa.service;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appcom.waffa.entity.User;
import com.appcom.waffa.exceptions.BadRequestException;
import com.appcom.waffa.exceptions.NotFoundException;
import com.appcom.waffa.mail.thymleaf.MailClient;
import com.appcom.waffa.mail.thymleaf.MailContentBuilder;
import com.appcom.waffa.model.ChangePasswordModel;
import com.appcom.waffa.respository.UserRepository;
import com.appcom.waffa.utils.ApplicationLogger;

@Service
public class PasswordServiceImpl implements PasswordService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired @Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//    public EmailServiceImpl emailService;
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
    private MailContentBuilder mailContentBuilder;

	@Override
	public void changePassword(int userId, ChangePasswordModel changeMdl) {
		User user = userRepo.findById(userId);
		
		logger.info("the user object " + user);
		if(user == null) 
		{
			throw new NotFoundException("Unable to update requested User,User is Not Found");
		}
		if(!passwordEncoder.matches(changeMdl.getOldPassword(), user.getPassword()))
		{
			throw new BadRequestException("please enter your valid old password");
		}


		user.setPassword(passwordEncoder.encode(changeMdl.getNewPassword()));
		userRepo.save(user);

	}



	@Override
	public void forgetPassowrd(String email) {
		
		User user = userRepo.findByUserEmail(email);
		if(user == null) 
		{
			throw new BadRequestException("not registred email,please enter a valid email to update the password");
		}		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 8, characters );
		
		ApplicationLogger logger = ApplicationLogger.getInstance();
		logger.info("new password is generated " + pwd);
		user.setPassword(passwordEncoder.encode(pwd));
		userRepo.save(user);
		
		try {
			MailClient mailService = new MailClient(mailSender , mailContentBuilder );
//			emailService.sendHTMLMail(new HTMLMail(email));
			mailService.prepareAndSend(email,"Thanks for using waffa beauty center",pwd );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	

}
