package com.viajeros.pe.firebase.model;

import androidx.lifecycle.LiveData;

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
    private LiveData<TouristPlace> places;
}
