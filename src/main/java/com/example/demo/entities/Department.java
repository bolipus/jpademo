package com.example.demo.entities;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
  @OrderColumn(name = "EMP_ORDER")
  private List<Employee> employees;

}
