package com.viajeros.pe.firebase.model;

import com.google.firebase.firestore.Exclude;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;

public class District extends FirebaseEntity{

    private int district_id;
    private String district;
    @Exclude
    private DocumentReferenceFirebaseLiveData<Province> provinceLiveData;
    @Exclude
    private Province province;
    @Exclude
    private DocumentReferenceFirebaseLiveData<Department> departmentLiveData;
    @Exclude
    private Department department;

    public District(int id, String district){
        this.district_id = id;
        this.district = district;
    }

    @Exclude
    public DocumentReferenceFirebaseLiveData<Province> getProvinceLiveData() {
        return provinceLiveData;
    }

    @Exclude
    public DocumentReferenceFirebaseLiveData<Department> getDepartmentLiveData() {
        return departmentLiveData;
    }

    @Exclude
    public Province getProvince() {
        return province;
    }

    @Exclude
    public Department getDepartment() {
        return department;
    }

}
