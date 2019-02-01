package com.cogni.apartment.model;

/*POJO for a apartment details*/
public class ApartmentUnitBO {

	private int apartment_id;
	private int unit_id;
	private int unit_type_id;
	private int sq_ft;
	
	public ApartmentUnitBO(int apartment_id, int unit_id, int unit_type_id, int sq_ft) {
		super();
		this.apartment_id = apartment_id;
		this.unit_id = unit_id;
		this.unit_type_id = unit_type_id;
		this.sq_ft = sq_ft;
	}

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

	public int getUnit_type_id() {
		return unit_type_id;
	}

	public void setUnit_type_id(int unit_type_id) {
		this.unit_type_id = unit_type_id;
	}

	public int getSq_ft() {
		return sq_ft;
	}

	public void setSq_ft(int sq_ft) {
		this.sq_ft = sq_ft;
	}

}
