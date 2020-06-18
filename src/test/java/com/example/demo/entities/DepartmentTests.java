package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.example.demo.repositories.DepartmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Log4j2
public class DepartmentTests {

  private TestEntityManager entityManager;
  private DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentTests(TestEntityManager entityManager, DepartmentRepository departmentRepository) {
    this.entityManager = entityManager;
    this.departmentRepository = departmentRepository;
  }

  @Test

  public void findById() {
    Department department = new Department();
    department.setName("ITFirst");

    department = entityManager.persist(department);

    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);
    emp.setDepartment(department);

    department.getEmployees().add(emp);

    entityManager.persist(emp);

    Optional<Department> optionalDepartment = departmentRepository.findById(department.getId());

    assertTrue(optionalDepartment.isPresent());

    assertEquals(department.getId(), optionalDepartment.get().getId(), "Ids are not equal.");

    assertEquals(department.getEmployees().size(), optionalDepartment.get().getEmployees().size(),
        "Number of employees are not equal");

  }

}
