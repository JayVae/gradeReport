package com.grade;

import com.grade.entity.*;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xslf.usermodel.XSLFTableStyle;
//import static org.apache.poi.xslf.usermodel.XSLFTableStyle.TablePartStyle.firstCol;
//import static org.apache.poi.xslf.usermodel.XSLFTableStyle.TablePartStyle.firstRow;

/**
 * Created by dell on 2017/2/24.
 */
public class excalSheet {
    private static String detail_ans = "E:\\gradeReport\\data\\all.xlsx";
    private static String correct_ans = "E:\\gradeReport\\data\\correctAns.xlsx";
    private static String attribute = "E:\\gradeReport\\data\\gradeAttr2.xlsx";

    public static void main(String[] args) throws InvalidFormatException, IOException {
        String[][] correct = getData(correct_ans,0,0,0,23);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < correct[0].length; j++) {
                System.out.println(correct[i][j]+" ");
            }
        }
        excalSheet sheet=new excalSheet();
        List<AnsResult> ans_result = sheet.getAnsResult();
        String fileurl = "data/ans_result.xlsx";
        sheet.writeData(ans_result,fileurl,0,1651,26,26);
    }
    public static void writeData(Object ob,String url,int firstRow,int endRow,int firstCol,int endCol) throws InvalidFormatException,IOException {
        File file = new File(url);
        //FileInputStream excel = new FileInputStream(file);
        OPCPackage excel = OPCPackage.open(file);
        XSSFWorkbook xssf = new XSSFWorkbook(excel);
        XSSFSheet sheet = xssf.getSheetAt(0);
        int rowNum = endRow-firstRow+1;
        int colNum = endCol-firstCol+1;
        /*int delatR = sheet.getLastRowNum()+1-rowNum;
        int delatC = sheet.getRow(0).getLastCellNum()-colNum;*/

        String[][] result = new String[rowNum][colNum];
        for(int row = firstRow, i = 0; row <= endRow; row++,i++){
            for(int col = firstCol, j =0; col <= endCol; col++,j++){
                result[i][j] = String.valueOf(sheet.getRow(row).getCell(col));
            }
        }
    }


    public String[] getExamNo() throws IOException, InvalidFormatException {
        String[][] stuExamNo = getData(detail_ans,0,1651,26,26);
        String[] stuExam1 = new String[1652];
        for (int i = 0; i < stuExam1.length; i++) {
            stuExam1[i] = String.valueOf(Double.valueOf(stuExamNo[i][0]).intValue());
        }
        return stuExam1;
    }

    public static String[] getStuNo() throws InvalidFormatException,IOException{
        String[][] stuNo = getData(detail_ans,0,1651,1,1);//����Excel�е����ݰ��ţ��޸�getData����
        String[] stuNo1 = new String[1652];
        for (int i = 0; i < stuNo.length; i++) {
            stuNo1[i] = String.valueOf(Double.valueOf(stuNo[i][0]).intValue());
        }
        return stuNo1;
    }

    public static String[] getStuName() throws InvalidFormatException,IOException{
        String[][] stuName = getData(detail_ans,0,1651,0,0);//����Excel�е����ݰ��ţ��޸�getData����
        String[] stuName1 = new String[1652];
        for (int i = 0; i < stuName.length; i++) {
            stuName1[i] = stuName[i][0];
        }
        return stuName1;
    }

    public Answer getCorrectAnswer() throws InvalidFormatException, IOException{
        String[][] corAns= getData(correct_ans,0,0,0,23);//��ȷ��
        Answer ans = new Answer();
        ans.setQuesId((short)1);
        ans.setQ1(corAns[0][0]);
        ans.setQ2(corAns[0][1]);
        ans.setQ3(corAns[0][2]);
        ans.setQ4(corAns[0][3]);
        ans.setQ5(corAns[0][4]);
        ans.setQ6(corAns[0][5]);
        ans.setQ7(corAns[0][6]);
        ans.setQ8(corAns[0][7]);
        ans.setQ9(corAns[0][8]);
        ans.setQ10(corAns[0][9]);
        ans.setQ11(corAns[0][10]);
        ans.setQ12(corAns[0][11]);
        ans.setQ13(corAns[0][12]);
        ans.setQ14(corAns[0][13]);
        ans.setQ15(corAns[0][14]);
        ans.setQ16(corAns[0][15]);
        ans.setQ17(corAns[0][16]);
        ans.setQ18(corAns[0][17]);
        ans.setQ19(corAns[0][18]);
        ans.setQ20(String.valueOf(Double.valueOf(corAns[0][19]).intValue()));
        ans.setQ21(String.valueOf(Double.valueOf(corAns[0][20]).intValue()));
        ans.setQ22(String.valueOf(Double.valueOf(corAns[0][21]).intValue()));
        ans.setQ23(corAns[0][22]);
        ans.setQ24(corAns[0][23]);
        return ans;
    }

    public List<Student> getStudent() throws InvalidFormatException,IOException{
        String[] stuNos = getStuNo();
        String[] stuNames = getStuName();
        String[] stuExamNos = getExamNo();
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < stuNos.length; i++) {
            Student student = new Student();
            student.setStudentId(String.valueOf(Integer.valueOf(stuNos[i])));
            student.setStuName(stuNames[i]);
            student.setStuExamNo(stuExamNos[i]);
            students.add(student);
        }
        return students;
    }

    public List<DetailAns> getDetailAns() throws InvalidFormatException, IOException{
        String[][] details = getData(detail_ans,0,1651,2,25);
        String[] stuNo = getStuNo();
        List<DetailAns> detailAnss = new ArrayList<DetailAns>();
        for (int i = 0; i < stuNo.length; i++) {
            DetailAns detailAns = new DetailAns();
            detailAns.setDansId(i+1);
            detailAns.setStudentId(String.valueOf(Integer.valueOf(stuNo[i])));
            detailAns.setQuestion1(details[i][0]);
            detailAns.setQuestion2(details[i][1]);
            detailAns.setQuestion3(details[i][2]);
            detailAns.setQuestion4(details[i][3]);
            detailAns.setQuestion5(details[i][4]);
            detailAns.setQuestion6(details[i][5]);
            detailAns.setQuestion7(details[i][6]);
            detailAns.setQuestion8(details[i][7]);
            detailAns.setQuestion9(details[i][8]);
            detailAns.setQuestion10(details[i][9]);
            detailAns.setQuestion11(details[i][10]);
            detailAns.setQuestion12(details[i][11]);
            detailAns.setQuestion13(details[i][12]);
            detailAns.setQuestion14(details[i][13]);
            detailAns.setQuestion15(details[i][14]);
            detailAns.setQuestion16(details[i][15]);
            detailAns.setQuestion17(details[i][16]);
            detailAns.setQuestion18(details[i][17]);
            detailAns.setQuestion19(details[i][18]);
            detailAns.setQuestion20(details[i][19]);
            detailAns.setQuestion21(details[i][20]);
            detailAns.setQuestion22(details[i][21]);
            detailAns.setQuestion23(details[i][22]);
            detailAns.setQuestion24(details[i][23]);
            detailAnss.add(detailAns);
        }
        return detailAnss;
    }

    public List<AnsResult> getAnsResult() throws InvalidFormatException,IOException{
        String[][] correct = getData(correct_ans,0,0,0,23);//��ȷ��
        String[][] details = getData(detail_ans,0,1651,2,25);//ѧ����
        int[][] ansRes = new int[details.length][details[0].length];
        float[] totalGrade = new float[details.length];

        for (int i = 0; i < details.length; i++) {
            float total = 0;

            for (int j = 0; j < details[0].length; j++) {
                try {
                    if (j == 19 || j == 20) {

                        if (Float.valueOf(details[i][j]) >= 1.0f) {
                            ansRes[i][j] = 1;
                        } else {
                            ansRes[i][j] = 0;
                        }
                        total += Float.valueOf(details[i][j]);
                    } else {
                        if (j == 21) {
                            if (Float.valueOf(details[i][j]) >= 1.5f) {
                                ansRes[i][j] = 1;
                            } else {
                                ansRes[i][j] = 0;
                            }
                            total += Float.valueOf(details[i][j]);
                        } else {
                            if (details[i][j].equals(correct[0][j])) {
                                ansRes[i][j] = 1;
                            } else {
                                ansRes[i][j] = 0;
                            }
                            total += ansRes[i][j];
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            totalGrade[i] = total;
        }
        String[] stuNos = getStuNo();
        List<AnsResult> ansResults = new ArrayList<AnsResult>();
        for (int i = 0; i < ansRes.length; i++) {
            AnsResult ansResult = new AnsResult();
            ansResult.setAnsResId(i+1);
            ansResult.setStudentId(String.valueOf(Integer.valueOf(stuNos[i])));
            ansResult.setTotalGrade(Float.valueOf(totalGrade[i]));
            ansResult.setQuestion1(String.valueOf(ansRes[i][0]));
            ansResult.setQuestion2(String.valueOf(ansRes[i][1]));
            ansResult.setQuestion3(String.valueOf(ansRes[i][2]));
            ansResult.setQuestion4(String.valueOf(ansRes[i][3]));
            ansResult.setQuestion5(String.valueOf(ansRes[i][4]));
            ansResult.setQuestion6(String.valueOf(ansRes[i][5]));
            ansResult.setQuestion7(String.valueOf(ansRes[i][6]));
            ansResult.setQuestion8(String.valueOf(ansRes[i][7]));
            ansResult.setQuestion9(String.valueOf(ansRes[i][8]));
            ansResult.setQuestion10(String.valueOf(ansRes[i][9]));
            ansResult.setQuestion11(String.valueOf(ansRes[i][10]));
            ansResult.setQuestion12(String.valueOf(ansRes[i][11]));
            ansResult.setQuestion13(String.valueOf(ansRes[i][12]));
            ansResult.setQuestion14(String.valueOf(ansRes[i][13]));
            ansResult.setQuestion15(String.valueOf(ansRes[i][14]));
            ansResult.setQuestion16(String.valueOf(ansRes[i][15]));
            ansResult.setQuestion17(String.valueOf(ansRes[i][16]));
            ansResult.setQuestion18(String.valueOf(ansRes[i][17]));
            ansResult.setQuestion19(String.valueOf(ansRes[i][18]));
            ansResult.setQuestion20(String.valueOf(ansRes[i][19]));
            ansResult.setQuestion21(String.valueOf(ansRes[i][20]));
            ansResult.setQuestion22(String.valueOf(ansRes[i][21]));
            ansResult.setQuestion23(String.valueOf(ansRes[i][22]));
            ansResult.setQuestion24(String.valueOf(ansRes[i][23]));
            ansResults.add(ansResult);
        }
        return ansResults;
    }

    public List<StuAttrGrade> getStuAttrGrade() throws InvalidFormatException,IOException{
        String[][] attrGrade = getData(attribute,0,1651,1,7);//ÿ��ѧ�������Ե÷֣��޸Ĳ���
        String[] stuNos = getStuNo();
        List<StuAttrGrade> stuAttrGrades = new ArrayList<StuAttrGrade>();
        for (int i = 0; i < stuNos.length; i++) {
            StuAttrGrade stuAttrGrade = new StuAttrGrade();
            stuAttrGrade.setSagId(i+1);
            stuAttrGrade.setStudentId(stuNos[i]);
            stuAttrGrade.setAttr1Grade(Float.parseFloat(attrGrade[i][0]));
            stuAttrGrade.setAttr2Grade(Float.parseFloat(attrGrade[i][1]));
            stuAttrGrade.setAttr3Grade(Float.parseFloat(attrGrade[i][2]));
            stuAttrGrade.setAttr4Grade(Float.parseFloat(attrGrade[i][3]));
            stuAttrGrade.setAttr5Grade(Float.parseFloat(attrGrade[i][4]));
            stuAttrGrade.setAttr6Grade(Float.parseFloat(attrGrade[i][5]));
            stuAttrGrade.setAttr7Grade(Float.parseFloat(attrGrade[i][6]));
            stuAttrGrades.add(stuAttrGrade);
        }
        return stuAttrGrades;
    }

    public static String[][] getData(String fileUrl,int firstRow,int endRow,int firstCol,int endCol) throws InvalidFormatException, IOException{
        File file = new File(fileUrl);
        //FileInputStream excel = new FileInputStream(file);
        OPCPackage excel = OPCPackage.open(file);
        XSSFWorkbook xssf = new XSSFWorkbook(excel);
        XSSFSheet sheet = xssf.getSheetAt(0);
        int rowNum = endRow-firstRow+1;
        int colNum = endCol-firstCol+1;
        /*int delatR = sheet.getLastRowNum()+1-rowNum;
        int delatC = sheet.getRow(0).getLastCellNum()-colNum;*/
        String[][] result = new String[rowNum][colNum];
        for(int row = firstRow, i = 0; row <= endRow; row++,i++){
            for(int col = firstCol, j =0; col <= endCol; col++,j++){
                result[i][j] = String.valueOf(sheet.getRow(row).getCell(col));
            }
        }
        return result;
    }


}
