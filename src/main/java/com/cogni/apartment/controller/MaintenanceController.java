package com.cogni.apartment.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.Emp;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.RuleBO;
import com.cogni.apartment.repository.EmpRepository;
import com.cogni.apartment.repository.MaintenanceRepository;
import com.cogni.apartment.service.MaintenanceService;
import com.cogni.apartment.serviceImpl.MaintenanceServiceImpl;
import com.cogni.apartment.util.ApartmentDataHelper;
import com.cogni.apartment.util.Reader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/aptservice")
@Api(value = "Api Controller Services", description = "Apartment Service ")
public class MaintenanceController {

	@Autowired
	EmpRepository empRepository;

	@Autowired
	MaintenanceRepository mtncRepo;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("apartmentJob")
	Job apartmentManagementJob;

	@GetMapping("/allemp")
	public List<Emp> getAllNotes() {
		return empRepository.findAll();
	}

	@GetMapping("/hello")
	public String hello() {
		return "HELLO BNNBn";
	}

	@GetMapping("/get_maintenance_data")
	public List<MaintenanceDTO> getAllMaintenanceData() {
		return mtncRepo.findAll();
	}

	@PostMapping("/save_maintenance_data")
	public MaintenanceDTO saveMaintenanceData(@RequestBody MaintenanceDTO mtncDto) {
		System.out.println("MaintenanceDTO " + mtncDto.getKey().getApartment_id());
		return mtncRepo.save(mtncDto);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Done"),
			@ApiResponse(code = 400, message = "Malformed Syntax Exception"),
			@ApiResponse(code = 101, message = "JDBC Exception Occurs"),
			@ApiResponse(code = 108, message = "Entity Not Found"),
			@ApiResponse(code = 103, message = "Query Times Out"),
			@ApiResponse(code = 104, message = "Unknown Exception.Please Try Again") })
	@GetMapping("/run-batch-job")
	public String handle(/*@RequestBody List<MaintenanceDTO> mtncDto*/) throws Exception {

		/* temporary Code to add data to be read by reader later */
		List<ApartmentUnitBO> aptUnitList =  new ApartmentDataHelper().getApartmentDetailsList(); 
		Map<String,RuleBO> ruleMap =  new ApartmentDataHelper().getRuleMap();
		MaintenanceService mtncService = new MaintenanceServiceImpl();
		new Reader().mtncDtos = mtncService.getMaintenanceDtlsList(aptUnitList,ruleMap);
		/* End */

		JobParameters jobParameters = new JobParametersBuilder().addString("source", "apartment management")
				.addDate("date", new Date()).toJobParameters();
		jobLauncher.run(apartmentManagementJob, jobParameters);

		return "Batch job has been invoked";
	}
}
