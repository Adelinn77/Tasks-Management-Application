package DataModel;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private int idEmployee = 1;
    private String name;
    static int id = 0;

    public Employee() {
        this.idEmployee = id+1;
        id++;
    }

    public Employee(String name) {
        this.idEmployee = id+1;
        id++;
        this.name = name;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setId(int value) {
        id = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return idEmployee == employee.idEmployee && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEmployee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                '}';
    }
}
