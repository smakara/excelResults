/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.excel.results;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zw.co.excel.results.db.connection.DatabaseConn;
import zw.co.excel.results.dto.StudentCourses;
import zw.co.excel.results.dto.StudentDetails;

/**
 *
 * @author shepherd
 */
public class GenerateExcel {

    DatabaseConn db = new DatabaseConn();

    public static void main(String[] args) {
        try {
            new GenerateExcel().generateExcel("GSU-EGS", "1");
        } catch (IOException ex) {
            Logger.getLogger(GenerateExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void  ExcelGen(String programmeCode, String yearofstudy) {
        try {
            new GenerateExcel().generateExcel(programmeCode, yearofstudy);
        } catch (IOException ex) {
            Logger.getLogger(GenerateExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateExcel(String programmeCode, String yearofstudy) throws FileNotFoundException, IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Exam Results");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("No.");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("STUDENT’S FULL NAME");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("G");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("REG NUMBER");
        headerCell.setCellStyle(headerStyle);

        String[] marksHeaders = {"CA", "EX", "OM"};

        List<StudentCourses> all_courses = db.getCourseByProgrammeCode(programmeCode,yearofstudy);

        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 4;
        int lastCol = 6;
        int columncount = 4;
        int counterLastCourse = 0;
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));

        for (int i = 0; i < all_courses.size(); i++) { //sem1 courses loop
            if (all_courses.get(i).getSemesterOfStudy().compareToIgnoreCase("1") == 0) {
                headerCell = header.createCell(columncount);
                headerCell.setCellValue(all_courses.get(i).getModuleCode());
                headerCell.setCellStyle(headerStyle);
                columncount = columncount + 3;
                counterLastCourse = i;

                firstCol = lastCol + 1;
                lastCol = firstCol + 2;
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }

        }

        //re-initiate  the variables for 2nd semester courses
        firstRow = 1;
        lastRow = 1;
        firstCol = 4;
        lastCol = 6;
        columncount = 4;
        header = sheet.createRow(1);
        int column2count = 4;
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
        for (int i = 0; i < all_courses.size(); i++) {//sem2 courses loop
            if (all_courses.get(i).getSemesterOfStudy().compareToIgnoreCase("2") == 0) {
                headerCell = header.createCell(column2count);
                headerCell.setCellValue(all_courses.get(i).getModuleCode());
                headerCell.setCellStyle(headerStyle);
                column2count = column2count + 3;

                firstCol = lastCol + 1;
                lastCol = firstCol + 2;
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }
        }

//remove merged Regions after column headers
//        CellRangeAddress mergedRegionToRemove = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
//        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
//            if (mergedRegionToRemove.equals(mergedRegion)) {
//                sheet.removeMergedRegion(i);
//            }
//        }
        //Part AGGR
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 22, 22));
//        header = sheet.createRow(1);
//        headerCell = header.createCell(22);
//        headerCell.setCellValue("PART AGGR");
        //Part AGGR
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 23, 23));
//        headerCell = header.createCell(23);
//        headerCell.setCellValue("REMARKS");
        //marks headers
        int marksHeadersColumnCount = 4;
        header = sheet.createRow(2);

        for (int i = 0; i < all_courses.size(); i++) {//loop for putting marks headers i.e (ca,ex,om) on all sem courses 
            if (all_courses.get(i).getSemesterOfStudy().compareToIgnoreCase("1") == 0) {

                for (int x = 0; x < marksHeaders.length; x++) {
                    headerCell = header.createCell(marksHeadersColumnCount);
                    headerCell.setCellValue(marksHeaders[x]);
                    marksHeadersColumnCount = marksHeadersColumnCount + 1;

                }
            }
        }

        //Excel Row Data
        int rownum = 3;
        int firstRowXaxis = 3;
        int lastRowXaxis = firstRowXaxis + 1;
        int firstColXaxis = 1;
        int lastColXaxis = 1;

        List<StudentCourses> sc = new ArrayList<>();
        Row rowData = sheet.createRow(rownum);
        Cell cell = rowData.createCell(1);
        //"GSU-EGS", "1"   "EMI", "1"
        List<StudentDetails> xlDataList = db.getExcelData(programmeCode,yearofstudy);
        System.out.println(xlDataList.size());

        sheet.addMergedRegion(new CellRangeAddress(firstRowXaxis, lastRowXaxis, firstColXaxis, lastColXaxis));
        rowData = sheet.createRow(firstRowXaxis);
        cell = rowData.createCell(1);
        cell.setCellValue("tamuka");

        for (int i = 0; i < xlDataList.size(); i++) {
            rowData = sheet.createRow(rownum);

            //student name
            cell = rowData.createCell(1);
            cell.setCellValue(xlDataList.get(i).getLastName() + " " + xlDataList.get(i).getFirstName());

            //student gender
            sheet.addMergedRegion(new CellRangeAddress(firstRowXaxis, lastRowXaxis, 2, 2));
            cell = rowData.createCell(2);
            cell.setCellValue(xlDataList.get(i).getSex());

            //student StudentNumber
            sheet.addMergedRegion(new CellRangeAddress(firstRowXaxis, lastRowXaxis, 3, 3));
            cell = rowData.createCell(3);
            cell.setCellValue(xlDataList.get(i).getStudentNumber());

            //student marks
            System.out.println(xlDataList.get(i).getMarks());

            int marksCellNum = 4;

            System.out.println("######===== " + xlDataList.get(i).getMarks().toString());

            for (int x = 0; x < xlDataList.get(i).getMarks().size(); x++) { //semester 1 marks

                if (xlDataList.get(i).getMarks().get(x).getSos().compareTo("1") == 0) {

                    for (int y = 0; y < marksHeaders.length; y++) {

                        if (marksHeaders[y].compareToIgnoreCase("CA") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getCa());
                            marksCellNum = marksCellNum + 1;
                        }

                        if (marksHeaders[y].compareToIgnoreCase("EX") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getEx());
                            marksCellNum = marksCellNum + 1;
                        }

                        if (marksHeaders[y].compareToIgnoreCase("OM") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getOm());
                            marksCellNum = marksCellNum + 1;
                        }

                    }
                }

            }

//            ==================== semester 2==================== 
            rownum = rownum + 1;
            rowData = sheet.createRow(rownum);
            marksCellNum = 4;
         ;
            for (int x = 0; x < xlDataList.get(i).getMarks().size(); x++) { //semester 2 marks

                if (xlDataList.get(i).getMarks().get(x).getSos().compareTo("2") == 0) {

                    for (int y = 0; y < marksHeaders.length; y++) {

                        if (marksHeaders[y].compareToIgnoreCase("CA") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getCa());
                            marksCellNum = marksCellNum + 1;
                        }

                        if (marksHeaders[y].compareToIgnoreCase("EX") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getEx());
                            marksCellNum = marksCellNum + 1;
                        }

                        if (marksHeaders[y].compareToIgnoreCase("OM") == 0) {
                            cell = rowData.createCell(marksCellNum);
                            cell.setCellValue(xlDataList.get(i).getMarks().get(x).getOm());
                            marksCellNum = marksCellNum + 1;
                        }

                    }
                }

            }

//            /            ==================== semester 2==================== 
            rownum = rownum + 1;
            firstRowXaxis = firstRowXaxis + 2;
            lastRowXaxis = firstRowXaxis + 1;
            sheet.addMergedRegion(new CellRangeAddress(firstRowXaxis, lastRowXaxis, firstColXaxis, lastColXaxis));
            rowData = sheet.createRow(firstRowXaxis);
        }
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);

        workbook.write(outputStream);

        workbook.close();

    }

}
