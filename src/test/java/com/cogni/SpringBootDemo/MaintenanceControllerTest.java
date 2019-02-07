package com.cogni.SpringBootDemo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cogni.apartment.SpringBootDemoApplication;
import com.cogni.apartment.controller.MaintenanceController;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.MaintenanceKey;
import com.cogni.apartment.repository.EmpRepository;
import com.cogni.apartment.repository.MaintenanceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
// @SpringBootTest(classes = MaintenanceController.class)
@WebMvcTest(value = MaintenanceController.class)
@ContextConfiguration(classes = { SpringBootDemoApplication.class })

public class MaintenanceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmpRepository empRepository;

	@MockBean
	MaintenanceRepository mtncRepo;

	@MockBean
	JobLauncher jobLauncher;

	@MockBean
	@Qualifier("apartmentJob")
	Job apartmentManagementJob;

	@MockBean
	RabbitTemplate template;

	@Autowired
	WebApplicationContext webApplicationContext;

	@org.junit.Before
	public void setUp() {
		Mockito.when(mtncRepo.findAll()).thenReturn(getAllMaintenanceDTOs());

	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void getAllMaintenanceData() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aptservice/get_maintenance_data")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(jsonPath("$[0].maintenance_amount", is(1200)));

		String expected = "[{\"key\":{\"apartment_id\":1001,\"unit_id\":101,\"for_month\":\"JAN-19\"},\"maintenance_amount\":1200,\"due_amount\":0},"
				+ "{\"key\":{\"apartment_id\":1001,\"unit_id\":102,\"for_month\":\"JAN-19\"},\"maintenance_amount\":1300,\"due_amount\":0},"
				+ "{\"key\":{\"apartment_id\":1001,\"unit_id\":103,\"for_month\":\"JAN-19\"},\"maintenance_amount\":1500,\"due_amount\":0},"
				+ "{\"key\":{\"apartment_id\":1002,\"unit_id\":101,\"for_month\":\"JAN-19\"},\"maintenance_amount\":2200,\"due_amount\":0},"
				+ "{\"key\":{\"apartment_id\":1002,\"unit_id\":102,\"for_month\":\"JAN-19\"},\"maintenance_amount\":1700,\"due_amount\":0}]";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples
		// and 10K Students","steps":["Learn Maven","Import Project","First
		// Example","Second Example"]}

		JSONAssert.assertEquals(expected, mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString(), false);
	}

	/*
	 * @Test public void getEmployeeByIdAPI() throws Exception { mvc.perform(
	 * MockMvcRequestBuilders .get("/employees/{id}", 1)
	 * .accept(MediaType.APPLICATION_JSON)) .andDo(print())
	 * .andExpect(status().isOk())
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1)); }
	 */

	public List<MaintenanceDTO> getAllMaintenanceDTOs() {

		List<MaintenanceDTO> dtos = new ArrayList<MaintenanceDTO>();
		MaintenanceKey key1 = new MaintenanceKey(1001, 101, "JAN-19");
		MaintenanceDTO dto1 = new MaintenanceDTO(key1, 1200, 0);
		MaintenanceKey key2 = new MaintenanceKey(1001, 102, "JAN-19");
		MaintenanceDTO dto2 = new MaintenanceDTO(key2, 1300, 0);
		MaintenanceKey key3 = new MaintenanceKey(1001, 103, "JAN-19");
		MaintenanceDTO dto3 = new MaintenanceDTO(key3, 1500, 0);
		MaintenanceKey key4 = new MaintenanceKey(1002, 101, "JAN-19");
		MaintenanceDTO dto4 = new MaintenanceDTO(key4, 2200, 0);
		MaintenanceKey key5 = new MaintenanceKey(1002, 102, "JAN-19");
		MaintenanceDTO dto5 = new MaintenanceDTO(key5, 1700, 0);
		dtos.add(dto1);
		dtos.add(dto2);
		dtos.add(dto3);
		dtos.add(dto4);
		dtos.add(dto5);

		return dtos;

	}
}
