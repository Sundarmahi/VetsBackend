package com.bt.service;

import com.bt.entities.Clinic;

public interface ClinicService {
	public Clinic getClinicById(long id);

	public Clinic postClinic(Clinic clinic);
	
	public Clinic putClinic(Clinic clinic);

}