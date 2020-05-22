package com.vbp.quizzery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vbp.quizzery.exception.UserServiceException;
import com.vbp.quizzery.io.entity.UserEntity;
import com.vbp.quizzery.io.entity.VerificationToken;
import com.vbp.quizzery.io.repositories.UserRepository;
import com.vbp.quizzery.io.repositories.VerificationTokenRepository;
import com.vbp.quizzery.service.UserService;
import com.vbp.quizzery.shared.Utils;
import com.vbp.quizzery.shared.dto.UserDto;
import com.vbp.quizzery.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; // password encoder given from Spring

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity foundUser = userRepository.findByEmail(user.getEmail());

		if (foundUser != null)
			throw new RuntimeException("record exists"); // check if user already exists (unique email)

//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(user, userEntity);

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		String publicUserId = utils.generateUserId(30); // random user id 30 alphanumeric characters long
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);

		UserEntity storedUserDetails = userRepository.save(userEntity);

		VerificationToken verificationToken = new VerificationToken(userEntity);

		verificationTokenRepository.save(verificationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userEntity.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("quizzerywebapp@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/Quizzery/confirm-account?token=" + verificationToken.getToken());

		emailSenderService.sendEmail(mailMessage);

		// UserDto returnValue = new UserDto();
		// BeanUtils.copyProperties(storedUserDetails, returnValue);

		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		System.out.println(returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getUserRole().name()));

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
				userEntity.getEmailVerificationStatus(), true, true, true, authorities);

	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;

	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setUserName(user.getUserName());

		UserEntity updatedUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		// UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userRepository.delete(userEntity);
		// BeanUtils.copyProperties(userEntity, returnValue);
		// return returnValue;

	}

}
