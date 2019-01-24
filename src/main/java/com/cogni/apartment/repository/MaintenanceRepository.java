package com.cogni.apartment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogni.apartment.model.Emp;
import com.cogni.apartment.model.MaintenanceDTO;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceDTO, Long> {

}