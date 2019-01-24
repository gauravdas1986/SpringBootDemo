package com.cogni.apartment.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.MaintenanceKey;
import com.cogni.apartment.model.RuleBO;
import com.cogni.apartment.service.MaintenanceService;
import com.cogni.apartment.util.ApartmentDataHelper;

public class MaintenanceServiceImpl implements MaintenanceService{
	
	public List<MaintenanceDTO> mtncDtlsList = new ArrayList<MaintenanceDTO>();

	/*Method to build the final list to be persisted*/
	@Override
	public List<MaintenanceDTO> getMaintenanceDtlsList(List<ApartmentUnitBO> aptUnitList, Map<String,RuleBO> ruleMap) {
		ApartmentDataHelper aptHelper = new ApartmentDataHelper();
		aptUnitList.forEach(aptUnit -> {
			RuleBO ruleBO = ruleMap.get(aptUnit.getApartment_id()+"_"+aptUnit.getUnit_type_id());
			System.out.println(aptUnit.getApartment_id()+"____"+aptUnit.getUnit_id());
			MaintenanceKey key = new MaintenanceKey();
			key.setApartment_id(aptUnit.getApartment_id());
			key.setUnit_id(aptUnit.getUnit_id());
			key.setFor_month("JAN-19");
			MaintenanceDTO mtncDTO = new MaintenanceDTO();
			mtncDTO.setKey(key);
			mtncDTO.setMaintenance_amount(aptHelper.calculateMtncAmt(aptUnit,ruleBO));
			mtncDTO.setDue_amount(0);
			mtncDtlsList.add(mtncDTO);
		}); 
		return mtncDtlsList;
	} 

}
