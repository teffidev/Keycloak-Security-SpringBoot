package com.example.msusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode
public class User {

    private String id;
    private String userName;
    private String email;
    private String name;

    private List<Bill> bills;

}
