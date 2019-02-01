package com.cogni.apartment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.MaintenanceKey;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceDTO, MaintenanceKey> {

}