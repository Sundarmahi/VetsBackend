package com.bt.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.entities.Clinic;
//import com.bt.entities.Pet;
import com.bt.entities.ScheduleSlots;
import com.bt.entities.Vet;
import com.bt.exceptions.InValidTimeSlotException;
import com.bt.exceptions.NoVetsFoundException;
import com.bt.exceptions.VetAlreadyExistsException;
import com.bt.repository.ClinicRepository;
import com.bt.repository.ScheduleSlotsRepository;
import com.bt.repository.VetRepository;
import com.bt.service.*;

@Service
public class VetServiceImpl implements VetService {

	@Autowired
	VetRepository vetRepository;

	@Autowired
	ClinicRepository clinicRepository;

	@Autowired
	ScheduleSlotsRepository scheduleRepository;

	@Override
	public List<Vet> getVets() {
		List<Vet> vets = vetRepository.findAll();
		if (vets.isEmpty()) {
			throw new NoVetsFoundException("No Vets available. Please Hire.");
		} else {
			return vets;
		}
	}

	@Override
	public Vet postVet(Vet vet) {
		Vet newVet = new Vet();
		if (vetRepository.existsById(vet.getVetId())) {
			throw new VetAlreadyExistsException("Vet Id is already taken. Try Providing different Id.");
		} else {
			newVet = vetRepository.save(vet);
			return newVet;
		}
	}

	@Override
	public Vet putVet(long id, Vet vet) {
		Vet updatedVet = null;
		if (vetRepository.existsById(id)) {
			vet.setVetId(id);
			updatedVet = vetRepository.save(vet);
			return updatedVet;
		} else {
			throw new NoVetsFoundException("No Id found to edit the Vet details. Provide Correct Id");
		}
	}

	@Override
	public String deleteVetById(long id) {
		if (vetRepository.existsById(id)) {
			vetRepository.deleteById(id);
			return "Removed";
		} else {
			throw new NoVetsFoundException("Oops!! You have entered inValid Id.");
		}
	}

	@Override
	public Vet getVetById(long vetId) {
		Vet vet = vetRepository.findById(vetId)
				.orElseThrow(() -> new NoVetsFoundException("Vet not found with id: " + vetId));
		return vet;
	}
	
	@Override
	public long getVetIdbyEmail(String email) {
		Vet vet = vetRepository.findByEmail(email);
		
		return vet != null ? vet.getVetId() : null;
	}

	@Override
	public List<Vet> getHighlyRatedVets(double minimumRating) {
		List<Vet> allVets = vetRepository.findAll();

		if (allVets.isEmpty()) {
			throw new NoVetsFoundException("No Vets available. Please Hire.");
		} else {
			List<Vet> highlyRatedVets = allVets.stream().filter(vet -> vet.getRating() >= minimumRating)
					.collect(Collectors.toList());

			if (highlyRatedVets.isEmpty()) {
				throw new NoVetsFoundException("No highly rated vets available.");
			} else {
				return highlyRatedVets;
			}
		}
	}

	@Override
	public Vet addClinic(long vetId, Clinic clinic) {
		Vet vet = vetRepository.findById(vetId)
				.orElseThrow(() -> new NoVetsFoundException("Vet not found with id: " + vetId));
		Clinic newClinic = new Clinic();
		newClinic = clinicRepository.save(clinic);

		vet.setMyClinic(newClinic);

		return vetRepository.save(vet);
	}

	@Override
	public Clinic getClinicByVetId(long vetId) {
		Vet vet = vetRepository.findById(vetId)
				.orElseThrow(() -> new NoVetsFoundException("Vet not found with id: " + vetId));

		return vet.getMyClinic();
	}

	@Override
	public Vet setSchedule(long vetId, LocalDate scheduleDate, int startTime, int endTime) {
	    Vet vet = vetRepository.findById(vetId)
	            .orElseThrow(() -> new NoVetsFoundException("Vet Not Found with the provided Id"));

	    if (scheduleDate.isBefore(LocalDate.now())) {
	    	throw new InValidTimeSlotException("Cannot set schedules for previous dates.");
	    }
	    
	    ScheduleSlots existingSchedule = vet.getDailySchedule().stream()
	            .filter(entry -> entry.getDate().equals(scheduleDate))
	            .findFirst()
	            .orElse(null);

	    if (existingSchedule != null) {
	        // Update existing time slots
	        List<String> updatedTimeSlots = createTimeSlots(startTime, endTime);
	        existingSchedule.setTime(updatedTimeSlots);
	    } else {
	        // Create new time slots
	        List<String> timeSlots = createTimeSlots(startTime, endTime);
	        ScheduleSlots newSchedule = new ScheduleSlots(scheduleDate, timeSlots);
	        vet.getDailySchedule().add(newSchedule);
	    }

	    vetRepository.save(vet);

	    return vet;
	}

	@Override
	public List<String> getTimeSlotsByVetIdandDate(long vet_Id, LocalDate date) {
		List<String> timeSlots = scheduleRepository.findTimeSlotsForVetandDate(vet_Id, date);

		return timeSlots;
	}

	@Transactional
	@Override
	public String deleteTimeSlot(Long vetId, String date, String time) {
		 try {
	            Long slotId = scheduleRepository.findIdByDateAndVetId(date, vetId);
	            if (slotId != null) {
	                int deleteCount = scheduleRepository.deleteBySlotIdAndTime(slotId, time);
	                if (deleteCount > 0) {
	                    return "Time slot deleted successfully";
	                } else {
	                    return "No matching time slots found for the provided time";
	                }
	            } else {
	                return "No matching time slots found for the provided date and vet ID";
	            }
	        } catch (Exception e) {
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	            return "An error occurred while deleting the time slot";
	        }
	    }

	@Override
	public List<ScheduleSlots> getAvailableSlots(long vetId) {
		Vet vet = vetRepository.findById(vetId).orElseThrow(() -> new NoVetsFoundException("Invalid Vet Id"));

		List<ScheduleSlots> availableSlots = vet.getDailySchedule();

		return availableSlots;
	}

	private List<String> createTimeSlots(int startTime, int endTime) {
		List<String> timeSlots = new ArrayList<>();

		if (startTime >= endTime) {
			throw new InValidTimeSlotException(
					"You have provide with wrong end time. End time should not be less than or equal to startTime");
		}
		LocalTime startTime1 = LocalTime.of(startTime, 0);
		LocalTime endTime1 = LocalTime.of(endTime, 0);

		while (startTime1.isBefore(endTime1)) {
			timeSlots.add(startTime1.format(DateTimeFormatter.ofPattern("hh:mm a")));
			startTime1 = startTime1.plusHours(1);
		}

		return timeSlots;
	}

//	@Override
//	public List<Pet> getRecentlyConsultedPets() {
//		return null;
//	}

}
