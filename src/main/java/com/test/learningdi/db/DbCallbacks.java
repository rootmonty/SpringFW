package com.test.learningdi.db;

import com.test.learningdi.model.Employee;

import java.util.List;

public interface DbCallbacks {

    int add(Employee object);
    int addAll(List<Employee> listOfEmployees);
    int remove(int id);
    int remove(Employee object);
    int update(int id, Employee object);
    int delete(int id);
    int delete(Employee object);
    Employee get(int id);

    int clear();
    int close();
    int open();

}
