package Model;

public class Department extends EmployeeManager {
    String deptName;
    String deptCode;
    String address;
    Coordinates coordinates;
    // Preguntar sobre el porcentaje de zonaje.

    public Department(String deptName, String deptCode, String address, Coordinates coordinates) {
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.address = address;
        this.coordinates = coordinates;
    }

    public Department(){
        deptName = new String();
        deptCode = new String();
        address = new String();
        coordinates = new Coordinates(0, 0);
    }
    
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}