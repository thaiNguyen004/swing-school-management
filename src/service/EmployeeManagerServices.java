package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import model.beans.Employee;
import model.ultilities.PutStudent;
import model.beans.Student;
import model.beans.Teacher;
import model.beans.TeachingAssignment;
import static service.StudentService.url;
import view.ultilities.TeacherManagement;

public class EmployeeManagerServices {

    public static String url = "";

    public EmployeeManagerServices() {
        url = """
              jdbc:sqlserver://localhost:1433;databaseName=db01_asm_java3;
              user=PH33935;password=123;encrypt=false
              """;
    }

    public Employee createEmployeeObj(String username) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Employee o = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Employees "
                    + "JOIN UserInformations ON Employees.email = UserInformations.username "
                    + "WHERE UserInformations.username = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            resS = ps.executeQuery();
            while (resS.next()) {
                DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    o = new Employee(
                            resS.getInt("EmployeeID"),
                            resS.getString("EmployeeName"),
                            resS.getString("Email"),
                            LocalDate.parse(resS.getString("DateOfBirth"), dateFormat2),
                            resS.getString("PhoneNumber"),
                            resS.getString("Provice"),
                            resS.getInt("Title"),
                            resS.getInt("ReportTo"),
                            LocalDate.parse(resS.getString("LastModified"), dateFormat2),
                            resS.getString("Descriptor"));
                    return o;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                resS.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return o;
    }
    
    
    
}
