package model.beans;

import java.time.LocalDate;

public class Major {
    private int majorID;
    private String majorName;
    private String MajorCode;
    private LocalDate lastModified;
    private String descriptor;

    public Major() {
    }

    public Major(int majorID, String majorName, String MajorCode, LocalDate lastModified, String descriptor) {
        this.majorID = majorID;
        this.majorName = majorName;
        this.MajorCode = MajorCode;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCode() {
        return MajorCode;
    }

    public void setMajorCode(String MajorCode) {
        this.MajorCode = MajorCode;
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
