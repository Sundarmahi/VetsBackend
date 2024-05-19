package com.bt.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@JsonIgnore
	private long vetId;
	
	@NotEmpty(message = "Name cannot be Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name should not contain numbers")
	private String vetName;
	
	@Pattern(regexp = "^[0-9]*$", message = "Mobile number must contain only digits")
    @Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
	private String mobileNo;
	
	@Email(message = "Invalid Email Id")
	private String email;
	
	@NotEmpty(message = "Mention your speciality")
	private String speciality;
	
	@NotNull(message = "Rating attribute cannot be null")
    @Min(value = 0, message = "Rating must be greater than or equal to 0")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
	private int rating;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vet_id")
    private List<ScheduleSlots> dailySchedule = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "myClinicId")
	@JsonIgnore
	private Clinic myClinic;

	public double getRating() {
		return rating;
	}

}
