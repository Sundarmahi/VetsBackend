package com.bt.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSlots {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@NotNull(message = "Date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@FutureOrPresent(message = "Cannot set schedules for previous dates.")
	private LocalDate date;

    @ElementCollection
    @Column(name = "time")
    private List<String> time;
    
    
    public ScheduleSlots(LocalDate date, List<String> times) {
    	this.date = date;
    	this.time = times;
    }
}