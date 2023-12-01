import java.util.ArrayList;

public interface Database {
    public ArrayList<Employee> getEmployees();
    public void createEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
}
