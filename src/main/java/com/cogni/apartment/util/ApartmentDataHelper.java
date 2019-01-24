package com.cogni.apartment.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.RuleBO;

public class ApartmentDataHelper {

	Map<String, RuleBO> ruleMap = new HashMap<String, RuleBO>();

	/* method to return list of all the apartment details */
	public List<ApartmentUnitBO> getApartmentDetailsList() {
		ApartmentUnitBO aptUnitBO1 = new ApartmentUnitBO(1001, 101, 1, 800);
		ApartmentUnitBO aptUnitBO2 = new ApartmentUnitBO(1001, 102, 2, 1100);
		ApartmentUnitBO aptUnitBO3 = new ApartmentUnitBO(1001, 103, 1, 1300);
		ApartmentUnitBO aptUnitBO4 = new ApartmentUnitBO(1002, 101, 1, 800);
		ApartmentUnitBO aptUnitBO5 = new ApartmentUnitBO(1002, 102, 2, 1400);
		ApartmentUnitBO aptUnitBO6 = new ApartmentUnitBO(1002, 103, 2, 700);

		List<ApartmentUnitBO> aptDtlsList = new ArrayList<ApartmentUnitBO>();
		aptDtlsList.add(aptUnitBO1);
		aptDtlsList.add(aptUnitBO2);
		aptDtlsList.add(aptUnitBO3);
		aptDtlsList.add(aptUnitBO4);
		aptDtlsList.add(aptUnitBO5);
		aptDtlsList.add(aptUnitBO6);

		return aptDtlsList;

	}

	/* method to return list of all the rules */
	public Map<String,RuleBO> getRuleMap() {
		RuleBO ruleBO1 = new RuleBO(1001, 1001, 1, 300, 3, 300, new Date());
		RuleBO ruleBO2 = new RuleBO(1002, 1001, 2, 300, 3, 300, new Date());
		RuleBO ruleBO3 = new RuleBO(1003, 1002, 1, 300, 3, 300, new Date());
		RuleBO ruleBO4 = new RuleBO(1004, 1002, 2, 300, 3, 300, new Date());

		ruleMap.put(ruleBO1.getApt_id() + "_" + ruleBO1.getUnit_type_id(), ruleBO1);
		ruleMap.put(ruleBO2.getApt_id() + "_" + ruleBO2.getUnit_type_id(), ruleBO2);
		ruleMap.put(ruleBO3.getApt_id() + "_" + ruleBO3.getUnit_type_id(), ruleBO3);
		ruleMap.put(ruleBO4.getApt_id() + "_" + ruleBO4.getUnit_type_id(), ruleBO4);
		
		return ruleMap;
	}

	/* method to calculate the maintenance amount */
	public int calculateMtncAmt(ApartmentUnitBO aptUnitBO, RuleBO ruleBO) {
		return (ruleBO.getBase_charge()+ruleBO.getOther_charges()+aptUnitBO.getSq_ft()*ruleBO.getCharge_per_sq_ft());
	}
}
