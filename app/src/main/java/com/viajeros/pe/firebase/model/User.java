package com.viajeros.pe.firebase.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends FirebaseEntity{
    @Getter(AccessLevel.PUBLIC)
    private String name;
    @Getter(AccessLevel.PUBLIC)
    private String lastname;
    @Getter(AccessLevel.PUBLIC)
    private String email;
    @Getter(AccessLevel.PUBLIC)
    private Integer phone;
}
