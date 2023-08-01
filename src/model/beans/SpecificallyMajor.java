package model.beans;

import java.time.LocalDate;

public class SpecificallyMajor {
    private int specificallyMajorID;
    private String specificallyMajorName;
    private String specificallyMajorCode;
    private int majorID;
    private LocalDate lastModified;
    private String descriptor;

    public SpecificallyMajor() {
    }

    public SpecificallyMajor(int specificallyMajorID, String specificallyMajorName, String specificallyMajorCode, int majorID, LocalDate lastModified, String descriptor) {
        this.specificallyMajorID = specificallyMajorID;
        this.specificallyMajorName = specificallyMajorName;
        this.specificallyMajorCode = specificallyMajorCode;
        this.majorID = majorID;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public SpecificallyMajor(String specificallyMajorName, String specificallyMajorCode, String descriptor) {
        this.specificallyMajorName = specificallyMajorName;
        this.specificallyMajorCode = specificallyMajorCode;
        this.descriptor = descriptor;
    }
    
    
    
    

    public int getSpecificallyMajorID() {
        return specificallyMajorID;
    }

    public void setSpecificallyMajorID(int specificallyMajorID) {
        this.specificallyMajorID = specificallyMajorID;
    }

    public String getSpecificallyMajorName() {
        return specificallyMajorName;
    }

    public void setSpecificallyMajorName(String specificallyMajorName) {
        this.specificallyMajorName = specificallyMajorName;
    }

    public String getSpecificallyMajorCode() {
        return specificallyMajorCode;
    }

    public void setSpecificallyMajorCode(String specificallyMajorCode) {
        this.specificallyMajorCode = specificallyMajorCode;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
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
