package com.waffa.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.waffa.constant.Status;
import com.waffa.entity.User;
import com.waffa.exception.code.RegistrationExceptionCode;
import com.waffa.exceptions.RegistrationException;
import com.waffa.model.RegistrationModel;
import com.waffa.respository.UserRepository;
import com.waffa.utils.CoreValidations;
import com.waffa.utils.CurrentDate;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	


	@Autowired
	private UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public void saveUser(RegistrationModel registrationModel) {
		try {
		Date currentDate = CurrentDate.getCurrentDate();
		User userEntity = new User();

		if (!CoreValidations.isAlphaNumeric(registrationModel.getUserName())) {
			throw new RegistrationException(RegistrationExceptionCode.USER_NAME_NOT_VALID,
					registrationModel.getUserName());
		}

		// Email validation
		if (!CoreValidations.isEmailValid(registrationModel.getUserEmail())) {
			throw new RegistrationException(RegistrationExceptionCode.INVALID_EMAIL_EXCEPTION,
					registrationModel.getUserEmail());
		}
		
		  User userExists = userRepository.findByUserEmail(registrationModel.getUserEmail());
         if(userExists != null)
         {
        	 throw new RegistrationException(RegistrationExceptionCode.EMAIL_ALREADY_REGISTRED,
        			 registrationModel.getUserEmail());
         }

		userEntity.setUserName(registrationModel.getUserName());
		logger.info("the user name sent is "+ registrationModel.getUserName());
		userEntity.setUserEmail(registrationModel.getUserEmail());
		userEntity.setEncPassword(bCryptPasswordEncoder.encode(registrationModel.getPassword()));
		userEntity.setIsActive(Status.Y);
		userEntity.setMobileNumber(registrationModel.getMobileNumber());
		userEntity.setRegDate(currentDate);
		userRepository.save(userEntity);
		}
		catch (RegistrationException ex) {
			logger.error("---------------------------------------------------------------------------------------");
			logger.error("Error when sign up user", ex);
			logger.error("----------------------------------------------------------------------------------------");
			throw ex;
		} catch (Exception e) {
			logger.error("----------------------------------------------------------------------------------------");
			logger.error("Error when sign up user", e);
			logger.error("----------------------------------------------------------------------------------------");
			throw new RegistrationException(RegistrationExceptionCode.SIGNUP_EXCEPTION, "");
		}
	}

}
