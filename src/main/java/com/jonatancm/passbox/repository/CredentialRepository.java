package com.jonatancm.passbox.repository;

import com.jonatancm.passbox.model.CredentialsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<CredentialsRecord, Long> {

}
