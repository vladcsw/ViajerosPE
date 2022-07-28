package com.viajeros.pe.firebase.model;

import java.util.List;

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
public class Binnacle extends FirebaseEntity{
    @Getter(AccessLevel.PUBLIC)
    private String userId;
    @Getter(AccessLevel.PUBLIC)
    private List<TouristPlace> places;
    @Getter(AccessLevel.PUBLIC)
    private String name;
}
