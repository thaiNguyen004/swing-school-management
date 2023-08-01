package model.beans;

import java.time.LocalDate;

public class TeachingAssignment {
    private int teachingAssignmentID;
    private int subjectID;
    private int classID;
    private String subjectName;
    private int teacherID;
    private int teachInShift;
    private int classroomID;
    private int semester;
    private String season;
    private int block;
    private LocalDate lastModified; 
    private String descriptor;
    private int isTeaching;

    public TeachingAssignment() {
    }

    public TeachingAssignment(int teachingAssignmentID, int subjectID, int classID, int teacherID, int teachInShift, int classroomID, int semester, String season, int block, LocalDate lastModified, String descriptor, int isTeaching) {
        this.teachingAssignmentID = teachingAssignmentID;
        this.subjectID = subjectID;
        this.classID = classID;
        this.teacherID = teacherID;
        this.teachInShift = teachInShift;
        this.classroomID = classroomID;
        this.semester = semester;
        this.season = season;
        this.block = block;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
        this.isTeaching = isTeaching;
    }

    public TeachingAssignment(int subjectID, int classID, int teacherID, int teachInShift, int classroomID, int semester, String season, int block, LocalDate lastModified, String descriptor) {
        this.subjectID = subjectID;
        this.classID = classID;
        this.teacherID = teacherID;
        this.teachInShift = teachInShift;
        this.classroomID = classroomID;
        this.semester = semester;
        this.season = season;
        this.block = block;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
    }

    public TeachingAssignment(int teachingAssignmentID, int subjectID, int classID, String subjectName, int teacherID, int teachInShift, int classroomID, int semester, String season, int block, LocalDate lastModified, String descriptor, int isTeaching) {
        this.teachingAssignmentID = teachingAssignmentID;
        this.subjectID = subjectID;
        this.classID = classID;
        this.subjectName = subjectName;
        this.teacherID = teacherID;
        this.teachInShift = teachInShift;
        this.classroomID = classroomID;
        this.semester = semester;
        this.season = season;
        this.block = block;
        this.lastModified = lastModified;
        this.descriptor = descriptor;
        this.isTeaching = isTeaching;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    
    
    
    
    
    public int getTeachingAssignmentID() {
        return teachingAssignmentID;
    }

    public void setTeachingAssignmentID(int teachingAssignmentID) {
        this.teachingAssignmentID = teachingAssignmentID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getTeachInShift() {
        return teachInShift;
    }

    public void setTeachInShift(int teachInShift) {
        this.teachInShift = teachInShift;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
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

    public int getIsTeaching() {
        return isTeaching;
    }

    public void setIsTeaching(int isTeaching) {
        this.isTeaching = isTeaching;
    }

    

    
}
