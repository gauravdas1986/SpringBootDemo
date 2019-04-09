package com.cogni.SpringBootDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cogni.apartment.exception.DBRollBackException;
import com.cogni.apartment.model.Emp;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.repository.EmpRepository;
import com.cogni.apartment.repository.MaintenanceRepository;

@Component
public class InsertTest {

	@Autowired
	EmpRepository empRepository;

	@Autowired
	MaintenanceRepository mtncRepository;

	@Transactional(rollbackFor = DBRollBackException.class)
	public void insert(Emp emp, MaintenanceDTO mtncDTO) throws DBRollBackException {
		MaintenanceDTO mtnc = mtncRepository.save(mtncDTO);
		System.out.println("MNTC start");
		if (mtnc == null) {
			throw new DBRollBackException();
		}
		System.out.println("MNTC end");
		System.out.println("Emp start");
		
		Emp emp1 = empRepository.save(emp);
		if (emp1 == null) {
			throw new DBRollBackException();
		}
		System.out.println("Emp end");
		
	}
}
