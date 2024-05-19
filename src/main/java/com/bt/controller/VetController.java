package com.bt.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import com.bt.entities.Appointment;
import com.bt.entities.Clinic;
//import com.bt.entities.Pet;
import com.bt.entities.ScheduleSlots;
import com.bt.entities.Vet;
import com.bt.exceptions.InValidTimeSlotException;
import com.bt.exceptions.NoAppointmentFoundException;
import com.bt.exceptions.NoPetsFoundException;
import com.bt.exceptions.NoVetsFoundException;
import com.bt.exceptions.VetAlreadyExistsException;
//import com.bt.service.AppointmentService;
//import com.bt.service.PetService;
import com.bt.service.VetService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/vet")
public class VetController {

	@Autowired
	VetService vetService;

	org.slf4j.Logger logger = LoggerFactory.getLogger(VetController.class);

	@GetMapping("/get")
	@Operation(summary = "To get all the vets")
	public ResponseEntity<?> getAllVets() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<Vet>>(vetService.getVets(), HttpStatus.OK);
			logger.info("Displayed All Vets");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("No Vets found, Exception Handled");
		}
		return response;
	}

	@PostMapping("/post")
	@Operation(summary = "Add New Vet")
	public ResponseEntity<?> postVet(@RequestBody @Valid Vet vet) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Vet>(vetService.postVet(vet), HttpStatus.OK);
			logger.info("New Vets Added");
		} catch (VetAlreadyExistsException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("Vet already exist, Exception Handled");
		}
		return response;
	}

	@PutMapping("/put/{id}")
	@Operation(summary = "Edit Vet Details")
	public ResponseEntity<?> putVet(@PathVariable long id, @RequestBody @Valid Vet vet) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Vet>(vetService.putVet(id, vet), HttpStatus.OK);
			logger.info("Vet details edited");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("No Vets found, Exception Handled");
		}
		return response;
	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Vet")
	public ResponseEntity<?> deleteVet(@PathVariable(value = "id") long id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<String>(vetService.deleteVetById(id), HttpStatus.OK);
			logger.info("Vets Details deleted");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("No Vets found, Exception Handled");
		}
		return response;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Vet by ID")
	public ResponseEntity<?> getVetById(@PathVariable(value = "id") long id) {
		ResponseEntity<?> response;
		try {
			Vet vet = vetService.getVetById(id);
			response = new ResponseEntity<Vet>(vet, HttpStatus.OK);
			logger.info("got the appointment details of the reqiuired doctors ");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
			logger.error("No Vets Found,Exception Handled");
		}
		return response;
	}

	@GetMapping("/getVetId/{email}")
	@Operation(summary = "Get Vet by ID")
	public ResponseEntity<?> getVetIdbyEmail(@PathVariable(value = "email") String email) {
		ResponseEntity<?> response;
		try {
			long vetId = vetService.getVetIdbyEmail(email);
			response = new ResponseEntity<Long>(vetId, HttpStatus.OK);
			logger.info("Successfully Retrived Id.");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
			logger.error("No Vets Found,Exception Handled");
		}
		return response;
	}

	@GetMapping("/getHighlyRated")
	@Operation(summary = "To get all highly rated Vets")
	public ResponseEntity<?> getHighlyRatedVets() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<Vet>>(vetService.getHighlyRatedVets(4), HttpStatus.OK);
			logger.info("Got highly rated vets");

		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("No Vets found, Exception Handled");
		}
		return response;
	}

	@PostMapping("/addClinic/{vetId}")
	@Operation(summary = "Added Clinic to the Vet")
	public ResponseEntity<?> addClinicToVet(@PathVariable Long vetId, @RequestBody @Valid Clinic clinic) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Vet>(vetService.addClinic(vetId, clinic), HttpStatus.OK);
			logger.info("Clinic Details Added for Vet");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("Failed adding clinic details, Vet Not Found exception handled");
		}
		return response;
	}

	@GetMapping("/clinic/{vetId}")
	@Operation(summary = "Get Clinic Details for Vet by his Id")
	public ResponseEntity<?> getClinicByVetId(@PathVariable Long vetId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Clinic>(vetService.getClinicByVetId(vetId), HttpStatus.OK);
			logger.info("Displayed Clinic details for the Vet");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("Unable to display Clinic details ,InValid Vet Id exception handled");
		}
		return response;
	}

	@PostMapping("/setSchedule/{id}/{date}/{startTime}/{endTime}")
	public ResponseEntity<?> setSchedule(@PathVariable long id,
			@PathVariable @Min(value = -1, message = "Start Time should be greater than 0") @Max(value = 23, message = "Time should be between 0 to 24") int startTime,
			@PathVariable @Min(value = -1, message = "End Time should be greater than 0") @Max(value = 23, message = "Time should be between 0 to 24") int endTime,
			@PathVariable String date) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Vet>(vetService.setSchedule(id, LocalDate.parse(date), startTime, endTime),
					HttpStatus.OK);
			logger.info("Slots scheduled for the vet");
		} catch (NoVetsFoundException | InValidTimeSlotException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("Exception Handled while scheduling the slots");
		}
		return response;
	}

	@GetMapping("/AvailableSlots/{id}")
	public ResponseEntity<?> getAvailableSlots(@PathVariable long id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<ScheduleSlots>>(vetService.getAvailableSlots(id), HttpStatus.OK);
			logger.info("Available Slots for each vet is displayed");
		} catch (NoVetsFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			logger.error("Vet not found exception handled");
		}
		return response;
	}

	@GetMapping("/scheduleSlots/{vetId}/date/{date}")
	@Operation(summary = "To get all slots time for that vet on that date")
	public ResponseEntity<List<String>> getTimeSlotsForVet(@PathVariable Long vetId, @RequestParam String date) {
		List<String> timeSlots = vetService.getTimeSlotsByVetIdandDate(vetId, LocalDate.parse(date));
		return ResponseEntity.ok().body(timeSlots);
	}

	@Transactional
	@DeleteMapping("/delete/timeslot/{vetId}/date/{date}/time/{time}")
	@Operation(summary = "To delete the time slot of that vet on that date")
	public ResponseEntity<String> deleteTimeSlot(@RequestParam Long vetId, @RequestParam String date,
			@RequestParam String time) {
		String message = vetService.deleteTimeSlot(vetId, date, time);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		StringBuilder errors = new StringBuilder("Validation errors: ");
		ex.getBindingResult().getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));

		return ResponseEntity.badRequest().body(errors.toString());
	}

//	@GetMapping("/getRecentlyConsulted")
//	public ResponseEntity<?> getRecentlyConsulted() {
//		ResponseEntity<?> response = null;
//		response = new ResponseEntity<>(vetService.getRecentlyConsultedPets(), HttpStatus.OK);
//		logger.info("got details of recently consulted pets");
//		return response;
//	}
}
