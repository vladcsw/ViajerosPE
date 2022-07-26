package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.Department;

public class DepartmentRepository extends FirebaseRepository<Department> {

    private static DepartmentRepository instance;

    public synchronized static DepartmentRepository getInstance() {
        if(instance == null) {
            instance = new DepartmentRepository();
        }
        return instance;
    }

    private DepartmentRepository() {
        super(Department.class);
    }


}
