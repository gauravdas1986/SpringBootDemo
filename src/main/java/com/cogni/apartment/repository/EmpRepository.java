package com.cogni.apartment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogni.apartment.model.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {

}