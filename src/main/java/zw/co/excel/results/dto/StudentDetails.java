/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.excel.results.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author shepherd
 */
public class StudentDetails {

    public StudentDetails() {
    }

    public String studentNumber;
    private String firstName;
    private String lastName;
    private String sex;

    private List<Marks> marks;

    private String remark;
    
    private String partAggr ;

    public String getPartAggr() {
        return partAggr;
    }

    public void setPartAggr(String partAggr) {
        this.partAggr = partAggr;
    }

    /**
     * @return the studentNumber
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * @param studentNumber the studentNumber to set
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * @return the marks
     */
    public List<Marks> getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "StudentDetails{" + "studentNumber=" + studentNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex + ", marks=" + marks + ", remark=" + remark + '}';
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

}
