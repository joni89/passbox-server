package com.jonatancm.passbox.service;

import com.jonatancm.passbox.model.CredentialsRecord;

import java.util.Optional;

public interface CredentialService {

	Iterable<CredentialsRecord> findAll();

	Optional<CredentialsRecord> findById(Long id);

	CredentialsRecord insert(CredentialsRecord record);

	CredentialsRecord update(CredentialsRecord record);

	void delete(Long id);

}
