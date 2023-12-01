import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataService implements Database {
    iDatabase database;
    public DataService(iDatabase database) {
        this.database = database;
    }
    @Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> empList = null;
        try {
            empList = tryGetEmployees();
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozók lekérdezése sikertelen!");
            System.err.println(e.getMessage());
        }
        return empList;
    }
    public ArrayList<Employee> tryGetEmployees() 
            throws SQLException {
        ArrayList<Employee> empList = new ArrayList<>();
        Connection conn = database.connectDb();
        String sql = "select * from employee";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            Employee emp = new Employee(
                rs.getInt("id"), 
                rs.getString("name"),
                rs.getString("city"),
                rs.getDouble("salary")
            );
            empList.add(emp);
        }        
        return empList;
    }

    @Override
    public void createEmployee(Employee employee) {
        try {
            tryCreateEmployee(employee);
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozó felvétele sikertelen!");
            System.err.println(e.getMessage());
        }
    }
    public void tryCreateEmployee(Employee employee) 
            throws SQLException {
        Connection conn = database.connectDb();
        String sql = "insert into employee " +
        "(name, city, salary) values " +
        "(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.name);
        pstmt.setString(2, employee.city);
        pstmt.setDouble(3, employee.salary);
        pstmt.execute();
        database.close(conn);
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            tryUpdateEmployee(employee);
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozó módosítása sikertelen!");
            System.err.println(e.getMessage());
        }
    }    
    private void tryUpdateEmployee(Employee employee) 
            throws SQLException {
        Connection conn = database.connectDb();
        String sql = "update employee set " +
        "name=?, city=?, salary=? " +
        "where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.name);
        pstmt.setString(2, employee.city);
        pstmt.setDouble(3, employee.salary);
        pstmt.setInt(4, employee.id);
        pstmt.executeUpdate();
        database.close(conn);
    }

    @Override
    public void deleteEmployee(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }
    
}
