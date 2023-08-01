package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.beans.SubjectDetail;
import static service.Service.url;

public class SubjectService {

    public static String url = "";

    public SubjectService() {
        url = """
              jdbc:sqlserver://localhost:1433;databaseName=db01_asm_java3;
              user=PH33935;password=123;encrypt=false
              """;
    }

    public SubjectDetail getSubjectByTeachingAssignmentID(int TeachingAssignmentID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        SubjectDetail subject = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from getSubjectByTasm(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, TeachingAssignmentID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                subject = new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getString("subjectName"),
                        resS.getString("subjectCode"),
                        resS.getInt("credit"),
                        resS.getInt("typeOfSubject"),
                        resS.getInt("furLough"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getInt("majorID"),
                        resS.getString("descriptor")
                );
            }

            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return subject;
    }
    
    public SubjectDetail getSubjectBySubjectDetailID(int subjectDetailID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        SubjectDetail subject = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "SELECT * FROM DBO.getSubjectFromSubjectDetailID(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectDetailID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                subject = new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getString("subjectName"),
                        resS.getString("subjectCode"),
                        resS.getInt("credit"),
                        resS.getInt("typeOfSubject"),
                        resS.getInt("furLough"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getInt("majorID"),
                        resS.getString("descriptor")
                );
            }
            return subject;
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
        return subject;
    }
    
    public SubjectDetail getSubjectBySubjectID(int SubjectID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        SubjectDetail subject = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * subjectsdetail where subjectsdetail.subjectid = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, SubjectID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                subject = new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getString("subjectName"),
                        resS.getString("subjectCode"),
                        resS.getInt("credit"),
                        resS.getInt("typeOfSubject"),
                        resS.getInt("furLough"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getInt("majorID"),
                        resS.getString("descriptor")
                );
            }

            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return subject;
    }
    
    
}
