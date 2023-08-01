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
import model.beans.Teacher;

public class TeacherService {

    // url
    public static String url = "";

    public TeacherService() {
        url = """
              jdbc:sqlserver://localhost:1433;
                    databaseName=db01_asm_java3;
                    user=PH33935;password=123;encrypt=false
              """;
    }

    public Teacher createTeacherObj(String username) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Teacher o = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Teachers "
                    + "JOIN UserInformations ON Teachers.email = UserInformations.username "
                    + "WHERE UserInformations.username = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            resS = ps.executeQuery();
            while (resS.next()) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                try {
                    o = new Teacher(
                            Integer.valueOf(resS.getString("TeacherID")),
                             resS.getString("TeacherName"),
                             resS.getString("Email"),
                             resS.getInt("sex"),
                             LocalDate.parse(resS.getString("DateOfBirth"), dateFormat),
                             resS.getString("PhoneNumber"),
                             resS.getString("Provice"),
                             resS.getInt("MajorID"),
                             resS.getInt("TeacherManagerID"),
                             resS.getBytes("Photo"),
                             LocalDate.parse(resS.getString("LastModified"), dateFormat),
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

    public boolean createTeacher(Teacher o) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           INSERT INTO Teachers 
                           (TeacherName, Sex, DateOfBirth, PhoneNumber, Provice, MajorID, TeacherManagerID, Photo, LastModified, Descriptor)
                           VALUES
                           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                           """;
            ps = connection.prepareStatement(query);
                ps.setString(1, o.getTeacherName());
                ps.setInt(2, o.getSex());
                ps.setString(3, o.getDateOfBirth().toString());
                ps.setString(4, o.getPhoneNumber());
                ps.setString(5, o.getProvice());
                ps.setInt(6, o.getMajorID());
                if(o.getTeacherManagerID() == 0) {
                    ps.setNull(7, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(7, o.getTeacherManagerID());
                }
                if(o.getData() != null) {
                    ps.setBytes(8, o.getData());
                } else {
                    ps.setNull(8, java.sql.Types.VARBINARY);
                }
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

    public Teacher getTeacherByTeachingAssignmentID(int TeachingAssignmentID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Teacher teacher = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from getTeacherByTasm(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, TeachingAssignmentID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                teacher = new Teacher(
                        resS.getInt("teacherID"),
                        resS.getString("teacherName"),
                        resS.getString("email"),
                        resS.getInt("sex"),
                        LocalDate.parse(resS.getString("dateOfBirth"), format),
                        resS.getString("phoneNumber"),
                        resS.getString("provice"),
                        resS.getInt("majorID"),
                        resS.getInt("teacherManagerID"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor"));
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
        return teacher;
    }
    
    
    public boolean updateTeacher(Teacher o) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "{CALL Update_teacher(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            ps = connection.prepareStatement(query);

            ps.setInt(1, o.getTeacherID());
            ps.setInt(2, o.getSex());
            ps.setString(3, o.getPhoneNumber());
            ps.setString(4, o.getProvice());
            ps.setInt(5, o.getMajorID());
            if(o.getTeacherManagerID() == 0) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, o.getTeacherManagerID());
            }
            if(o.getData() != null) {
                ps.setBytes(7, o.getData());
            } else {
                ps.setNull(7, java.sql.Types.VARBINARY);
            }
            ps.setString(8, o.getLastModified().toString());
            ps.setString(9, o.getDescriptor());
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
    
    public List<Teacher> getListTeacher() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Teacher> list = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from v_getListTeacher";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                list.add(new Teacher(
                        resS.getInt("teacherID"),
                        resS.getString("teacherName"),
                        resS.getString("email"),
                        resS.getInt("sex"),
                        LocalDate.parse(resS.getString("dateOfBirth"), format),
                        resS.getString("phoneNumber"),
                        resS.getString("provice"),
                        resS.getInt("majorID"),
                        resS.getInt("teacherManagerID"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
    public Teacher getTeacherByTeacherID (int teacherID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Teacher teacher = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from dbo.getTeacherByTeacherID(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teacherID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                teacher = new Teacher(
                        resS.getInt("teacherID"),
                        resS.getString("teacherName"),
                        resS.getString("email"),
                        resS.getInt("sex"),
                        LocalDate.parse(resS.getString("dateOfBirth"), format),
                        resS.getString("phoneNumber"),
                        resS.getString("provice"),
                        resS.getInt("majorID"),
                        resS.getInt("teacherManagerID"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor"));
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return teacher;
    }
    

    public boolean deleteTeacher(int teacherID) {
        PreparedStatement ps = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Teachers SET isDelete = 1 WHERE Teachers.TeacherID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teacherID);

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
    
    
}
