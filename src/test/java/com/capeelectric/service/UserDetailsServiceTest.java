package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

	@MockBean
	private UserRepository userRepository;

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@MockBean
	private BCryptPasswordEncoder passwordEncoder;

	private User user;

	private Optional<User> optionaluser;

	{
		user = new User();
		user.setUsername("lvsystem@capeindia.net");
		user.setPassword("cape");
		user.setEmail("lvsystem@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);
		user.setOtp(1234);

		optionaluser = Optional.of(user);
	}

	@Test
	public void testFindByUserName() throws ForgotPasswordException, IOException {
		Optional<User> userlist = null;
		userlist = Optional.ofNullable(user);

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(userlist);
		User findByUserName = userDetailsServiceImpl.findByUserName("lvsystem@capeindia.net");
		assertEquals("lvsystem@capeindia.net", findByUserName.getUsername());
		
		ForgotPasswordException assertThrows = Assertions.assertThrows(ForgotPasswordException.class,
				() -> userDetailsServiceImpl.findByUserName(null));
		assertEquals("Email-Id Is Required", assertThrows.getMessage());

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(null);
		ForgotPasswordException assertThrows2 = Assertions.assertThrows(ForgotPasswordException.class,
				() -> userDetailsServiceImpl.findByUserName("lvsystem@capeindia.net"));
		assertEquals("Given Email-Id Is Not Available", assertThrows2.getMessage());

	} 

	@Test
	public void testChangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String encodePass = encode.encode("cape");
		optionaluser.get().setPassword(encodePass);

		when(passwordEncoder.matches("cape", encodePass)).thenReturn(true);

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		User changePassword = userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword);

		ChangePasswordException assertThrows1 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape1", "cape123"));
		assertEquals("Old password is not matching with encoded password", assertThrows1.getMessage());

		optionaluser.get().setPassword(encodePass);
		ChangePasswordException assertThrows2 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape"));
		assertEquals("Old password cannot be entered as new password", assertThrows2.getMessage());

		user.setUserexist(false);
		User changePassword2 = userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword2);
	}

	@Test
	public void testUpdatedPassword() throws UpdatePasswordException {

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);

		User updatePassword = userDetailsServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123", 1234);
		assertNull(updatePassword);

		UsernameNotFoundException assertThrows = Assertions.assertThrows(UsernameNotFoundException.class,
				() -> userDetailsServiceImpl.updatePassword(null, "cape123", 1234));
		assertEquals("Username Not Valid", assertThrows.getMessage());

		user.setUserexist(false);
		  UpdatePasswordException assertThrows2 = Assertions.assertThrows(UpdatePasswordException.class,
				() -> userDetailsServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123", 1234));
		assertEquals("User Not Available", assertThrows2.getMessage());

	}

	@Test
	public void testSaveUser() throws UserException {

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.saveUser(user));
		assertEquals("Given email-Id is Already Existing", assertThrows.getMessage());
		user.setUserexist(false);
		User saveUser = userDetailsServiceImpl.saveUser(user);
		assertNull(saveUser);

	}

	@Test
	public void testRetrieveUserInformation() throws UserException {
		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		User retrieveUserInformation = userDetailsServiceImpl.retrieveUserInformation(user.getEmail());
		assertEquals("lvsystem@capeindia.net", retrieveUserInformation.getEmail());

		user.setUserexist(false);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.retrieveUserInformation(user.getEmail()));
		assertEquals("User Not Available", assertThrows.getMessage());

	}

	@Test
	public void testUpdateUserProfile() {
		User updateUserProfile = userDetailsServiceImpl.updateUserProfile(user);
		assertNull(updateUserProfile);
	}

}
