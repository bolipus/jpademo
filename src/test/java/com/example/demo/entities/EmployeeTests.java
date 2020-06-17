package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.ParkingSpaceRepository;
import com.example.demo.repositories.PhoneRepository;
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
public class EmployeeTests {

  private TestEntityManager entityManager;
  private EmployeeRepository employeeRepository;


  @Autowired
  public EmployeeTests(TestEntityManager entityManager, EmployeeRepository employeeRepository) {
    this.entityManager = entityManager;
    this.employeeRepository = employeeRepository;
  }

  @Test
  public void findById() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    emp = entityManager.persist(emp);

    Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getId());

    assertTrue(optionalEmployee.isPresent());

    assertEquals(emp.getId(), optionalEmployee.get().getId(), "Ids are not equal");
  }



  @Test
  public void save() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    Employee savedEmployee = employeeRepository.save(emp);

    Employee foundEmployee = entityManager.find(Employee.class, savedEmployee.getId());

    assertNotNull(foundEmployee);
    assertEquals(savedEmployee.getId(), foundEmployee.getId(), "Ids are not equal");
  }

  @Test
  public void saveCascade() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    Department department = new Department();
    department.setName("ITFirst");
    department.getEmployees().add(emp);
    emp.setDepartment(department);

    Employee savedEmployee = employeeRepository.save(emp);

    Employee foundEmployee = entityManager.find(Employee.class, savedEmployee.getId());

    assertNotNull(foundEmployee);
    assertNotNull(foundEmployee.getDepartment(), "Department is not saved");
    assertEquals(savedEmployee.getId(), foundEmployee.getId(), "Ids are not equal");
  }

  @Test
  public void deleteById() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    emp = entityManager.persist(emp);



    employeeRepository.deleteById(emp.getId());

    Employee found = entityManager.find(Employee.class, emp.getId());

    assertNull(found, "Employee still exits.");

  }

  @Test
  public void deleteCascadeById() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    ParkingSpace parkingSpace = new ParkingSpace();
    parkingSpace.setEmployee(emp);
    parkingSpace.setLocation("A12");
    parkingSpace.setLot(20);
    emp.setParkingSpace(parkingSpace);

    Phone phone = new Phone();
    phone.setNumber("123-456-789");
    phone.setType("Samsung");
    emp.getPhones().add(phone);

    emp = entityManager.persist(emp);


    employeeRepository.deleteById(emp.getId());

    Employee found = entityManager.find(Employee.class, emp.getId());


    assertNull(found, "Employee still exits.");

    ParkingSpace ps = entityManager.find(ParkingSpace.class, emp.getParkingSpace().getId());

    assertNull(ps, "Parking space is not deleted.");

    for (Phone ph : emp.getPhones()) {
      Phone savedPhone = entityManager.find(Phone.class, ph.getId());
      assertNull(savedPhone, "Phone is not deleted.");
    }

  }

  @Test
  public void testUpdate() {
    Employee emp = new Employee();
    emp.setName("Peter");
    emp.setType(EmployeeType.FULL_TIME);

    Phone phone = new Phone();
    phone.setNumber("123-456-789");
    phone.setType("Samsung");
    emp.getPhones().add(phone);

    emp = entityManager.persist(emp);

    phone.setNumber("111-111-111");

    emp.setName("Damjan");

    Employee manegedEmp = entityManager.merge(emp);

    // FIXME
    log.info(manegedEmp);
  }


}
