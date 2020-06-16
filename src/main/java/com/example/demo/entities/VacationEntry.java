package com.example.demo.entities;

import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Embeddable
@Data
public class VacationEntry {
  @Temporal(TemporalType.DATE)
  private Calendar startDate;

  private int daysTaken;
}
