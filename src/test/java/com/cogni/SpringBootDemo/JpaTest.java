package com.cogni.SpringBootDemo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cogni.apartment.model.Emp;
import com.cogni.apartment.repository.EmpRepository;

public class JpaTest {
	@Autowired
	private EmpRepository empRepo;
	
	@Autowired
	private EntityManager entityManager;

	public static void main(String[] args) throws Exception {
		Emp emp = new Emp(1004l, "AAA", "JAVA212121212121", "20000");
		new JpaTest().write(emp);
	}

	@Transactional
	public void write(Emp emp) throws Exception {

		try {
			System.out.println("JPA called with emp " + emp.getEmp_name());
			empRepo.save(emp);
//			entityManager.persist(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
