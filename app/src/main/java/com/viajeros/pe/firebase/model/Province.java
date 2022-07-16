package com.viajeros.pe.firebase.model;

import com.google.firebase.firestore.Exclude;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;

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
public class Province extends FirebaseEntity{
    @Getter(AccessLevel.PUBLIC)
    private String province;
    @Exclude
    private DocumentReferenceFirebaseLiveData<Department> departmentLiveData;
    @Exclude
    private Department department;

    @Exclude
    public DocumentReferenceFirebaseLiveData<Department> getDepartmentLiveData(){
        return departmentLiveData;
    }

    @Exclude
    public Department getDepartment() {
        return department;
    }
}
