package com.jonatancm.passbox.service;

import com.jonatancm.passbox.model.CredentialsRecord;
import com.jonatancm.passbox.model.User;
import com.jonatancm.passbox.repository.CredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredentialServiceImpl implements CredentialService {

	private final CredentialRepository credentialRepository;

	public CredentialServiceImpl(CredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}

	@Override
	public Iterable<CredentialsRecord> findAll() {
		return this.credentialRepository.findAll();
	}

	@Override
	public Optional<CredentialsRecord> findById(Long id) {
		return this.credentialRepository.findById(id);
	}

	@Override
	public CredentialsRecord insert(CredentialsRecord record) {
		if(record.getId() != null) {
			throw new IllegalArgumentException("Cannot insert a record which has an ID");
		}
		setCredentialsUser(record);

		return this.credentialRepository.save(record);
	}

	private void setCredentialsUser(CredentialsRecord record) {
		// FIXME eliminar cuando tengamos un usuario con la sesión.
		User user = new User();
		user.setId(1L);
		record.setUser(user);
	}

	@Override
	public CredentialsRecord update(CredentialsRecord record) {
		if(record.getId() == null) {
			throw new IllegalArgumentException("Cannot update a record without an ID");
		}

		setCredentialsUser(record);

		return this.credentialRepository.save(record);
	}

	@Override
	public void delete(Long id) {
		this.credentialRepository.deleteById(id);
	}

}
