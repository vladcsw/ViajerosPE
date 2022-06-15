package com.viajeros.pe.firebase.model;

import com.google.firebase.firestore.Exclude;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;

import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Province extends FirebaseEntity{
    private int province_id;
    private String province;
    @Exclude
    private DocumentReferenceFirebaseLiveData<Department> departmentLiveData;
    @Exclude
    private Department department;

    public Province(int id, String province){
        this.province_id = id;
        this.province = province;
    }

    @Exclude
    public DocumentReferenceFirebaseLiveData<Department> getDepartmentLiveData(){
        return departmentLiveData;
    }

    @Exclude
    public Department getDepartment() {
        return department;
    }
}
