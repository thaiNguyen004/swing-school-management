package model.beans;

import java.time.LocalDate;

public class Student{
    private int studentID;
    private String studentName;
    private int sex;
    private int batch;
    private LocalDate dob;
    private String email;
    private String phoneNumber;
    private String provice;
    private String specificallyMajorName;
    private int specificallyMajorID;
    private byte [] photo;
    private LocalDate lastModified;
    private String descriptor;

    public Student() {
    }

    public Student(int studentID, String studentName, int sex, int batch, LocalDate dob, String email, String phoneNumber, String provice, String specificallyMajorName, byte[] photo, LocalDate lastModified, String descriptor) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.sex = sex;
        this.batch = batch;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.specificallyMajorName = specificallyMajorName;
        this.photo = photo;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    
    
    public Student(int studentID, String studentName, int sex, int batch, LocalDate dob, String email, String phoneNumber, String provice, LocalDate lastModified, String descriptor) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.sex = sex;
        this.batch = batch;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Student(int studentID, String studentName, int sex, String specificallyMajorName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.sex = sex;
        this.specificallyMajorName = specificallyMajorName;
    }

    public int getSpecificallyMajorID() {
        return specificallyMajorID;
    }

    public void setSpecificallyMajorID(int specificallyMajorID) {
        this.specificallyMajorID = specificallyMajorID;
    }
    
    
    
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    
    
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getSpecificallyMajorName() {
        return specificallyMajorName;
    }

    public void setSpecificallyMajorName(String specificallyMajorName) {
        this.specificallyMajorName = specificallyMajorName;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
    
    
    
}
