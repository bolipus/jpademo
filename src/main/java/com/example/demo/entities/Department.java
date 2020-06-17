package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Department {
  @Id
  @SequenceGenerator(name = "DEP_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEP_SEQ")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
  private List<Employee> employees = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "EMP_SENIORITY")
  @MapKeyJoinColumn(name = "EMP_ID")
  @Column(name = "SENIORITY")
  private Map<Employee, Integer> seniorities = new HashMap<>();
}
