package model.beans;

import java.time.LocalDate;

public class Classroom {
    private int classroomID;
    private int numberOfFloor;
    private int numberOfRoom;
    private int buildingID;
    private int status;
    private LocalDate lastModified;
    private String descriptor;

    public Classroom(int classroomID, int numberOfFloor, int numberOfRoom, int buildingID, int status, LocalDate lastModified, String descriptor) {
        this.classroomID = classroomID;
        this.numberOfFloor = numberOfFloor;
        this.numberOfRoom = numberOfRoom;
        this.buildingID = buildingID;
        this.status = status;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public Classroom(int numberOfFloor, int numberOfRoom, int buildingID, String descriptor) {
        this.numberOfFloor = numberOfFloor;
        this.numberOfRoom = numberOfRoom;
        this.buildingID = buildingID;
        this.descriptor = descriptor;
    }

    public Classroom(int classroomID, int status, String descriptor) {
        this.classroomID = classroomID;
        this.status = status;
        this.descriptor = descriptor;
    }
    
    public Classroom() {
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
