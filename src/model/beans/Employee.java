package model.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee implements Serializable{
    private int employeeID;
    private String employeeName;
    private int sex;
    private String email;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String provice;
    private int title;
    private int reportTo;
    private LocalDate lastModified;
    private String descriptor;
    private byte[] photo;

    public Employee() {
        
    }

    public Employee(int employeeID, String employeeName, String email, LocalDate dateOfBirth, String phoneNumber, String provice, int title, int reportTo, LocalDate lastModified, String descriptor) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.title = title;
        this.reportTo = reportTo;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Employee(String employeeName, int sex, LocalDate dateOfBirth, String phoneNumber, String provice, int title, LocalDate lastModified, String descriptor, byte[] photo) {
        this.employeeName = employeeName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.provice = provice;
        this.title = title;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
        this.photo = photo;
    }

    
    
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    
    
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getReportTo() {
        return reportTo;
    }

    public void setReportTo(int reportTo) {
        this.reportTo = reportTo;
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

