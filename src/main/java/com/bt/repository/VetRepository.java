package com.bt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bt.entities.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long>{
//	@Query("SELECT a FROM Appointment a JOIN FETCH a.pet p JOIN FETCH a.vet v WHERE p.id = :petId and v.id = :vetId")
//	List<Object[]> findAppointmentsByvetId(@Param("vetId") Long vetId);

//	@Query("Select v from Vet")
//	List<Vet> findAllRecentlyConsultedPets();
	
	Vet findByEmail(String email);
}
