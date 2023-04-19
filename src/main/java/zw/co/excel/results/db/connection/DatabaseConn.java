/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.excel.results.db.connection;

import zw.co.excel.results.dto.StudentCourses;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zw.co.excel.results.dto.Marks;
import zw.co.excel.results.dto.StudentDetails;
import zw.co.excel.results.dto.StudentNumbers;

/**
 *
 * @author shepherd
 */
public class DatabaseConn {

    public Connection conn() {
        try {
            Connection conn = null;
            return conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pengindb", "root", "Root123*");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<StudentCourses> getCourseByProgrammeCode(String programmeCode, String yearOfStudy) {
        List<StudentCourses> studentCoursesList = new ArrayList<StudentCourses>();

        try {
            String SQL_SELECT = "SELECT * FROM tblmoduleprogrammestatus WHERE programmeCode =? AND yearOfStudy =? ORDER BY tblmoduleprogrammestatus.moduleCode ASC";
            //
            System.out.println(SQL_SELECT);

            PreparedStatement preparedStatement = conn().prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, programmeCode);
            preparedStatement.setString(2, yearOfStudy);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                StudentCourses sc = new StudentCourses();
                sc.setProgrammeCode(resultSet.getString("programmeCode"));
                sc.setModuleCode(resultSet.getString("moduleCode"));
                sc.setYearOfStudy(resultSet.getString("yearOfStudy"));
                sc.setSemesterOfStudy(resultSet.getString("semesterOfStudy"));

                studentCoursesList.add(sc);

            }
            return studentCoursesList;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentCoursesList;

    }

    public List<StudentDetails> getExcelData(String programmeCode, String part) {

        List<StudentDetails> sdList = new ArrayList<>();

        for (StudentNumbers student : getStudentsByProgramme(programmeCode, part)) {

            sdList.add(getStudentMarks(programmeCode, part, student));
        }

        return sdList;
    }

    public List<StudentNumbers> getStudentsByProgramme(String programmeCode, String part) {
        List<StudentNumbers> studentNumbersList = new ArrayList<StudentNumbers>();

        try {
            String SQL_SELECT = "SELECT * FROM studentmember join studentprogrammestatus on studentprogrammestatus.studentNumber=studentmember.studentNumber where studentprogrammestatus.programmeCode=? and studentprogrammestatus.yearOfStudy=? and studentprogrammestatus.recordStatus=? ORDER BY studentmember.lastName ASC ";

            PreparedStatement preparedStatement = conn().prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, programmeCode);
            preparedStatement.setString(2, part);
            preparedStatement.setString(3, "CURRENT");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                StudentNumbers sd = new StudentNumbers();
                sd.setStudentNumber(resultSet.getString("studentNumber"));
                sd.setFirstName(resultSet.getString("firstName"));
                sd.setLastName(resultSet.getString("lastName"));
                sd.setSex(resultSet.getString("sex"));

                System.out.println(resultSet.getString("studentNumber"));

                studentNumbersList.add(sd);

            }
            return studentNumbersList;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String getStudentsRemark(String studentNumber, String part) {

        String remark = " ";
        try {
            String SQL_SELECT = "select  * from tbl_student_part_remark where student_number=? and year_of_study =?  ORDER BY tbl_student_part_remark.semester_of_study";

            PreparedStatement preparedStatement = conn().prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, studentNumber);
            preparedStatement.setString(2, part);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                remark = resultSet.getString("remark");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remark;

    }

    public String getStudentsAgr(String studentNumber, String part) {

        String aggr = " ";
        try {
            String SQL_SELECT = "select  * from tbl_student_part_aggregate where student_number=? ";

            PreparedStatement preparedStatement = conn().prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, studentNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                aggr = resultSet.getString("p" + part + "_avg");

            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aggr;

    }

    public StudentDetails getStudentMarks(String programmeCode,
            String part, StudentNumbers student) {

        StudentDetails sd = new StudentDetails();
        try {
            String SQL_SELECT2 = "SELECT * FROM `tblexam_marks`  where  programme_code=" + programmeCode + " and year_of_study=" + part + " and student_number=" + student.getStudentNumber() + "  and id not in (SELECT 	id FROM (SELECT id,ROW_NUMBER() OVER (PARTITION BY module_code ORDER BY module_code) AS row_num FROM tblexam_marks where  programme_code=" + programmeCode + " and year_of_study=" + part + " and student_number=" + student.getStudentNumber() + ") t WHERE row_num > 1) order by tblexam_marks.module_code ASC ;";
            String SQL_SELECT3 = "SELECT * FROM `tblexam_marks`  where  programme_code=? and year_of_study=? and student_number=?  and id not in (SELECT id FROM (SELECT id,ROW_NUMBER() OVER (PARTITION BY module_code ORDER BY module_code) AS row_num FROM tblexam_marks where  programme_code=? and year_of_study=? and student_number=?) t WHERE row_num > 1) order by tblexam_marks.module_code ASC ;";

            String SQL_SELECT = "SELECT * FROM tblexam_marks WHERE programme_code =? AND year_of_study =?  and student_number=? ORDER BY tblexam_marks.module_code ASC";
            PreparedStatement preparedStatement = conn().prepareStatement(SQL_SELECT3);
            preparedStatement.setString(1, programmeCode);
            preparedStatement.setString(2, part);
            preparedStatement.setString(3, student.getStudentNumber());
            preparedStatement.setString(4, programmeCode);
            preparedStatement.setString(5, part);
            preparedStatement.setString(6, student.getStudentNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

//            String SQL_SELECT2 = "SELECT * FROM `tblexam_marks`  where  programme_code=" + programmeCode + " and year_of_study=" + part + " and student_number=" + student.getStudentNumber() + "  and id not in (SELECT 	id FROM (SELECT id,ROW_NUMBER() OVER (PARTITION BY module_code ORDER BY module_code) AS row_num FROM tblexam_marks where  programme_code=" + programmeCode + " and year_of_study=" + part + " and student_number=" + student.getStudentNumber() + ") t WHERE row_num > 1) order by tblexam_marks.module_code ASC ;";
//            System.out.println(SQL_SELECT2);;
//            Statement stmt = conn().createStatement();
//            ResultSet resultSet = stmt.executeQuery(SQL_SELECT2);
            List<Marks> marksList = new ArrayList<>();
            while (resultSet.next()) {

                Marks marks = new Marks();
                marks.setModuleCode(resultSet.getString("module_code"));
                marks.setCa(resultSet.getDouble("coursework_mark"));
                marks.setEx(resultSet.getDouble("exam_mark"));
                marks.setOm(resultSet.getDouble("overall_mark"));
                marks.setSos(resultSet.getString("semester_of_study"));
                marks.setYos(resultSet.getString("year_of_study"));

                marksList.add(marks);
            }

            sd.setStudentNumber(student.getStudentNumber());
            sd.setFirstName(student.getFirstName());
            sd.setLastName(student.getLastName());
            sd.setSex(student.getSex());
            sd.setMarks(marksList);
            //to add remarks here
            sd.setRemark(getStudentsRemark(student.getStudentNumber(), part));
            sd.setPartAggr(getStudentsAgr(student.getStudentNumber(), part));

            return sd;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sd;

    }

    public static void main(String[] args) {

        StudentNumbers sd = new StudentNumbers();
        sd.setStudentNumber("G0231012G");

//        System.out.println(new DatabaseConn().getStudentMarks("GSU-EGS", "1", sd).toString());
//        System.out.println(new DatabaseConn().getStudentsByProgramme("GSU-EGS", "1"));
//         System.out.println(new DatabaseConn().getStudentsByProgramme("EMI", "1"));
// System.out.println(new DatabaseConn().getStudentsAgr("G0231016A", "1"));
        System.out.println(new DatabaseConn().getStudentsRemark("G0231013D", "1"));
    }
}
