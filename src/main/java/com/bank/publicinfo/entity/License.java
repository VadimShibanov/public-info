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
@Table(name = "license", schema = "public_bank_information")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Byte photo;

    @OneToOne
    @JoinColumn(name="bank_details_id", referencedColumnName = "id")
    private BankDetails bankDetails;
}
