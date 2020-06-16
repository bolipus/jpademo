package com.example.demo.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {

  @Id
  @SequenceGenerator(name = "EMP_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
  private Long id;

  private String name;

  private Long salary;

  @Enumerated(EnumType.STRING)
  private EmployeeType type;

  @Temporal(TemporalType.DATE)
  private Date startDate;

  @ManyToOne
  @JoinColumn(name = "DEP_ID")
  private Department department;

  @OneToOne
  @JoinColumn(name = "PSPACE_ID")
  ParkingSpace parkingSpace;
}
