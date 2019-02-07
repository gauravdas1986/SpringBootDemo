package com.cogni.apartment.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogni.apartment.jpa.InsertTest;
import com.cogni.apartment.model.Emp;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.MaintenanceKey;
import com.cogni.apartment.repository.EmpRepository;

@RestController
@RequestMapping("/jpa")
public class JpaController {

	@Autowired
	EmpRepository empRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	InsertTest test;

	@GetMapping("/save_emp_data")
	public void saveEmpData() {

		Emp emp = new Emp(1004l, "AAA", "JAVA", "20000");
		
		MaintenanceKey key = new MaintenanceKey();
		key.setApartment_id(1001);
		key.setUnit_id(101);
		key.setFor_month("JAN-19");
		MaintenanceDTO mtncDTO = new MaintenanceDTO();
		mtncDTO.setKey(key);
		mtncDTO.setMaintenance_amount(1111);
		mtncDTO.setDue_amount(0);
		try {
			System.out.println("JPA called with emp " + emp.getEmp_name());
			// entityManager.persist(emp);
			test.insert(emp, mtncDTO);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
