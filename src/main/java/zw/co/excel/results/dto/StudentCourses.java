/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.excel.results.dto;

/**
 *
 * @author shepherd
 */
public class StudentCourses {

    private String programmeCode;
    private String moduleCode;
    private String yearOfStudy;
    private String semesterOfStudy;

    public StudentCourses() {
    }
    
    
    

    /**
     * @return the programmeCode
     */
    public String getProgrammeCode() {
        return programmeCode;
    }

    /**
     * @param programmeCode the programmeCode to set
     */
    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    /**
     * @return the moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @param moduleCode the moduleCode to set
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * @return the yearOfStudy
     */
    public String getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * @param yearOfStudy the yearOfStudy to set
     */
    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * @return the semesterOfStudy
     */
    public String getSemesterOfStudy() {
        return semesterOfStudy;
    }

    /**
     * @param semesterOfStudy the semesterOfStudy to set
     */
    public void setSemesterOfStudy(String semesterOfStudy) {
        this.semesterOfStudy = semesterOfStudy;
    }

    public StudentCourses(String programmeCode, String moduleCode, String yearOfStudy, String semesterOfStudy) {
        this.programmeCode = programmeCode;
        this.moduleCode = moduleCode;
        this.yearOfStudy = yearOfStudy;
        this.semesterOfStudy = semesterOfStudy;
    }

    @Override
    public String toString() {
        return "StudentCourses{" + "programmeCode=" + programmeCode + ", moduleCode=" + moduleCode + ", yearOfStudy=" + yearOfStudy + ", semesterOfStudy=" + semesterOfStudy + '}';
    }
    
    

}
