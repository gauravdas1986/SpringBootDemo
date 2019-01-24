package com.cogni.apartment.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.MaintenanceKey;
import com.cogni.apartment.repository.MaintenanceRepository;

public class Reader implements ItemReader<MaintenanceDTO> {

	@Autowired
	MaintenanceRepository mtncRepo;

	List<MaintenanceDTO> mntnList = null;
	private ItemReader<MaintenanceDTO> delegate;

	public static List<MaintenanceDTO> mtncDtos = new ArrayList<>();

	@Override
	public MaintenanceDTO read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		 if (delegate == null) {
	            delegate = new IteratorItemReader<>(mtncDtos);
	        }
	        return delegate.read();
	}

}