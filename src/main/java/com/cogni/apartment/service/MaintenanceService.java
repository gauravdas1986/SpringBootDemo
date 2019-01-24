package com.cogni.apartment.service;

import java.util.List;
import java.util.Map;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.RuleBO;

public interface MaintenanceService {
	 public List<MaintenanceDTO> getMaintenanceDtlsList(List<ApartmentUnitBO> aptUnitList, Map<String,RuleBO> ruleMap);
}
