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
@Table(name = "bank_details", schema = "public_bank_information")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long bik;

    @Column(unique = true, nullable = false)
    private Long inn;

    @Column(unique = true, nullable = false)
    private Long kpp;

    @Column(name = "cor_account", unique = true, nullable = false)
    private Integer corAccount;

    @Column(nullable = false, length = 180)
    private String city;

    @Column(name = "joint_stock_company", nullable = false, length = 15)
    private String jointStockCompany;

    @Column(nullable = false, length = 80)
    private String name;

}
