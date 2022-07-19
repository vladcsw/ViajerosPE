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
public class ToDoListItem extends FirebaseEntity{
    @Getter(AccessLevel.PUBLIC)
    private Boolean item_state;
    @Getter(AccessLevel.PUBLIC)
    private String statement;
}
