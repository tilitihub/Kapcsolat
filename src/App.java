import java.sql.Connection;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        DataService ds = new DataService(new Sqlite());

        Employee updemp = new Employee(
            7, 
            "Perge Mária", 
            "Szeged", 
            395.0);
        ds.updateEmployee(updemp);

        // Employee newemp = new Employee("Kis Elemér", "Hatvan", 395.0);
        // ds.createEmployee(newemp);

        ArrayList<Employee> empList = ds.getEmployees();
        for(Employee emp  : empList  ) {
            System.out.println(emp.id + " " +emp.name);
        }
        
        
    }
}
