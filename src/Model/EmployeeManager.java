package Model;

import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employeeList;

    public EmployeeManager() {
        employeeList = new ArrayList<>();
    }

    public Boolean validateID(String id) {
        for(Employee employee : employeeList) {
            if(employee.getId().equals(id))
                return false;
        }
        return true;
    }

    public Boolean add(Employee employee) {
        if(this.validateID(employee.getId())) {
            employeeList.add(employee);
            return true;
        }
        return false;
    }

    public Boolean delete(String id) {
        for (Employee employee : employeeList) {
            if (validateID(id)) {
                if (employee.getId().equals(id)) {
                    employeeList.remove(employee);
                    return true;
                }
            }
        }
        return false;
    }
}
