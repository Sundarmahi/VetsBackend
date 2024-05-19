// package com.bt;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import com.bt.controller.VetController;
// import com.bt.entities.Vet;
// import com.bt.service.AppointmentService;
// import com.bt.service.PetService;
// import com.bt.service.VetService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @WebMvcTest(VetController.class)
// public class Test_controller {
// 	@MockBean
// 	private VetService impl;
// 	@MockBean
// 	private PetService petService;
// 	@MockBean
// 	private AppointmentService appointmentServiceImpl;
// 	@Autowired
// 	MockMvc mockMvc;
	
// 	@Test
// 	public void getallvets() throws Exception {
		 
//         Vet vet = new Vet();
        
//         vet.setVetId(11);
// 	    vet.setEmail("sandesh@gmail.com");
// 	    vet.setMobileNo("8660181787");
// 	    vet.setRating(10);
// 	    vet.setVetName("sandesh");
// 	    vet.setSpeciality("oncologist");
// 	    vet.setDailySchedule(null);
	  
// 	    Vet vet1=new Vet();
// 	    vet1.setVetId(110);
// 	    vet1.setEmail("rohan@gmail.com");
// 	    vet1.setMobileNo("8660181787");
// 	    vet1.setRating(1);
// 	    vet1.setVetName("Rohan");
// 	    vet1.setSpeciality("cardiologist");
// 	    vet.setDailySchedule(null);
// 	    List<Vet> vetlist = Arrays.asList(vet,vet1);
// 	    when(impl.getVets()).thenReturn(vetlist);
// 	    mockMvc.perform(get("/api/vet/get").contentType(MediaType.APPLICATION_JSON))
// 	    .andExpect(status().isOk())
// 	    .andExpect(jsonPath("$[0].rating",org.hamcrest.Matchers.equalTo(10.0)));
	    
// 	}
// 	@Test
//     public void testGetHighlyRatedVets() throws Exception {
//         List<Vet> highlyRatedVets = Arrays.asList(new Vet(1, "Vet1", "8660181787", "sankeerth@gmail.com", "dentistry", 5, null, null));
//         when(impl.getHighlyRatedVets(4)).thenReturn(highlyRatedVets);
//         mockMvc.perform(get("/api/vet/getHighlyRated")
//         		.contentType(MediaType.APPLICATION_JSON))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.length()", org.hamcrest.Matchers.equalTo(1)));

// 	}
// 	@Test
//     public void testPostVet() throws Exception {
//         Vet vet = new Vet(1, "Vet1", "3456789", "dfghj@gmail.com", "urinology", 6, null, null);
//         when(impl.postVet(vet)).thenReturn(vet);
//         mockMvc.perform(post("/api/vet/post").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vet)))
//         .andExpect(status().isOk());
// 	}
	
// 	@Test
// 	public void testPutVet() throws Exception {
//         Vet vet = new Vet(1, "shivam", "2345678","dsfg@gmail.com", "tblogy", 8, null, null);
//         when(impl.putVet(vet)).thenReturn(vet);
//         mockMvc.perform(put("/api/vet/put").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vet))).andExpect(status().isOk());
        
        
// 	}
// 	@Test
// 	public void testDeleteVet() throws Exception {
// 		 Vet vet = new Vet(1, "shivam", "2345678","dsfg@gmail.com", "tblogy", 8, null, null);
// 		 List<Vet> vetlist = Arrays.asList(vet);
// 		 when(impl.deleteVetById(1)).thenReturn("deleted successfully");
// 		 mockMvc.perform(delete("/api/vet/delete/1")
// 				 .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vetlist))).andExpect(jsonPath("$.length()", org.hamcrest.Matchers.equalTo(null))).andExpect(status().isOk());
		 
	        
// 	}
// 	@Test
//     public void testGetVetById() throws Exception {
//         Vet vet1 = new Vet(1, "nithin", "345632", "dfghj@gmail.com", "oncology", 9,null, null);
//         Vet vet = new Vet(2, "shivam", "2345678","dsfg@gmail.com", "tblogy", 8, null, null);
// 		 List<Vet> vetlist = Arrays.asList(vet,vet1);
// 		 when(impl.getVetById(2)).thenReturn(vet);
// 		 mockMvc.perform(get("/api/vet/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.mobileNo",org.hamcrest.Matchers.equalTo( "2345678")));
        
// 	}
// //	@Test
// //	public void testGetappointmentByvetId() throws Exception {
// //		Vet vet1 = new Vet(1, "nithin", "345632", "dfghj@gmail.com", "oncology", 9,null, null);
// //		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
// //		Date date =new Date(2002-02-19);
// //		Appointment appointment=new Appointment(10, date, "closed", 5, 10, 3);
// //		
// //	}
	
	
	

	

// }
