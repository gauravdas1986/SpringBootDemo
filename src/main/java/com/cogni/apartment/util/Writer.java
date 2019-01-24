package com.cogni.apartment.util;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.repository.MaintenanceRepository;

@Component
public class Writer implements ItemWriter<MaintenanceDTO>{
	
	@Autowired
	private MaintenanceRepository mtncRepo;

	@Override
	@Transactional
	public void write(List<? extends MaintenanceDTO> entities) throws Exception {
		System.out.println("Batch Writer called with entities "+entities.size());
		mtncRepo.saveAll(entities);
	}
	
}