package model.ultilities;

import java.time.LocalDate;

public class PutStudent {
    private int studentID;
    private String StudentName;
    private int sex;
    private LocalDate dob;
    private String phoneNumber;
    private String provice;
    private byte [] photo;
    private int batch ;
    private int specificallyMajorID;
    private LocalDate lastModified;
    private String descriptor;

    public PutStudent() {
    }

    public PutStudent(String StudentName, int sex, LocalDate dob, String phoneNumber, String provice, byte[] photo, int batch, int specificallyMajorID, LocalDate lastModified, String descriptor) {
        this.StudentName = StudentName;
        this.sex = sex;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.photo = photo;
        this.batch = batch;
        this.specificallyMajorID = specificallyMajorID;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    
    public PutStudent(int studentID, String StudentName, int sex, LocalDate dob, String phoneNumber, String provice, byte[] photo, int batch, int specificallyMajorID, LocalDate lastModified, String descriptor) {
        this.studentID = studentID;
        this.StudentName = StudentName;
        this.sex = sex;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.photo = photo;
        this.batch = batch;
        this.specificallyMajorID = specificallyMajorID;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getSpecificallyMajorID() {
        return specificallyMajorID;
    }

    public void setSpecificallyMajorID(int specificallyMajorID) {
        this.specificallyMajorID = specificallyMajorID;
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
