package com.jonatancm.passbox.repository;

import com.jonatancm.passbox.model.CredentialsRecord;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<CredentialsRecord, Long> {

}
