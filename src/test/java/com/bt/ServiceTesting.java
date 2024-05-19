package com.bt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bt.entities.Clinic;
import com.bt.entities.Vet;
import com.bt.exceptions.NoVetsFoundException;
import com.bt.repository.ClinicRepository;
import com.bt.repository.VetRepository;
import com.bt.service.impl.VetServiceImpl;
 
@ExtendWith(MockitoExtension.class)
@WebMvcTest(VetServiceImpl.class)
public class ServiceTesting {
 
    @MockBean
    private VetRepository vetRepository;
 
    @MockBean
    private ClinicRepository clinicRepo;
 
    @Autowired
    private VetServiceImpl vetService;
 
    @Test
    public void testGetAllVets() {
        List<Vet> vets = new ArrayList<>();
        Clinic clinic = new Clinic();
        Clinic clinic1 = new Clinic();
        vets.add(new Vet(1L, "John Doe", "123456789", "john@example.com", "Dentistry", 5, new ArrayList<>(), clinic));
        vets.add(new Vet(2L, "Jane Doe", "987654321", "jane@example.com", "Surgery", 4, new ArrayList<>(), clinic1));
 
        when(vetRepository.findAll()).thenReturn(vets);
 
        List<Vet> result = vetService.getVets();
 
        assertEquals(2, result.size());
        verify(vetRepository).findAll();
    }
 
    @Test
    public void testGetVetById() {
        Long id = 1L;
        Vet vet = new Vet(1L, "John Doe", "123456789", "john@example.com", "Dentistry", 5, new ArrayList<>(),
                new Clinic());
 
        when(vetRepository.findById(id)).thenReturn(Optional.of(vet));
 
        Vet result = vetService.getVetById(id);
 
        assertEquals(vet, result);
        verify(vetRepository).findById(id);
    }
 
  
 
    @Test
    public void testDeleteVetById() {
        long id = 1L;
        Vet vet = new Vet();
        vet.setVetId(id);
        vet.setVetName("John Doe");
        vet.setMobileNo("1234567890");
        vet.setEmail("johndoe@example.com");
        vet.setSpeciality("General");
        vet.setRating(5);
        Clinic clinic = new Clinic();
        clinic.setClinicId(1L);
        clinic.setClinicName("ABC Clinic");
        clinic.setClinicLocation("MUMBAI");
        vet.setMyClinic(clinic);
 
        when(vetRepository.existsById(id)).thenReturn(true);
        when(vetRepository.findById(id)).thenReturn(Optional.of(vet));
 
        String result = vetService.deleteVetById(id);
 
        assertEquals("Removed", result);
    }
 
    @Test
    void testPostVet() {
        Vet vetToSave = new Vet();
        vetToSave.setVetId(1L);
        vetToSave.setVetName("John Doe");
        vetToSave.setMobileNo("1234567890");
        vetToSave.setEmail("john.doe@example.com");
        vetToSave.setSpeciality("General");
        vetToSave.setRating(5);
 
        when(vetRepository.existsById(1L)).thenReturn(false);
        when(vetRepository.save(vetToSave)).thenReturn(vetToSave);
 
        Vet savedVet = vetService.postVet(vetToSave);
 
        assertEquals(1L, savedVet.getVetId());
    }
 
 
@Test
public void testDeleteVetById_NotFound() {
    long id = 1L;
 
    when(vetRepository.existsById(id)).thenReturn(false);
 
    
}
 
@Test
public void testPostVet_AlreadyExists() {
    Vet vetToSave = new Vet();
    vetToSave.setVetId(1L);
    vetToSave.setVetName("John Doe");
    vetToSave.setMobileNo("1234567890");
    vetToSave.setEmail("john.doe@example.com");
    vetToSave.setSpeciality("General");
    vetToSave.setRating(5);
 
    when(vetRepository.existsById(1L)).thenReturn(true);
}
@Test
void testDeleteVetById_NoVetsFoundException() {
    // Arrange
    long id = 1L;
    when(vetRepository.existsById(id)).thenReturn(false);
    boolean exceptionThrown = false;
 
    try {
        vetService.deleteVetById(id);
    } catch (NoVetsFoundException e) {
        exceptionThrown = true;
    }
 
    
    verify(vetRepository).existsById(id);
}}