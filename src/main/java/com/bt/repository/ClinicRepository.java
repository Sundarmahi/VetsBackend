package com.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bt.entities.Clinic;

@Repository
public interface ClinicRepository  extends JpaRepository<Clinic, Long>{

}
