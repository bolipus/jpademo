package com.example.demo.entities;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Address {
  private String street;
  private String city;
  private String country;
}
