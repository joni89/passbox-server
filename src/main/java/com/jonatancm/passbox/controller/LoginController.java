package com.jonatancm.passbox.controller;

import com.jonatancm.passbox.exception.UserNotAuthenticatedException;
import com.jonatancm.passbox.exception.UserNotFoundException;
import com.jonatancm.passbox.model.User;
import com.jonatancm.passbox.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping
	public User login(@RequestParam String username, @RequestParam String password) {

		return this.loginService.login(username, password);
	}

	@ExceptionHandler({ UserNotFoundException.class, UserNotAuthenticatedException.class })
	public ResponseEntity<?> handleLoginExceptions(Exception exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
