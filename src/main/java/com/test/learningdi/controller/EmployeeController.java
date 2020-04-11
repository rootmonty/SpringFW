package com.test.learningdi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.learningdi.db.DbApi;
import com.test.learningdi.model.Employee;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController implements CrudInterface {

    private static final String LOG = EmployeeController.class.getCanonicalName();

    private DbApi dbInstance;
    private ObjectMapper objectMapper;

    public EmployeeController(DbApi instance){
        this.dbInstance = instance;
        objectMapper = new ObjectMapper();
    }

    @Override
    @PostMapping("/employees/")
    public String insert(@RequestBody Employee employee) {
        Integer response = dbInstance.add(employee);
        return response.toString();
    }

    @Override
    @GetMapping("/employees/")
    public String get(@RequestHeader int id) {
        Employee fetchedEmployee = this.dbInstance.get(id);
        try {
            return objectMapper.writeValueAsString(fetchedEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @PutMapping("/employees/")
    public String update(@RequestHeader int id, @RequestBody Employee employee) {
        int updateInfo = this.dbInstance.update(id, employee);
        return String.valueOf(updateInfo);
    }

    @Override
    @DeleteMapping("/employees/")
    public String delete(@RequestHeader Employee employee) {
        int deleteInfo = this.dbInstance.delete(employee);
        return String.valueOf(deleteInfo);
    }

    @Override
    @DeleteMapping("/employees/")
    public String deleteById(int id) {
        int deleteInfo = this.dbInstance.delete(id);
        return String.valueOf(deleteInfo);
    }
}
