package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "employees")
public class Department {
  @Id
  @SequenceGenerator(name = "DEP_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEP_SEQ")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<Employee> employees = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "EMP_SENIORITY")
  @MapKeyJoinColumn(name = "EMP_ID")
  @Column(name = "SENIORITY")
  private Map<Employee, Integer> seniorities = new LinkedHashMap<>();

}
