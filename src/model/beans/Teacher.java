package model.beans;

import java.time.LocalDate;

public class Teacher {
    private int teacherID;
    private String teacherName;
    private String email;
    private int sex;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String provice;
    private int majorID;
    private int teacherManagerID;
    private byte [] data;
    private LocalDate lastModified;
    private String descriptor;

    public Teacher() {
    }

    public Teacher(int teacherID, String teacherName, String email, int sex, LocalDate dateOfBirth, String phoneNumber, String provice, int majorID, int teacherManagerID, byte[] data, LocalDate lastModified, String descriptor) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.email = email;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.majorID = majorID;
        this.teacherManagerID = teacherManagerID;
        this.data = data;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Teacher(String teacherName, int sex, LocalDate dateOfBirth, String phoneNumber, String provice, int majorID, int teacherManagerID, byte[] data, LocalDate lastModified, String descriptor) {
        this.teacherName = teacherName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.majorID = majorID;
        this.teacherManagerID = teacherManagerID;
        this.data = data;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Teacher(int teacherID, int sex, String phoneNumber, String provice, int majorID, int teacherManagerID, byte[] data, LocalDate lastModified, String descriptor) {
        this.teacherID = teacherID;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.majorID = majorID;
        this.teacherManagerID = teacherManagerID;
        this.data = data;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    
    
    
    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public int getTeacherManagerID() {
        return teacherManagerID;
    }

    public void setTeacherManagerID(int teacherManagerID) {
        this.teacherManagerID = teacherManagerID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
