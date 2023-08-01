package model.beans;

import java.time.LocalDate;

public class SubjectDetail {
    private int subjectID;
    private int subjectDetailID;
    private String subjectName;
    private String subjectCode;
    private int credit;
    private int typeOfSubject;
    private int furLough;
    private LocalDate lastModified;
    private int majorID;
    private int specificallyMajorID;
    private String descriptor;

    public SubjectDetail(int subjectID, String subjectName, String subjectCode, int credit, int typeOfSubject, int furLough, LocalDate lastModified, int majorID, String descriptor) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credit = credit;
        this.typeOfSubject = typeOfSubject;
        this.furLough = furLough;
        this.lastModified = lastModified;
        this.majorID = majorID;
        this.descriptor = descriptor;
    }

    public SubjectDetail(String subjectName, String subjectCode, int credit, int typeOfSubject, int furLough, LocalDate lastModified, int majorID, String descriptor) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credit = credit;
        this.typeOfSubject = typeOfSubject;
        this.furLough = furLough;
        this.lastModified = lastModified;
        this.majorID = majorID;
        this.descriptor = descriptor;
    }

    public SubjectDetail(int subjectID, int subjectDetailID, String subjectName, String subjectCode, int credit, int typeOfSubject, int furLough, LocalDate lastModified, int majorID, String descriptor) {
        this.subjectID = subjectID;
        this.subjectDetailID = subjectDetailID;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credit = credit;
        this.typeOfSubject = typeOfSubject;
        this.furLough = furLough;
        this.lastModified = lastModified;
        this.majorID = majorID;
        this.descriptor = descriptor;
    }
    
    
    
    
    public SubjectDetail(int subjectID, int subjectDetailID, String subjectName, String subjectCode, int credit, int typeOfSubject, int furLough, LocalDate lastModified, int majorID, int specificallyMajorID, String descriptor) {
        this.subjectID = subjectID;
        this.subjectDetailID = subjectDetailID;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credit = credit;
        this.typeOfSubject = typeOfSubject;
        this.furLough = furLough;
        this.lastModified = lastModified;
        this.majorID = majorID;
        this.specificallyMajorID = specificallyMajorID;
        this.descriptor = descriptor;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectDetailID() {
        return subjectDetailID;
    }

    public void setSubjectDetailID(int subjectDetailID) {
        this.subjectDetailID = subjectDetailID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTypeOfSubject() {
        return typeOfSubject;
    }

    public void setTypeOfSubject(int typeOfSubject) {
        this.typeOfSubject = typeOfSubject;
    }

    public int getFurLough() {
        return furLough;
    }

    public void setFurLough(int furLough) {
        this.furLough = furLough;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public int getSpecificallyMajorID() {
        return specificallyMajorID;
    }

    public void setSpecificallyMajorID(int specificallyMajorID) {
        this.specificallyMajorID = specificallyMajorID;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    
    
    
}
