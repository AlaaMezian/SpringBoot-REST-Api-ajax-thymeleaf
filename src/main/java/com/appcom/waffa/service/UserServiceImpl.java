package com.appcom.waffa.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.AdminUser;
import com.appcom.waffa.entity.User;
import com.appcom.waffa.exceptions.BadRequestException;
import com.appcom.waffa.exceptions.ForbiddenException;
import com.appcom.waffa.exceptions.InternalServerErrorException;
import com.appcom.waffa.model.AdminUserModel;
import com.appcom.waffa.model.RegistrationModel;
import com.appcom.waffa.model.UserModel;
import com.appcom.waffa.respository.AdminUserRepository;
import com.appcom.waffa.respository.UserRepository;
import com.appcom.waffa.utils.CoreValidations;
import com.appcom.waffa.utils.CurrentDate;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminUserRepository adminUserRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AdminUser findUserByEmail(String email) {
		return adminUserRepository.findByUserEmail(email);
	}

	@Override
	public void saveUser(RegistrationModel registrationModel) {
		try {
			Date currentDate = CurrentDate.getCurrentDate();
			User userEntity = new User();

			if (!CoreValidations.isAlphaNumeric(registrationModel.getUserName())) {
				throw new BadRequestException("userName is not valid is should only contain letters and numbers");
			}

			// validation of phone number todo
			CoreValidations.validatePhoneNumber(registrationModel.getMobileNumber());

			// Email validation
			if (!CoreValidations.isEmailValid(registrationModel.getUserEmail())) {
				throw new BadRequestException("email is not valid please enter a valid email format ");
			}

			User userExists = userRepository.findByUserEmail(registrationModel.getUserEmail());
			if (userExists != null) {
				throw new ForbiddenException("user already signedUp,please SignUp with an other email");
			}

			User userFoundByUserName = userRepository.findByUsername(registrationModel.getUserName());
			if (userFoundByUserName != null) {
				throw new ForbiddenException("the userName you are trying to signUp with is already taken");
			}

			userEntity.setUsername(registrationModel.getUserName());
			userEntity.setUserEmail(registrationModel.getUserEmail());
			userEntity.setPassword(bCryptPasswordEncoder.encode(registrationModel.getPassword()));
			userEntity.setIsActive(Status.Y);
			userEntity.setMobileNumber(registrationModel.getMobileNumber());
			userRepository.save(userEntity);
		} catch (InternalServerErrorException ex) {
			logger.error("---------------------------------------------------------------------------------------");
			logger.error("Error when sign up user", ex);
			logger.error("----------------------------------------------------------------------------------------");
			throw ex;
		}
	}

	@Override
	public void editProfile(UserModel userModel, int id) {
		User userEntity = userRepository.findById(id);
	
		CoreValidations.validatePhoneNumber(userModel.getMobile());
		if (userEntity == null) {
			throw new BadRequestException("sorry the user you are trying to edit is no longer registered");
		} 
		else {
			userEntity.setUserEmail(userModel.getEmail());
			userEntity.setMobileNumber(userModel.getMobile());
			userEntity.setPassword(userModel.getPassword());
		}
		userRepository.save(userEntity);

	}

	@Override
	public void signUpAdmin(AdminUserModel adminModel) {
		AdminUser admin = new AdminUser();
		admin.setIsActive(Status.Y);
		admin.setPassword(bCryptPasswordEncoder.encode(adminModel.getPass()));
		admin.setUserEmail(adminModel.getUserEmail());
		admin.setUserName(adminModel.getUserName());
		adminUserRepository.save(admin);
	}

}
