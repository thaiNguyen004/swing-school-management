package service;

import model.beans.Employee;
import java.sql.*;

public class EmployeeService {
    private static String url;

    public EmployeeService() {
        url = """
              jdbc:sqlserver://localhost:1433;
              databaseName=db01_asm_java3;
              user=PH33935;password=123; encrypt=false;
              """;
        
    }
    
    public boolean createEmployee(Employee o) {
        Connection connection = null;
        CallableStatement cs = null;
        
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL createEmployee(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cs = connection.prepareCall(query);
            cs.setString(1, o.getEmployeeName());
            cs.setInt(2, o.getSex());
            cs.setString(3, o.getDateOfBirth().toString());
            cs.setString(4, o.getPhoneNumber());
            cs.setString(5, o.getProvice());
            cs.setInt(6, o.getTitle());
            cs.setBytes(7, o.getPhoto());
            cs.setString(8, o.getLastModified().toString());
            cs.setString(9, o.getDescriptor());
            cs.execute();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean deleteEmployee(int employeeID, String userName) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           UPDATE Employees SET Employees.isDelete = 1 WHERE Employees.EmployeeID = ?
                           UPDATE UserInformations SET UserInformations.isDelete = 1 WHERE UserInformations.Username = ?
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, employeeID);
            ps.setString(2, userName);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return true;
    }
    
    
}
