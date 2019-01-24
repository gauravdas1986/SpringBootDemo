package com.cogni.apartment.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "emp")
@EntityListeners(AuditingEntityListener.class)
/*@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)*/
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emp_id;

    @NotBlank
    private String emp_name;

    @NotBlank
    private String emp_dept;

    @NotBlank
    private String emp_sal;

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_dept() {
		return emp_dept;
	}

	public void setEmp_dept(String emp_dept) {
		this.emp_dept = emp_dept;
	}

	public String getEmp_sal() {
		return emp_sal;
	}

	public void setEmp_sal(String emp_sal) {
		this.emp_sal = emp_sal;
	}

   

}