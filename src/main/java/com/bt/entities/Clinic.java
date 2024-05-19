package com.bt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonIgnore
	private long clinicId;
	
	@Size(max = 50, message = "Clinic Name size exceeded")
	private String clinicName;
	
	@NotEmpty(message = "Location of the clinic should be given")
	private String clinicLocation;
}
