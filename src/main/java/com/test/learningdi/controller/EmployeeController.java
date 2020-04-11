package com.test.learningdi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.learningdi.data.EmployeeRepository;
import com.test.learningdi.db.DbApi;
import com.test.learningdi.handler.ConnectionHandler;
import com.test.learningdi.handler.DbAuthentication;
import com.test.learningdi.model.Employee;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController implements CrudInterface {

    private static final String LOG = EmployeeController.class.getCanonicalName();

    private DbApi dbInstance;
    private ObjectMapper objectMapper;
    private ConnectionHandler connectionHandler;
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository, DbAuthentication dbAuthentication){
        this.employeeRepository = employeeRepository;
        connectionHandler = new ConnectionHandler(dbAuthentication);
        this.dbInstance = connectionHandler.getDbApiInstance();
        objectMapper = new ObjectMapper();
    }

    @Override
    @PostMapping("/employees/")
    public String insert(@RequestBody Employee employee) {
        connectionHandler.openConnectionGracefully();
        Integer response = dbInstance.add(employee);
        connectionHandler.closeConnectionGracefully();
        return response.toString();
    }

    @Override
    @GetMapping("/employees/")
    public String get(@RequestHeader int id) {
        connectionHandler.openConnectionGracefully();
        Employee fetchedEmployee = this.dbInstance.get(id);
        try {
            connectionHandler.closeConnectionGracefully();
            return objectMapper.writeValueAsString(fetchedEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        connectionHandler.closeConnectionGracefully();
        return null;
    }

    @Override
    @PutMapping("/employees/")
    public String update(@RequestHeader int id, @RequestBody Employee employee) {
        connectionHandler.openConnectionGracefully();
        int updateInfo = this.dbInstance.update(id, employee);
        connectionHandler.closeConnectionGracefully();
        return String.valueOf(updateInfo);
    }

    @Override
    @DeleteMapping("/employees/")
    public String delete(@RequestHeader Employee employee) {
        connectionHandler.openConnectionGracefully();
        int deleteInfo = this.dbInstance.delete(employee);
        connectionHandler.closeConnectionGracefully();
        return String.valueOf(deleteInfo);
    }

    @Override
    @DeleteMapping("/employees/")
    public String deleteById(int id) {
        connectionHandler.openConnectionGracefully();
        int deleteInfo = this.dbInstance.delete(id);
        connectionHandler.closeConnectionGracefully();
        return String.valueOf(deleteInfo);

    }
}
