package com.test.learningdi.db;

import com.test.learningdi.model.Employee;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbApi implements DbCallbacks {

    private static final String TAG = DbApi.class.getCanonicalName();

    @NotNull
    private Map<Integer, Employee> employeeDatabase;
    private Integer autoIncrement = -1;
    private boolean isOpen = false;

    public DbApi(){
        employeeDatabase = new HashMap<>();
        autoIncrement++;
    }


    @Override
    public int add(Employee employeeObject) {
        synchronized (this) {
            if(isOpen) {
                if (employeeExists(employeeObject)) {
                    return DbResponseConstants.ALREADY_EXISTS;
                }
                employeeDatabase.put(autoIncrement++, employeeObject);
                return DbResponseConstants.ADDED_SUCCESS;
            }
            return DbResponseConstants.DB_CLOSED;
        }
    }

    private boolean employeeExists(Employee obj) {
        if(isOpen) {
            if (employeeDatabase != null) {
                for (Map.Entry employee : employeeDatabase.entrySet()) {
                    Employee employee1 = (Employee) employee.getValue();
                    if (employee1.getName().contains(obj.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int addAll(List<Employee> listOfEmployees) {
        return 0;
    }

    @Override
    public int remove(int id) {
        synchronized (this) {
            if(isOpen) {
                if (employeeDatabase != null) {
                    if (employeeDatabase.get(id) != null) {
                        if (employeeDatabase.remove(id) != null) {
                            return DbResponseConstants.DELETED_SUCCESS;
                        }
                    }
                    return DbResponseConstants.FAILED;
                }
            }
            return DbResponseConstants.DB_CLOSED;
        }
    }

    @Override
    public int remove(Employee object) {
        synchronized (this) {
            if (isOpen) {
                if (employeeDatabase != null) {
                    for (Map.Entry employee : employeeDatabase.entrySet()) {
                        Employee employee1 = (Employee) employee.getValue();
                        if (employee1.getName().contains(object.getName())) {
                            employeeDatabase.remove(employee1);
                            return DbResponseConstants.DELETED_SUCCESS;
                        }
                    }
                }
                return DbResponseConstants.FAILED;
            }
        }
        return DbResponseConstants.DB_CLOSED;
    }

    @Override
    public int update(int id, Employee object) {
        synchronized (this) {
            if (isOpen) {
                if (employeeDatabase != null) {
                    if (employeeDatabase.get(id) != null) {
                        employeeDatabase.put(id, object);
                        return DbResponseConstants.UPDATED_SUCCESS;
                    }
                }
                return DbResponseConstants.FAILED;
            }
        }
        return DbResponseConstants.DB_CLOSED;
    }

    @Override
    public int delete(int id) {
        synchronized (this) {
            return DbResponseConstants.FAILED;
        }
    }

    @Override
    public int delete(Employee object) {
        return DbResponseConstants.FAILED;
    }

    @Override
    public Employee get(int id){
        synchronized (this) {
            if (isOpen) {
                if (employeeDatabase != null) {
                    employeeDatabase.get(id);
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public int clear() {
        this.employeeDatabase.clear();
        return -1;
    }

    @Override
    public int close() {
        isOpen = false;
        return -1;
    }

    @Override
    public int open() {
        isOpen = true;
        return 0;
    }

}
