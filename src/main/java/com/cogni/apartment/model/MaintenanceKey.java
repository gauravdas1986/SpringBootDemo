package com.cogni.apartment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*composite primary key for maintenance entity*/

@Embeddable
public class MaintenanceKey implements Serializable {
	public MaintenanceKey() {
		System.out.println("Default Constructor MaintenanceKey");
	}

	public MaintenanceKey(int apartment_id, int unit_id, String for_month) {
		super();
		this.apartment_id = apartment_id;
		this.unit_id = unit_id;
		this.for_month = for_month;
	}

	@Column(name = "apt_id")
	private int apartment_id;

	@Column(name = "unit_id")
	private int unit_id;

	@Column(name = "for_month")
	private String for_month;

	public int getApartment_id() {
		return apartment_id;
	}

	public void setApartment_id(int apartment_id) {
		this.apartment_id = apartment_id;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getFor_month() {
		return for_month;
	}

	public void setFor_month(String for_month) {
		this.for_month = for_month;
	}

	@Override
	public String toString() {
		return this.apartment_id + "_" + this.unit_id + "_" + this.for_month;
	}

}