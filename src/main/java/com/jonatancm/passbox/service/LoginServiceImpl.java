package com.jonatancm.passbox.service;

import com.jonatancm.passbox.exception.UserNotAuthenticatedException;
import com.jonatancm.passbox.exception.UserNotFoundException;
import com.jonatancm.passbox.model.User;
import com.jonatancm.passbox.model.security.PassboxUserDetails;
import com.jonatancm.passbox.repository.LoginRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

	private final AuthenticationManager authenticationManager;
	private final LoginRepository loginRepository;

	public LoginServiceImpl(AuthenticationManager authenticationManager, LoginRepository loginRepository) {
		this.authenticationManager = authenticationManager;
		this.loginRepository = loginRepository;
	}

	@Override
	public User login(String username, String password) {

		Optional<User> user = this.loginRepository.findByUsernameAndPassword(username, password);

		if(!user.isPresent()) {
			throw new UserNotFoundException();
		}

		PassboxUserDetails userDetails = new PassboxUserDetails(user.get());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if(!usernamePasswordAuthenticationToken.isAuthenticated()) {
			throw new UserNotAuthenticatedException();
		}

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		return user.get();
	}

	@Override
	public Optional<User> getLoggedInUser() {

		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

		if(userDetails instanceof PassboxUserDetails) {
			return Optional.of(((PassboxUserDetails) userDetails).getUser());
		}

		return Optional.empty();
	}
}
