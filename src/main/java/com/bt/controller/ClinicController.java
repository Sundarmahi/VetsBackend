package com.bt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bt.entities.Clinic;
import com.bt.exceptions.VetAlreadyExistsException;
import com.bt.service.ClinicService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("clinic")
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postClinic(@RequestBody @Valid Clinic clinic) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Clinic>(clinicService.postClinic(clinic), HttpStatus.OK);
		} catch (VetAlreadyExistsException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<?> getClinic(@PathVariable (value="id") long clinicId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Clinic>(clinicService.getClinicById(clinicId), HttpStatus.OK);
		} catch (VetAlreadyExistsException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> putClinic(@RequestBody @Valid Clinic clinic) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Clinic>(clinicService.postClinic(clinic), HttpStatus.OK);
		} catch (VetAlreadyExistsException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		StringBuilder errors = new StringBuilder("Validation errors: ");
		ex.getBindingResult().getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));

		return ResponseEntity.badRequest().body(errors.toString());
	}
	
}
