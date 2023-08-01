package model.beans;

import java.time.LocalDate;

public class Building {
    private int buildingID;
    private String buidlingCode;
    private LocalDate lastModified;
    private String descriptor;

    public Building(int buildingID, String buidlingCode, LocalDate lastModified, String descriptor) {
        this.buildingID = buildingID;
        this.buidlingCode = buidlingCode;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Building() {
    }

    public Building(String buidlingCode, LocalDate lastModified, String descriptor) {
        this.buidlingCode = buidlingCode;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuidlingCode() {
        return buidlingCode;
    }

    public void setBuidlingCode(String buidlingCode) {
        this.buidlingCode = buidlingCode;
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
