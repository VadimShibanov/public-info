package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "atm", schema = "public_bank_information")
public class Atm {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false ,length = 370)
    private String address;

    @Column(name = "start_of_work")
    private java.sql.Time startOfWork;

    @Column(name = "end_of_work")
    private java.sql.Time endOfWork;

    @Column(name = "all_hours",nullable = false)
    private Boolean allHours;

    @OneToOne
    @JoinColumn(name="branch_id", referencedColumnName = "id")
    private Branch branch;
}


