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
@Table(name = "branch", schema = "public_bank_information")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 370)
    private String address;

    @Column(name = "phone_number", unique = true, nullable = false)
    private Long phoneNumber;

    @Column(nullable = false, length = 250)
    private String city;

    @Column(name = "start_of_work", nullable = false)
    private java.sql.Time startOfWork;

    @Column(name = "end_of_work", nullable = false)
    private java.sql.Time endOfWork;

}
