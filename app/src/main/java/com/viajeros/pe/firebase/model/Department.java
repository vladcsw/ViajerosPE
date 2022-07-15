package com.viajeros.pe.firebase.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Department extends FirebaseEntity{

    private int id_department;
    private String department;

}
