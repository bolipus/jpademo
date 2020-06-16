package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Phone {
  @Id
  @SequenceGenerator(name = "PHONE_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_SEQ")
  private Long id;

  private String type;
  private String number;
}
