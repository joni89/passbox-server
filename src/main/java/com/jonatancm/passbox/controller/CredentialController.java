package com.jonatancm.passbox.controller;

import com.jonatancm.passbox.model.CredentialsRecord;
import com.jonatancm.passbox.service.CredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/credentials")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class CredentialController {

	private final CredentialService credentialService;

	public CredentialController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}

	@GetMapping
	public Iterable<CredentialsRecord> findAll() {
		return this.credentialService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CredentialsRecord> findById(@PathVariable Long id) {
		return ResponseEntity.of(this.credentialService.findById(id));
	}

	@PostMapping
	public CredentialsRecord insert(@Valid @RequestBody CredentialsRecord record, BindingResult bindingResult) {
		// if(bindingResult.hasErrors()) {
		// 	for(ObjectError error : bindingResult.getAllErrors()) {
		// 		error.get
		// 	}
		// }
		return this.credentialService.insert(record);
	}

	@PutMapping("/{id}")
	public CredentialsRecord update(@PathVariable Long id, @RequestBody CredentialsRecord record) {
		record.setId(id);
		return this.credentialService.update(record);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.credentialService.delete(id);
	}

}
