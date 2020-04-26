package com.jonatancm.passbox.repository;

import com.jonatancm.passbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndPassword(String username, String password);
}
