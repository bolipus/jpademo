package com.example.demo.entities;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Project {
  @Id
  @SequenceGenerator(name = "PROJ_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJ_SEQ")
  private Long id;

  private String name;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
  private Collection<Employee> employees;
}
