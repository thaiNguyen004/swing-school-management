package model.beans;

import java.time.LocalDate;

public class Classs {
    private int classID;
    private String classCode;
    private int majorID;
    private int specificallyMajorID;
    private int batch;
    private LocalDate lastModified;
    private String descriptor;

    public Classs(int classID, String classCode, int majorID, int specificallyMajorID, int batch, LocalDate lastModified, String descriptor) {
        this.classID = classID;
        this.classCode = classCode;
        this.majorID = majorID;
        this.specificallyMajorID = specificallyMajorID;
        this.batch = batch;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Classs() {
    }

    public Classs(int classID, int batch, String descriptor) {
        this.classID = classID;
        this.batch = batch;
        this.descriptor = descriptor;
    }

    public Classs(int majorID, int specificallyMajorID, int batch, LocalDate lastModified, String descriptor) {
        this.majorID = majorID;
        this.specificallyMajorID = specificallyMajorID;
        this.batch = batch;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }
    
    

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
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
