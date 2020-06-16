package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
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

    @Embedded
    private Address address;

    @ElementCollection
    @JoinTable(name = "EMP_VACATION", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"))
    private Collection<VacationEntry> vacationBookings;

    @ElementCollection
    @JoinTable(name = "EMP_NICKNAMES", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"))
    private Set<String> nickNames;

    @ManyToMany
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "id"))
    private Collection<Project> projects;

    @OneToMany
    @JoinTable(name = "EMP_PHONE", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PHONE_ID", referencedColumnName = "ID"))
    private Collection<Phone> phones;

    @ElementCollection
    @CollectionTable(name = "EMP_PHONENUM")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<PhoneType, String> phoneNumbers;

}
