package Model;

import javax.xml.*;

public class Employee extends Person {
    private double salary;
    private String deptCode;

    Employee(String id, String name, int phone, double salary, String deptCode){
        super(id, name, phone);
        this.salary = salary;
        this.deptCode = deptCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getdeptCode() {
        return deptCode;
    }

    public void setdeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String[] getAsStringArray() {
        return new String[]{ id,name,Integer.toString(phone),Double.toString(salary),deptCode };
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + phone + ", " + salary + ", " + ", " + deptCode;
    }
}
