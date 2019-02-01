package com.cogni.apartment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "maintenance_master")
@EntityListeners(AuditingEntityListener.class)

public class MaintenanceDTO implements Serializable{

	/*Composite primary key*/
	@EmbeddedId
	private MaintenanceKey key;

	public MaintenanceDTO(){
		
	}
	
	public MaintenanceDTO(MaintenanceKey key, int maintenance_amount, int due_amount) {
		super();
		this.key = key;
		this.maintenance_amount = maintenance_amount;
		this.due_amount = due_amount;
	}

	@Column(name = "maintainance_amount")
	@NotNull
	private int maintenance_amount;

	@Column(name = "due_amount")
	private int due_amount;

	public MaintenanceKey getKey() {
		return key;
	}

	public void setKey(MaintenanceKey key) {
		this.key = key;
	}

	public int getMaintenance_amount() {
		return maintenance_amount;
	}

	public void setMaintenance_amount(int maintenance_amount) {
		this.maintenance_amount = maintenance_amount;
	}

	public int getDue_amount() {
		return due_amount;
	}

	public void setDue_amount(int due_amount) {
		this.due_amount = due_amount;
	}

}