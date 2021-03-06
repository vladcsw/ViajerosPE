package com.viajeros.pe.firebase.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TouristPlace extends FirebaseEntity{
    @Getter(AccessLevel.PUBLIC)
    private String name;
    @Getter(AccessLevel.PUBLIC)
    private String coordinates;
    @Getter(AccessLevel.PUBLIC)
    private String description;
}
