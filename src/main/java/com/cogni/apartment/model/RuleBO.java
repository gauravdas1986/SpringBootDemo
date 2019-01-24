package com.cogni.apartment.model;

import java.util.Date;
import java.util.List;

public class RuleBO {
	private int rule_id;
	private int apt_id;
	private int unit_type_id;
	private int base_charge;
	private int charge_per_sq_ft;
	private int other_charges;
	private Date effectiveDate;

	public RuleBO(int rule_id, int apt_id, int unit_type_id, int base_charge, int charge_per_sq_ft, int other_charges,
			Date effectiveDate) {
		super();
		this.rule_id = rule_id;
		this.apt_id = apt_id;
		this.unit_type_id = unit_type_id;
		this.base_charge = base_charge;
		this.charge_per_sq_ft = charge_per_sq_ft;
		this.other_charges = other_charges;
		this.effectiveDate = effectiveDate;
	}

	public int getRule_id() {
		return rule_id;
	}

	public void setRule_id(int rule_id) {
		this.rule_id = rule_id;
	}

	public int getApt_id() {
		return apt_id;
	}

	public void setApt_id(int apt_id) {
		this.apt_id = apt_id;
	}

	public int getUnit_type_id() {
		return unit_type_id;
	}

	public void setUnit_type_id(int unit_type_id) {
		this.unit_type_id = unit_type_id;
	}

	public int getBase_charge() {
		return base_charge;
	}

	public void setBase_charge(int base_charge) {
		this.base_charge = base_charge;
	}

	public int getCharge_per_sq_ft() {
		return charge_per_sq_ft;
	}

	public void setCharge_per_sq_ft(int charge_per_sq_ft) {
		this.charge_per_sq_ft = charge_per_sq_ft;
	}

	public int getOther_charges() {
		return other_charges;
	}

	public void setOther_charges(int other_charges) {
		this.other_charges = other_charges;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}
