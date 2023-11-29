package com.msbills.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@EqualsAndHashCode
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idBill;
    private String idUser;

    private String customerBill;

    private String productBill;

    private Double totalPrice;

}
