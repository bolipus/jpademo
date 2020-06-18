package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ParkingSpace {
  @Id
  @SequenceGenerator(name = "PARSP_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARSP_SEQ")
  private Long id;

  private Integer lot;

  private String location;

  @OneToOne(mappedBy = "parkingSpace", fetch = FetchType.LAZY)
  private Employee employee;
}
