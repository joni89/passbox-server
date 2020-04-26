package com.jonatancm.passbox.service;

import com.jonatancm.passbox.model.User;

import java.util.Optional;

public interface LoginService {

	User login(String username, String password);

	Optional<User> getLoggedInUser();
}
