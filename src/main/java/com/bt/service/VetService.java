package com.bt.service;

import java.time.LocalDate;
import java.util.List;

import com.bt.entities.Clinic;
//import com.bt.entities.Pet;
import com.bt.entities.ScheduleSlots;
import com.bt.entities.Vet;

public interface VetService {

	public List<Vet> getVets();

	public Vet postVet(Vet vet);

	public String deleteVetById(long id);

	public Vet getVetById(long id);

	public List<Vet> getHighlyRatedVets(double minimumRating);

	public Vet addClinic(long vetId, Clinic clinic);

	public Clinic getClinicByVetId(long vetId);

	public Vet setSchedule(long vetId,LocalDate date, int startTime, int endTime);

	public List<ScheduleSlots> getAvailableSlots(long vetId);
	
	public long getVetIdbyEmail(String email);

//	public List<Pet> getRecentlyConsultedPets();

	public Vet putVet(long id, Vet vet);

	public List<String> getTimeSlotsByVetIdandDate(long vet_Id, LocalDate date);

	public String deleteTimeSlot(Long vetId, String date, String time);

}
