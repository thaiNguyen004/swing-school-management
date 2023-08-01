package service;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.lang.model.util.Types;
import model.beans.Building;
import model.beans.Classroom;
import model.beans.Classs;
import model.beans.Employee;
import model.beans.Grade;
import model.beans.Major;
import model.beans.SpecificallyMajor;
import model.beans.Student;
import model.beans.SubjectDetail;
import model.beans.Teacher;
import model.beans.TeachingAssignment;
import static service.SubjectService.url;

public class Service {

    public static String url = "";

    public Service() {
        url = """
              jdbc:sqlserver://localhost:1433;
                    databaseName=db01_asm_java3;
                    user=PH33935;password=123;encrypt=false
              """;
    }

    public List<TeachingAssignment> getTeachingAssignments(int teacherID) {

        List<TeachingAssignment> listTeachingAssignments = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resS = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM dbo.getTeachingAssignment(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teacherID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                try {
                    TeachingAssignment teachingAssignment
                            = new TeachingAssignment(
                                    resS.getInt("TeachingAssignmentID"),
                                    resS.getInt("SubjectDetailID"),
                                    resS.getInt("ClassID"),
                                    resS.getInt("TeacherID"),
                                    resS.getInt("TeachInShift"),
                                    resS.getInt("ClassroomID"),
                                    resS.getInt("Semester"),
                                    resS.getString("Season"),
                                    resS.getInt("Block"),
                                    LocalDate.parse(resS.getString("LastModified"), format),
                                    resS.getString("Descriptor"),
                                    resS.getInt("isTeaching")
                            );
                    listTeachingAssignments.add(teachingAssignment);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return listTeachingAssignments;
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
        return listTeachingAssignments;
    }
    
    
    

    public List<TeachingAssignment> getAllTeachingAssignments() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<TeachingAssignment> list = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from dbo.getAllTeachingAssignment()";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                list.add(new TeachingAssignment(
                        resS.getInt("TeachingAssignmentID"),
                        resS.getInt("SubjectDetailID"),
                        resS.getInt("ClassID"),
                        resS.getInt("TeacherID"),
                        resS.getInt("TeachInShift"),
                        resS.getInt("ClassroomID"),
                        resS.getInt("Semester"),
                        resS.getString("Season"),
                        resS.getInt("Block"),
                        LocalDate.parse(resS.getString("LastModified"), format),
                        resS.getString("Descriptor"),
                        resS.getInt("isTeaching")
                )
                );
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
        return list;
    }

    public List<Student> getListFullStudents() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Student> list = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM v_student";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while (resS.next()) {

                try {
                    list.add(new Student(resS.getInt("StudentID"),
                            resS.getString("FullName"),
                            resS.getInt("Sex"),
                            resS.getInt("Batch"),
                            LocalDate.parse(resS.getString("DateOfBirth"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Email"),
                            resS.getString("PhoneNumber"),
                            resS.getString("Provice"),
                            resS.getString("SpecificallyMajorName"),
                            resS.getBytes("Photo"),
                            LocalDate.parse(resS.getString("LastModified"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Descriptor")));
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
        return list;
    }

    public List<Student> getStudentParticipations(int teachingAssignmentID, int status) {
        List<Student> listStudentParticipations = new ArrayList<>();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PreparedStatement ps = null;
        ResultSet resS = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from dbo.getStudentsParticipation(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teachingAssignmentID);
            ps.setInt(2, status);
            resS = ps.executeQuery();
            while (resS.next()) {
                listStudentParticipations.add(new Student(
                        resS.getInt("StudentID"),
                        resS.getString("Fullname"),
                        resS.getInt("Sex"),
                        resS.getInt("Batch"),
                        LocalDate.parse(resS.getString("DateOfBirth"), format1),
                        resS.getString("Email"),
                        resS.getString("PhoneNumber"),
                        resS.getString("Provice"),
                        resS.getString("SpecificallyMajorName"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("LastModified"), format1),
                        resS.getString("Descriptor")));
            }
            return listStudentParticipations;
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
        return listStudentParticipations;
    }

    public List<Student> getListStudentsByClassCode(int teachingAssignmentID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Student> list = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM DBO.getStudentByClass(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teachingAssignmentID);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while (resS.next()) {
                list.add(new Student(resS.getInt("StudentID"),
                        resS.getString("FullName"),
                        resS.getInt("Sex"),
                        resS.getInt("Batch"),
                        LocalDate.parse(resS.getString("DateOfBirth"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        resS.getString("Email"),
                        resS.getString("PhoneNumber"),
                        resS.getString("Provice"),
                        resS.getString("SpecificallyMajorName"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("LastModified"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        resS.getString("Descriptor")));
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
        return list;
    }

    public List<Student> searchStudent(String MSSV) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Student> listStudent = null;
        String id;
        if (MSSV.length() > 2) {
            try {
                if (!MSSV.toUpperCase().substring(0, 2).equals("PH")) {
                    return null;
                } else {
                    id = MSSV.substring(2, MSSV.length());
                }
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
            select Students.StudentID, Students.FullName, Students.Sex, Students.Batch, Students.DateOfBirth, Students.Email, Students.PhoneNumber, Students.Provice, SpecificallyMajors.SpecificallyMajorName, Students.Photo, Students.LastModified, Students.Descriptor 
            from Students
            join SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Students.SpecificallyMajorID
            join mssv on students.studentid = mssv.studentid
            WHERE mssv.studentcode like ? and students.isDelete = 0
                           """;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + id + "%");
            listStudent = new ArrayList<>();
            resS = ps.executeQuery();
            while (resS.next()) {
                listStudent.add(new Student(resS.getInt("StudentID"),
                        resS.getString("FullName"),
                        resS.getInt("Sex"),
                        resS.getInt("Batch"),
                        LocalDate.parse(resS.getString("DateOfBirth"), format),
                        resS.getString("Email"),
                        resS.getString("PhoneNumber"),
                        resS.getString("Provice"),
                        resS.getString("SpecificallyMajorName"),
                        resS.getBytes("Photo"),
                        LocalDate.parse(resS.getString("LastModified"), format),
                        resS.getString("Descriptor")));

            }
            return listStudent;
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
        return null;
    }

    public List<Student> getListStudentsBySpecificallyMajorID(int specificallyMajorID, boolean isDelete) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Student> list = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM DBO.getStudentBySpecificallyMajorID(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            if(isDelete) {
                ps.setInt(2, 1);
            }else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while (resS.next()) {
                try {
                    list.add(new Student(resS.getInt("StudentID"),
                            resS.getString("FullName"),
                            resS.getInt("Sex"),
                            resS.getInt("Batch"),
                            LocalDate.parse(resS.getString("DateOfBirth"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Email"),
                            resS.getString("PhoneNumber"),
                            resS.getString("Provice"),
                            resS.getString("SpecificallyMajorName"),
                            resS.getBytes("Photo"),
                            LocalDate.parse(resS.getString("LastModified"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Descriptor")));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
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
        return list;
    }

    public List<Student> getListStudentsBySex(int Sex) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Student> list = null;

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM DBO.getStudentBySex(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, Sex);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while (resS.next()) {
                try {
                    list.add(new Student(resS.getInt("StudentID"),
                            resS.getString("FullName"),
                            resS.getInt("Sex"),
                            resS.getInt("Batch"),
                            LocalDate.parse(resS.getString("DateOfBirth"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Email"),
                            resS.getString("PhoneNumber"),
                            resS.getString("Provice"),
                            resS.getString("SpecificallyMajorName"),
                            resS.getBytes("Photo"),
                            LocalDate.parse(resS.getString("LastModified"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            resS.getString("Descriptor")));
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
        return list;
    }

    public Map<String, Integer> getSpecificallyMajorMap() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Map<String, Integer> specificallyMap = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           SELECT SpecificallyMajors.SpecificallyMajorID, 
                           SpecificallyMajors.SpecificallyMajorName FROM SpecificallyMajors
                           """;
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            specificallyMap = new TreeMap<>();
            while (resS.next()) {
                if(resS.getInt("SpecificallyMajorID") == 0) {
                    return null;
                }
                specificallyMap.put(resS.getString("SpecificallyMajorName"),
                        resS.getInt("SpecificallyMajorID"));
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
        return specificallyMap;
    }

    
    public boolean createSpecificallyMajor(int majorID, SpecificallyMajor o ) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           {CALL createSpecificallyMajor(?, ?, ?, ?)}
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            ps.setString(2, o.getSpecificallyMajorName());
            ps.setString(3, o.getSpecificallyMajorCode());
            ps.setString(4, o.getDescriptor());
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
    
    public Map<String, Integer> getMajorMap() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Map<String, Integer> majorMap = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                           SELECT Majors.MajorID, Majors.MajorName FROM Majors where isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            majorMap = new LinkedHashMap<>();
            while (resS.next()) {
                majorMap.put(resS.getString("MajorName"),
                        resS.getInt("MajorID"));
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
        return majorMap;
    }

    public Map<String, Integer> getAllClassCode() {
        PreparedStatement ps = null;
        ResultSet resS = null;
        Map<String, Integer> mapClassCode = null;
        boolean foundRecord = false;
        
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from v_classcodes";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            mapClassCode = new HashMap<>();
            while (resS.next()) {
                foundRecord = true;
                
                if(resS.getInt("ClassID") == 0) {
                    return null;
                }
                mapClassCode.put(resS.getString("Classname"), resS.getInt("ClassID"));
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
        if(!foundRecord) {
            return null;
        }
        return mapClassCode;
    }

    public List<Teacher> getListTeacherByMajorID(int majorID, boolean isDelete) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Teacher> listTeacher = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from dbo.getTeacherByMajorID(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            if(isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            listTeacher = new ArrayList<>();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                listTeacher.add(new Teacher(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listTeacher;
    }

    public List<Teacher> getListTeacherManager(int majorID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Teacher> listTeacherManager = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "select * from dbo.getTeacherManager(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            resS = ps.executeQuery();
            listTeacherManager = new ArrayList<>();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (resS.next()) {
                listTeacherManager.add(new Teacher(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listTeacherManager;
    }

    public String getClassname(int TeachingAssignmentID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getClassname(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, TeachingAssignmentID);
            resS = ps.executeQuery();
            String result = "";
            while (resS.next()) {
                result = resS.getString("Classname");
            }
            return result;
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
        return null;
    }

    public String getSubjectName(int subjectID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        String str = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT Subjects.SubjectName FROM Subjects WHERE Subjects.SubjectID = ? and Subjects.isDelete = 0";
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            while (resS.next()) {
                str = resS.getString("SubjectName");
            }

            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    } 
    
    public String getSubjectNameBySubjectDetailID(int subjectDetailID) {
        PreparedStatement ps = null;
        ResultSet resS = null;
        String str = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = """
                            SELECT Subjects.SubjectName FROM Subjects  
                                 JOIN SubjectsDetail ON Subjects.SubjectID = SubjectsDetail.SubjectID
                            WHERE SubjectsDetail.subjectDetailID = ? AND Subjects.isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectDetailID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            while (resS.next()) {
                str = resS.getString("SubjectName");
            }

            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    } 

    public byte[] getPhotoBinary(int StudentID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "SELECT Students.Photo FROM Students WHERE StudentID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, StudentID);
            resS = ps.executeQuery();
            byte[] result;
            while (resS.next()) {
                result = resS.getBytes("Photo");
                return result;
            }
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
        return null;
    }

    public void insertDefault() {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "UPDATE Students SET Photo = ?";
            ps = connection.prepareStatement(query);
            Image img;
            byte[] data = null;
            try {
                img = ImageIO.read(new File("C:\\Users\\nguye\\OneDrive\\Desktop\\asm\\image\\no-image-icon-6.png"));
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) img, "png", bout);
                data = bout.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ps.setBytes(1, data);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeachingAssignment> getTeachingAssignmentByClassID(int classID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        List<TeachingAssignment> list = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getTeachingAssignmentByClassID(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, classID);
            resS = ps.executeQuery();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            list = new ArrayList<>();
            while (resS.next()) {
                list.add(new TeachingAssignment(
                        resS.getInt("teachingAssignmentID"),
                        resS.getInt("SubjectDetailID"),
                        resS.getInt("classID"),
                        resS.getInt("teacherID"),
                        resS.getInt("teachInShift"),
                        resS.getInt("classroomID"),
                        resS.getInt("semester"),
                        resS.getString("season"),
                        resS.getInt("block"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor"),
                        resS.getInt("isTeaching")));
            }
            return list;
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
        return list;
    }

    public String getNameTeacher(int teacherID) {
        PreparedStatement ps = null;
        Connection connection = null;
        String teacherName = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "select teachers.teachername from teachers where teachers.teacherID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teacherID);
            resS = ps.executeQuery();
            while (resS.next()) {
                teacherName = resS.getString("TeacherName");
            }
            return teacherName;
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
        return teacherName;
    }

    public String getEmailTeacher(int teacherID) {
        PreparedStatement ps = null;
        Connection connection = null;
        String email = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "select teachers.email from teachers where teachers.teacherID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teacherID);
            resS = ps.executeQuery();
            while (resS.next()) {
                email = resS.getString("Email");
            }
            return email;
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
        return email;
    }

    public String getMajorNameByMajorID(int majorID) {
        PreparedStatement ps = null;
        Connection connection = null;
        String res = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "Select Majors.MajorName from Majors where Majors.MajorID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            resS = ps.executeQuery();
            while (resS.next()) {
                res = resS.getString("MajorName");
            }
            return res;
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
        return res;
    }
    
    public String getSpecificallyMajorNameByID(int specificallyMajorID) {
        PreparedStatement ps = null;
        Connection connection = null;
        String res = null;
        ResultSet resS = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "select s.SpecificallyMajorName from SpecificallyMajors s where s.SpecificallyMajorID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            resS = ps.executeQuery();
            while (resS.next()) {
                res = resS.getString("SpecificallyMajorName");
            }
            return res;
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
        return res;
    }

    public List<Major> getAllMajors(boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Major> listMajor = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listMajor = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "Select majors.majorid, majors.majorname, majorcode, lastmodified, descriptor from majors where majors.isDelete = ?";
            ps = connection.prepareStatement(query);
            if (isDelete) {
                ps.setInt(1, 1);
            } else {
                ps.setInt(1, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listMajor.add(new Major(
                        resS.getInt("majorID"),
                        resS.getString("majorName"),
                        resS.getString("MajorCode"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor")));
            }
            return listMajor;
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
        return listMajor;
    }

    public List<SubjectDetail> getSubjectsByMajorID(int majorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<SubjectDetail> listSubject = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listSubject = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getSubjectByMajorID(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            if (isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listSubject.add(new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getString("subjectName"),
                        resS.getString("subjectCode"),
                        resS.getInt("credit"),
                        resS.getInt("typeOfSubject"),
                        resS.getInt("furLough"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getInt("majorID"),
                        resS.getString("descriptor")));
            }
            return listSubject;
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
        return listSubject;
    }

    public List<SubjectDetail> getSubjectBySpecificallyMajorID(int specificallyMajorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<SubjectDetail> listSubject = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listSubject = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getSubjectDetailBySpecificallyMajor(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            if (isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listSubject.add(new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getInt("subjectDetailID"),
                        resS.getString("subjectName"),
                        resS.getString("subjectCode"),
                        resS.getInt("credit"),
                        resS.getInt("typeOfSubject"),
                        resS.getInt("furLough"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getInt("majorID"),
                        resS.getInt("specificallyMajorID"),
                        resS.getString("descriptor")));
            }
            return listSubject;
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
        return listSubject;
    }

    public List<SpecificallyMajor> getSpecificallyMajorByMajorID(int majorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<SpecificallyMajor> listSpecificallyMajors = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listSpecificallyMajors = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getSpecificallyMajorByMajorID(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, majorID);
            if (isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listSpecificallyMajors.add(new SpecificallyMajor(
                        resS.getInt("specificallyMajorID"),
                        resS.getString("specificallyMajorName"),
                        resS.getString("specificallyMajorCode"),
                        resS.getInt("majorID"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor")));
            }
            return listSpecificallyMajors;
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
        return listSpecificallyMajors;
    }

    public List<Classs> getClassBySpecificallyMajorID(int specificallyMajorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Classs> listClass = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listClass = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getClassCodeBySubject(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            if (isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listClass.add(new Classs(
                        resS.getInt("classID"),
                        resS.getString("classCode"),
                        resS.getInt("majorID"),
                        resS.getInt("specificallyMajorID"),
                        resS.getInt("batch"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor")));
            }
            return listClass;
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
        return listClass;
    }

    public List<Classs> getClassFromSubjectDetailAvailable(
            int subjectDetailID, int specificallyMajorID, boolean isDelete) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Classs> listClass = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listClass = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getClassFromSubjectDetailAvailable(?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectDetailID);
            ps.setInt(2, specificallyMajorID);
            if (isDelete) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            resS = ps.executeQuery();
            while (resS.next()) {
                listClass.add(new Classs(
                        resS.getInt("classID"),
                        resS.getString("classCode"),
                        resS.getInt("majorID"),
                        resS.getInt("specificallyMajorID"),
                        resS.getInt("batch"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor")));
            }
            return listClass;
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
        return listClass;
    }

    public List<Classroom> getClassroomAvailableFromShift(int shift) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Classroom> listClassroom = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listClassroom = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getClassroomFromShift(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, shift);
            resS = ps.executeQuery();
            while (resS.next()) {
                listClassroom.add(new Classroom(
                        resS.getInt("classroomID"), 
                        resS.getInt("numberOfFloor"), 
                        resS.getInt("numberOfRoom"), 
                        resS.getInt("buildingID"), 
                        resS.getInt("status"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getString("descriptor")));
            }
            return listClassroom;
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
        return listClassroom;
    }
    
    
    public boolean createTeachingAssignment(TeachingAssignment o) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL createTeachingAssignment(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            ps = connection.prepareStatement(query);
            ps.setInt(1, o.getSubjectID());
            ps.setInt(2, o.getClassID());
            ps.setInt(3, o.getTeacherID());
            ps.setInt(4, o.getTeachInShift());
            ps.setInt(5, o.getClassroomID());
            ps.setInt(6, o.getSemester());
            ps.setString(7, o.getSeason());
            ps.setInt(8, o.getBlock());
            ps.setString(9, o.getLastModified().toString());
            ps.setString(10, o.getDescriptor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public List<TeachingAssignment> getListTeachingAssignmentBySpecificallyMajorID (int specificallyMajorID) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<TeachingAssignment> listTeachingAssignment = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listTeachingAssignment = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getTeachingAssignmentsFromSubjectDetailID(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            resS = ps.executeQuery();
            while (resS.next()) {
                listTeachingAssignment.add(new TeachingAssignment(
                        resS.getInt("teachingAssignmentID"),
                        resS.getInt("SubjectDetailID"),
                        resS.getInt("classID"),
                        resS.getString("subjectName"),
                        resS.getInt("teacherID"),
                        resS.getInt("teachInShift"),
                        resS.getInt("classroomID"),
                        resS.getInt("semester"),
                        resS.getString("season"),
                        resS.getInt("block"),
                        LocalDate.parse(resS.getString("lastModified"), format),
                        resS.getString("descriptor"),
                        resS.getInt("isTeaching")));
            }
            return listTeachingAssignment;
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
        return listTeachingAssignment;
    }
   
    
    public String getClassroomName(int classroomID, boolean isDelete) {
        CallableStatement cs = null;
        Connection connection = null;
        ResultSet resS = null;
        String classroomName = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL getClassroomByClassroomID(?, ?, ?)}";
            cs = connection.prepareCall(query);
            cs.setInt(1, classroomID);
            if(isDelete) {
                cs.setInt(2, 1);
            } else {
                cs.setInt(2, 0);
            }
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            classroomName = cs.getString(3);
            return classroomName;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return classroomName;
    }
    
    
    public List<Student> getStudentJoinedButCanChange(int specificallyMajorID, int subjectDetailID, int status) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Student> listStudent = null;
        ResultSet resS = null;
        try {
            listStudent = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getStudentJoinedButCanChange(?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            ps.setInt(2, subjectDetailID);
            ps.setInt(3, status);
            resS = ps.executeQuery();
            while (resS.next()) {
                listStudent.add(new Student(
                        resS.getInt("studentID"), 
                        resS.getString("Fullname"), 
                        resS.getInt("sex"), 
                        resS.getString("specificallyMajorName")));
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
    
    
    public List<Student> getStudentHaveNotJoined(int specificallyMajorID, int subjectDetailID) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<Student> listStudent = null;
        ResultSet resS = null;
        try {
            listStudent = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getStudentHaveNotJoined(?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specificallyMajorID);
            ps.setInt(2, subjectDetailID);
            resS = ps.executeQuery();
            while (resS.next()) {
                listStudent.add(new Student(
                        resS.getInt("studentID"), 
                        resS.getString("Fullname"), 
                        resS.getInt("sex"), 
                        resS.getString("specificallyMajorName")));
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
    
    
    public boolean createStudyingParticipation(int teachingAssignmentID, int studentID) {
        System.out.println(teachingAssignmentID);
        System.out.println(studentID);
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL createStudyingParticipation(?, ?)}";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teachingAssignmentID);
            ps.setInt(2, studentID);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
    public int getMemberOfClass(int teachingAssignmentID, boolean isTeaching) {
        CallableStatement cs = null;
        Connection connection = null;
        String classroomName = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL getMemberOfClass(?, ?, ?)}";
            cs = connection.prepareCall(query);
            cs.setInt(1, teachingAssignmentID);
            if(isTeaching) {
                cs.setInt(2, 1);
            } else {
                cs.setInt(2, 0);
            }
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean updateClass(Classs o) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL updateClass(?, ?, ?)}";
            cs = connection.prepareCall(query);
            cs.setInt(1, o.getClassID());
            cs.setInt(2, o.getBatch());
            cs.setString(3, o.getDescriptor());
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean createSubject(SubjectDetail o) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL createSubject(?, ?, ?, ?, ?, ?, ?, ?)}";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            cs = connection.prepareCall(query);
            cs.setString(1, o.getSubjectName());
            cs.setString(2, o.getSubjectCode());
            cs.setInt(3, o.getCredit());
            cs.setInt(4, o.getTypeOfSubject());
            cs.setInt(5, o.getFurLough());
            cs.setString(6, LocalDate.parse(o.getLastModified().toString(), format).toString());
            cs.setInt(7, o.getMajorID());
            cs.setString(8, o.getDescriptor());
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public List<SubjectDetail> getSubjectHaveNotDone() {
        PreparedStatement ps = null;
        Connection connection = null;
        List<SubjectDetail> listSubjectDetails = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listSubjectDetails = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getSubjectHaveNotDone()";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            while (resS.next()) {
                listSubjectDetails.add(new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getInt("subjectDetailID"), 
                        resS.getString("subjectName"), 
                        resS.getString("subjectCode"), 
                        resS.getInt("credit"), 
                        resS.getInt("typeOfSubject"), 
                        resS.getInt("furLough"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getInt("majorID"), 
                        resS.getString("descriptor")));
            }
            return listSubjectDetails;
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
        return listSubjectDetails;
    }
    
    
    public boolean updateSubjectHaveNotDone(int subjectDetailID, int specificallyMajorID) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL updateSubjectHaveNotDone(?, ?)}";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            cs = connection.prepareCall(query);
            cs.setInt(1, subjectDetailID);
            cs.setInt(2, specificallyMajorID);
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean createClass(Classs o) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "{CALL createClasses(?, ?, ?, ?, ?)}";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            cs = connection.prepareCall(query);
            cs.setInt(1, o.getMajorID());
            cs.setInt(2, o.getSpecificallyMajorID());
            cs.setInt(3, o.getBatch());
            cs.setString(4, LocalDate.parse(o.getLastModified().toString(), format).toString());
            cs.setString(5, o.getDescriptor());
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;   
    }
    
    public boolean deleteClass(int ClassID) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = "update classes set classes.isDelete = 1 where classes.classID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, ClassID);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    } 
    
    
    public boolean deleteSubject(int subjectID, int subjectDetailID) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           update Subjects set Subjects.isDelete = 1 where Subjects.SubjectID = ?
                           update SubjectsDetail set SubjectsDetail.isDelete = 1 where SubjectsDetail.SubjectDetailID = ?
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectID);
            ps.setInt(2, subjectDetailID);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    } 
    
    public List<SubjectDetail> getSubjectBySParticipation (int studentID) {
        PreparedStatement ps = null;
        Connection connection = null;
        List<SubjectDetail> listSubjectDetails = null;
        ResultSet resS = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            listSubjectDetails = new ArrayList<>();
            connection = DriverManager.getConnection(url);
            String query = "select * from dbo.getSubjectByStudyingParticipations(?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, studentID);
            resS = ps.executeQuery();
            while (resS.next()) {
                listSubjectDetails.add(new SubjectDetail(
                        resS.getInt("subjectID"),
                        resS.getInt("subjectDetailID"), 
                        resS.getString("subjectName"), 
                        resS.getString("subjectCode"), 
                        resS.getInt("credit"), 
                        resS.getInt("typeOfSubject"), 
                        resS.getInt("furLough"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getInt("majorID"), 
                        resS.getString("descriptor")));
            }
            return listSubjectDetails;
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
        return listSubjectDetails;
    }

    public Grade getGradeForSubjectID(int subjectID, int studentID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        Grade o = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           select * from dbo.getGradeForSubjectDetailID(?,?)
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectID);
            ps.setInt(2, studentID);
            resS = ps.executeQuery();
            
            while(resS.next()) {
                o = new Grade(
                        resS.getDouble("lab1"), 
                        resS.getDouble("lab2"), 
                        resS.getDouble("lab3"), 
                        resS.getDouble("lab4"), 
                        resS.getDouble("lab5"), 
                        resS.getDouble("lab6"), 
                        resS.getDouble("lab7"), 
                        resS.getDouble("lab8"), 
                        resS.getDouble("quiz1"), 
                        resS.getDouble("quiz2"), 
                        resS.getDouble("quiz3"), 
                        resS.getDouble("quiz4"), 
                        resS.getDouble("quiz5"), 
                        resS.getDouble("quiz6"), 
                        resS.getDouble("quiz7"), 
                        resS.getDouble("quiz8"), 
                        resS.getDouble("assignment1"), 
                        resS.getDouble("assignment2"), 
                        resS.getDouble("assignmentfinal"));
            }
            return o;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    public boolean updateMark(Grade o, int subjectID, int studentID) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           {CALL updateGrade(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}
                           """;
            cs = connection.prepareCall(query);
            cs.setDouble(1, subjectID);
            cs.setDouble(2, studentID);
            cs.setDouble(3, o.getLab1());
            cs.setDouble(4, o.getLab2());
            cs.setDouble(5, o.getLab3());
            cs.setDouble(6, o.getLab4());
            cs.setDouble(7, o.getLab5());
            cs.setDouble(8, o.getLab6());
            cs.setDouble(9, o.getLab7());
            cs.setDouble(10, o.getLab8());
            
            cs.setDouble(11, o.getQuiz1());
            cs.setDouble(12, o.getQuiz2());
            cs.setDouble(13, o.getQuiz3());
            cs.setDouble(14, o.getQuiz4());
            cs.setDouble(15, o.getQuiz5());
            cs.setDouble(16, o.getQuiz6());
            cs.setDouble(17, o.getQuiz7());
            cs.setDouble(18, o.getQuiz8());

            cs.setDouble(19, o.getAssignment1());
            cs.setDouble(20, o.getAssignment2());
            cs.setDouble(21, o.getAssignmentfinal());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
    public String getTeacherNameBySubjectAndStudent(int subjectDetailID, int studentID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resS = null;
        String res = null;
        try  {
            connection = DriverManager.getConnection(url);
            String query = "SELECT * FROM DBO.getTeacherBySubjectAndStudent(?, ?)";
            ps = connection.prepareStatement(query);
            
            ps.setInt(1, subjectDetailID);
            ps.setInt(2, studentID);
            resS = ps.executeQuery();
            
            while(resS.next()) {
                res = resS.getString("TeacherName");
            }
            return res;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                resS.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }
    
   
    public List<Building> getAllBuilding() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Building> listBuilding = null; 
        boolean foundRecord = false;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try  {
            connection = DriverManager.getConnection(url);
            String query = "select * from v_Building";
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            listBuilding = new ArrayList<>();
            while(resS.next()) {
                foundRecord = true;
                listBuilding.add(new Building(
                        resS.getInt("buildingID"),
                        resS.getString("buildingCode"), 
                        LocalDate.parse(resS.getString("lastModified"), format), 
                        resS.getString("descriptor")));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                resS.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if(!foundRecord) {
            return null;
        }
        return listBuilding;
    }
    
    public boolean createClassroom(Classroom o) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           {CALL createClassroom(?, ?, ?, ?, ?)}
                           """;
            cs = connection.prepareCall(query);
            cs.setInt(1, o.getNumberOfFloor());
            cs.setInt(2, o.getNumberOfRoom());
            cs.setInt(3, o.getBuildingID());
            cs.setInt(4, 1);
            cs.setString(5, o.getDescriptor());
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean deleteSM(int  specificallyMajorID) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           UPDATE SpecificallyMajors SET isDelete = 1 WHERE SpecificallyMajors.specificallyMajorID = ?
                           """;
            cs = connection.prepareCall(query);
            cs.setInt(1, specificallyMajorID);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean updateSM(SpecificallyMajor specificallyMajor) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           UPDATE SpecificallyMajors
                           SET 
                           SpecificallyMajorName = ?,
                           SpecificallyMajorCode = ?,
                           LastModified = getdate(),
                           Descriptor = ?
                           """;
            cs = connection.prepareCall(query);
            cs.setString(1, specificallyMajor.getSpecificallyMajorName());
            cs.setString(2, specificallyMajor.getSpecificallyMajorCode());
            cs.setString(3, specificallyMajor.getDescriptor());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public List<Classroom> getClassroomsByBuildingID (int builingID, boolean isDelete) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resS = null;
        List<Classroom> listClassroom = null; 
        boolean foundRecord = false;
        try  {
            connection = DriverManager.getConnection(url);
            String query = "select * from Classrooms where  BuildingID = ? and isDelete = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, builingID);
            if(isDelete) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            resS = ps.executeQuery();
            listClassroom = new ArrayList<>();
            while(resS.next()) {
                foundRecord = true;
                listClassroom.add(new Classroom(
                        resS.getInt("classroomID"), 
                        resS.getInt("numberOfFloor"), 
                        resS.getInt("numberOfRoom"), 
                        resS.getInt("buildingID"), 
                        resS.getInt("status"), 
                        LocalDate.parse(resS.getString("lastModified")), 
                        resS.getString("descriptor")));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                resS.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if(!foundRecord) {
            return null;
        }
        return listClassroom;
    }
    
    public String getClassroomCodeByClassroomID (int  classroomID) {
        CallableStatement cs = null;
        Connection connection = null;
        String res = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           {CALL getClassroomCode(?, ?)}
                           """;
            cs = connection.prepareCall(query);
            cs.setInt(1, classroomID);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            res = cs.getString("classroomCode");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    
    
    public boolean updateClassroom(Classroom o) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           UPDATE Classrooms SET status = ?, lastModified = getdate(), descriptor = ? where ClassroomID = ?
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, o.getStatus());
            ps.setString(2, o.getDescriptor());
            ps.setInt(3, o.getClassroomID());
            
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
    
    public boolean deleteClassroom(int classroomID) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           UPDATE Classrooms set isDelete = 1 where ClassroomID = ?
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, classroomID);
            
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
    
    public boolean checkEmployeeManager() {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        boolean foundRecord = false;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           SELECT * from Employees where Employees.Title = 1 and isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            
            while(resS.next()) {
                foundRecord = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(!foundRecord) {
            return false;
        }
        return true;
    }
    
    public boolean isManager(int employeeID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        boolean foundRecord = false;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           SELECT * from Employees where Employees.EmployeeID = ? and Title = 1 and isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, employeeID);
            resS = ps.executeQuery();
            
            while(resS.next()) {
                foundRecord = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(!foundRecord) {
            return false;
        }
        return true;
    }
    
    public List<Employee> getListEmployeeFromEmployee() {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        List<Employee> list;
        boolean foundRecord = false;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           select * from Employees where Employees.Title <> 1 and Employees.isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while(resS.next()) {
                foundRecord = true;
                list.add(new Employee(
                        resS.getInt("employeeID"), 
                        resS.getString("employeeName"), 
                        resS.getString("email"), 
                        LocalDate.parse(resS.getString("dateOfBirth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                        resS.getString("phoneNumber"), 
                        resS.getString("provice"), 
                        resS.getInt("title"), 
                        resS.getInt("reportTo"), 
                        LocalDate.parse(resS.getString("lastModified"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                        resS.getString("descriptor")));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(!foundRecord) {
            return null;
        }
        return list;
    }
    
    public List<Employee> getListEmployeeFromAdmin() {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        List<Employee> list;
        boolean foundRecord = false;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           select * from Employees where Employees.isDelete = 0
                           """;
            ps = connection.prepareStatement(query);
            resS = ps.executeQuery();
            list = new ArrayList<>();
            while(resS.next()) {
                foundRecord = true;
                list.add(new Employee(
                        resS.getInt("employeeID"), 
                        resS.getString("employeeName"),
                        resS.getString("email"), 
                        LocalDate.parse(resS.getString("dateOfBirth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                        resS.getString("phoneNumber"), 
                        resS.getString("provice"), 
                        resS.getInt("title"), 
                        resS.getInt("reportTo"), 
                        LocalDate.parse(resS.getString("lastModified"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                        resS.getString("descriptor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(!foundRecord) {
            return null;
        }
        return list;
    }
    
    
    public String getEmployeeManagerName(int employeeID) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resS = null;
        boolean foundRecord = false;
        String employeeName = null; 
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           Select Employees.EmployeeName from Employees where Employees.EmployeeID = ?
                           """;
            ps = connection.prepareStatement(query);
            ps.setInt(1, employeeID);
            resS = ps.executeQuery();
            
            while(resS.next()) {
                foundRecord = true;
                employeeName = resS.getString("EmployeeName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
                ps.close();
                resS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(!foundRecord) {
            return null;
        }
        return employeeName;
    }
    
    public boolean changeRoleEmployee(Employee o) {
        CallableStatement cs = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            String query = """
                           {CALL changeRole(?)}
                           """;
            cs = connection.prepareCall(query);
            cs.setInt(1, o.getEmployeeID());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return true;
    }
    
}
