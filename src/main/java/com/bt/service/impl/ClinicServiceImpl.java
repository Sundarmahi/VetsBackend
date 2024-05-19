package com.bt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.entities.Clinic;
import com.bt.exceptions.NoClinicExistsException;
import com.bt.repository.ClinicRepository;
import com.bt.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	@Override
	public Clinic getClinicById(long id) {
		Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new NoClinicExistsException("InValid Id."));
		return clinic;
	}

	@Override
	public Clinic postClinic(Clinic clinic) {
		return clinicRepository.save(clinic);
	}

	@Override
	public Clinic putClinic(Clinic clinic) {
		if(clinicRepository.existsById(clinic.getClinicId())) {
			return clinicRepository.save(clinic);
		}else {
			throw new NoClinicExistsException("Please Provide Valid Id");
		}
	}

}
