package Model;

public class Person {
    protected String id;
    protected String name;
    protected int phone;

    public Person(String id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + phone;
    }
}
