package com.example.msusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode
public class Bill {

    private String idBill;
    private String idUser;
    private String customerBill;
    private String productBill;
    private Double totalPrice;
}