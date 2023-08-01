package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.beans.Student;
import model.beans.Teacher;
import model.ultilities.PutStudent;
import static service.TeacherService.url;

public class StudentService {
    public static String url = "";

    public StudentService() {
        url = """
              jdbc:sqlserver://localhost:1433;
                    databaseName=db01_asm_java3;
                    user=PH33935;password=123;encrypt=false
              """;
    }
    
    public Student createStudentObj(String username) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Student o = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           select * from dbo.getStudentByUsername(?)
                           """;
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            resS = ps.executeQuery();
            while(resS.next()) {
                o = new Student(
                        resS.getInt("StudentID"), 
                        resS.getString("Fullname"),
                        resS.getInt("sex"), 
                        resS.getInt("batch"), 
                        LocalDate.parse(resS.getString("DateOfBirth"), format), 
                        resS.getString("email"), 
                        resS.getString("phoneNumber"), 
                        resS.getString("provice"), 
                        resS.getString("specificallyMajorName"), 
                        resS.getBytes("photo"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getString("descriptor"));
                return o;
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
    
    
    // CRUD
    public boolean createStudent(PutStudent o) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           INSERT INTO Students 
                           (FullName, Sex, Batch, PhoneNumber, DateOfBirth, Provice, SpecificallyMajorID, Photo, LastModified, Descriptor)
                           VALUES 
                           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                           """;
            ps = connection.prepareStatement(query);
            ps.setString(1, o.getStudentName());
            ps.setInt(2, o.getSex());
            ps.setInt(3, o.getBatch());
            ps.setString(4, o.getPhoneNumber());
            ps.setString(5, o.getDob().toString());
            ps.setString(6, o.getProvice());
            ps.setInt(7, o.getSpecificallyMajorID());
            ps.setBytes(8, o.getPhoto());
            ps.setString(9, o.getLastModified().toString());
            ps.setString(10, o.getDescriptor());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateStudent(PutStudent o) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "{CALL UpdateStudent (?,?,?,?,?,?,?,?,?,?,?)}";

            ps = connection.prepareStatement(query);

            ps.setString(1, o.getStudentName());
            ps.setInt(2, o.getSex());
            ps.setString(3, o.getDob().toString());
            ps.setString(4, o.getPhoneNumber());
            ps.setString(5, o.getProvice());
            ps.setBytes(6, o.getPhoto());
            ps.setInt(7, o.getBatch());
            ps.setInt(8, o.getSpecificallyMajorID());
            ps.setString(9, o.getLastModified().toString());
            ps.setString(10, o.getDescriptor());
            ps.setInt(11, o.getStudentID());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    
    public Student readStudent (int studentID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Student student = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM DBO.getStudent(?)";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            ps = connection.prepareStatement(query);
            ps.setInt(1, studentID);
            resS = ps.executeQuery();
            while(resS.next()) {
                student = new Student(
                        resS.getInt("StudentID"), 
                        resS.getString("Fullname"),
                        resS.getInt("sex"), 
                        resS.getInt("batch"), 
                        LocalDate.parse(resS.getString("DateOfBirth"), format), 
                        resS.getString("email"), 
                        resS.getString("phoneNumber"), 
                        resS.getString("provice"), 
                        resS.getString("specificallyMajorName"), 
                        resS.getBytes("photo"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getString("descriptor"));
                return student;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    public void deleleStudent(int id) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Students SET isDelete = 1 where Students.StudentID = ?";
            ps = connection.prepareStatement(query);

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public List<Student> getListStudentBySpecificallyMajorID (int specificallyMajorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Student> listStudent = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listStudent = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getStudentBySpecificallyMajorID(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            if (isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listStudent.add(new Student(
                        resS.getInt("studentID"), 
                        resS.getString("FullName"), 
                        resS.getInt("sex"), 
                        resS.getInt("batch"), 
                        LocalDate.parse(resS.getString("DateOfBirth"), format), 
                        resS.getString("email"), 
                        resS.getString("phoneNumber"), 
                        resS.getString("provice"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getString("descriptor")));
            }
            return listStudent;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listStudent;
    }
     

    
}
