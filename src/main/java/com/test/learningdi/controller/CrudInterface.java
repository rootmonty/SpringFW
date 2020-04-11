package com.test.learningdi.controller;

import com.test.learningdi.model.Employee;

public interface CrudInterface {

    String insert(Employee employee);
    String get(int id);
    String update(int id, Employee employee);
    String delete(Employee employee);
    String deleteById(int id);
}
