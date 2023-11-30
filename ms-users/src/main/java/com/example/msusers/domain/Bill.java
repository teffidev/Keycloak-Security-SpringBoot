package com.example.msusers.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bill {

    private String idBill;
    private String idUser;
    private String customerBill;
    private String productBill;
    private Double totalPrice;
}
