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
public class StudentNumbers {

    public StudentNumbers() {
    }

    public String studentNumber;
    private String firstName;
    private String lastName;
    private String sex;

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
