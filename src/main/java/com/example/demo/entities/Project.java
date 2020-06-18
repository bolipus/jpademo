package com.example.demo.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "employees")
public class Project {
  @Id
  @SequenceGenerator(name = "PROJ_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJ_SEQ")
  private Long id;

  private String name;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @OrderBy("name")
  private Collection<Employee> employees = new LinkedHashSet<>();
}
