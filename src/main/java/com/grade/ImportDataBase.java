package com.grade;

import com.grade.entity.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/23.
 */
public class ImportDataBase {
    public static void main(String[] args) throws IOException, InvalidFormatException, SQLException, ClassNotFoundException {
        excalSheet sheet = new excalSheet();
        Answer correct = sheet.getCorrectAnswer();
//        importAnswer(correct);
        importStudent(sheet.getStudent());
        importAnsResult(sheet.getAnsResult());
        importDetailAns(sheet.getDetailAns());
        importStuAttrGrade(sheet.getStuAttrGrade());
    }

    /**
     * 将学生表导入到数据库
     * @param studentList
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void importStudent(List<Student> studentList) throws SQLException, ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.OpenDb();
        String sqll = "delete from student";
        dbHelper.updateQuery(sqll);
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            String sql = "insert into student (student_id, stu_name) values "+"("+"'"+student.getStudentId()+"'"+","+"'"+student.getStuName()+"'"+")";
            dbHelper.updateQuery(sql);
        }
        dbHelper.closeDb();
    }
    /**
     * 将answer导入到数据库
     * @param answerEntity
     * @throws SQLException
     * @throws ClassNotFoundException
     */


    public static void importAnswer(Answer answerEntity) throws SQLException, ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.OpenDb();
        String sqll = "delete from answer";
        dbHelper.updateQuery(sqll);
        String sql = "insert into answer (ques_id, q1, q2, q3,q4, q5,q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24) values "+
                "(" +"'"+ answerEntity.getQuesId()+"'" + "," +"'"+ answerEntity.getQ1()+"'" +"," +"'"+ answerEntity.getQ2()+"'" + "," +"'"+ answerEntity.getQ3() +"'"+","+"'" + answerEntity.getQ4()+"'"+ "," +"'"+ answerEntity.getQ5()+"'" + "," +"'"+ answerEntity.getQ6()+"'"+ ","+"'" + answerEntity.getQ7()+"'"+
                "," +"'"+ answerEntity.getQ8()+"'"+ ","+"'" + answerEntity.getQ9()+"'"+ "," +"'"+ answerEntity.getQ10()+"'"+ "," +"'"+ answerEntity.getQ11()+"'"+ "," +"'"+ answerEntity.getQ12()+"'"+
                ","+"'" + answerEntity.getQ13()+"'"+ ","+"'" + answerEntity.getQ14()+"'"+ "," +"'"+ answerEntity.getQ15()+"'"+ "," +"'"+ answerEntity.getQ16()+"'"+ ","+"'" + answerEntity.getQ17()+"'"+
                "," +"'"+ answerEntity.getQ18()+"'"+ ","+"'" + answerEntity.getQ19()+"'"+ ","+"'" +  answerEntity.getQ20()+"'"+ "," +"'"+  answerEntity.getQ21()+"'"+ ","+"'" +  answerEntity.getQ22()+"'"+ "," +"'"+  answerEntity.getQ23()+"'"+ "," +"'"+  answerEntity.getQ24()+"'"+")";
        dbHelper.updateQuery(sql);
        dbHelper.closeDb();
    }

    /**
     * 将DetailAns导入数据库，学生原始答案
     * @param detailAnsList
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void importDetailAns(List<DetailAns> detailAnsList) throws SQLException, ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.OpenDb();
        String sqll = "delete from detail_ans";
        dbHelper.updateQuery(sqll);
        int index = 0;
        for (int i = 0; i < detailAnsList.size(); i++) {
            DetailAns detalAns = detailAnsList.get(i);
//            check_ans(detalAns);
            String sql = "insert into  detail_ans(dans_id, student_id, question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14, question15, question16, question17, question18, question19, question20, question21, question22, question23, question24) values"+
                    "(" +  "'"+detalAns.getDansId() +  "'"+","+"'" + detalAns.getStudentId()+ "'"+","+"'"+detalAns.getQuestion1() + "'"+","+"'" + detalAns.getQuestion2() +  "'"+","+"'" + detalAns.getQuestion3() + "'"+","+"'" + detalAns.getQuestion4()+  "'"+","+"'" + detalAns.getQuestion5()+  "'"+","+"'" + detalAns.getQuestion6()+  "'"+","+"'" + detalAns.getQuestion7()+
                     "'"+","+"'" + detalAns.getQuestion8()+  "'"+","+"'" + detalAns.getQuestion9()+  "'"+","+"'" + detalAns.getQuestion10()+  "'"+","+"'" + detalAns.getQuestion11()+  "'"+","+"'" + detalAns.getQuestion12()+
                     "'"+","+"'" + detalAns.getQuestion13()+  "'"+","+"'" + detalAns.getQuestion14()+  "'"+","+"'" + detalAns.getQuestion15()+  "'"+","+"'" + detalAns.getQuestion16()+  "'"+","+"'" + detalAns.getQuestion17()+
                     "'"+","+"'" + detalAns.getQuestion18()+  "'"+","+"'" + detalAns.getQuestion19()+  "'"+","+"'" +   detalAns.getQuestion20()+  "'"+","+"'" +   detalAns.getQuestion21()+  "'"+","+"'" +  detalAns.getQuestion22()+  "'"+","+"'" +   detalAns.getQuestion23()+  "'"+ ","+"'" +   detalAns.getQuestion24()+ "'"+")";
            dbHelper.updateQuery(sql);
            System.out.println(index++);;
        }
        dbHelper.closeDb();
    }

    private static void check_ans(DetailAns detalAns) {
        String[] s = new String[]{"A", "B", "C", "D"};
        Random random = new Random();

        if (detalAns.getQuestion1().length() > 2 || detalAns.getQuestion1() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion1(s[index]);
        }
        if (detalAns.getQuestion2().length() > 2 || detalAns.getQuestion2() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion2(s[index]);
        }
        if (detalAns.getQuestion3().length() > 2 || detalAns.getQuestion3() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion3(s[index]);
        }
        if (detalAns.getQuestion4().length() > 2 || detalAns.getQuestion4() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion4(s[index]);
        }
        if (detalAns.getQuestion5().length() > 2 || detalAns.getQuestion5() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion5(s[index]);
        }
        if (detalAns.getQuestion6().length() > 2 || detalAns.getQuestion6() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion6(s[index]);
        }
        if (detalAns.getQuestion7().length() > 2 || detalAns.getQuestion7() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion7(s[index]);
        }
        if (detalAns.getQuestion8().length() > 2 || detalAns.getQuestion8() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion8(s[index]);
        }
        if (detalAns.getQuestion9().length() > 2 || detalAns.getQuestion9() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion9(s[index]);
        }
        if (detalAns.getQuestion10().length() > 2 || detalAns.getQuestion10() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion10(s[index]);
        }
        if (detalAns.getQuestion11().length() > 2 || detalAns.getQuestion11() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion11(s[index]);
        }
        if (detalAns.getQuestion12().length() > 2 || detalAns.getQuestion12() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion12(s[index]);
        }
        if (detalAns.getQuestion13().length() > 2 || detalAns.getQuestion13() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion13(s[index]);
        }
        if (detalAns.getQuestion14().length() > 2 || detalAns.getQuestion14() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion14(s[index]);
        }
        if (detalAns.getQuestion15().length() > 2 || detalAns.getQuestion15() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion15(s[index]);
        }
        if (detalAns.getQuestion16().length() > 2 || detalAns.getQuestion16() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion16(s[index]);
        }
        if (detalAns.getQuestion17().length() > 2 || detalAns.getQuestion17() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion17(s[index]);
        }
        if (detalAns.getQuestion18().length() > 2 || detalAns.getQuestion18() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion1(s[index]);
        }
        if (detalAns.getQuestion19().length() > 2 || detalAns.getQuestion19() == "无") {
            int index = random.nextInt(s.length);
            detalAns.setQuestion1(s[index]);
        }
    }

    /**
     * 将AnsResult导入数据库，学生处理过的答案
     * @param ansResultList
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void importAnsResult(List<AnsResult> ansResultList) throws SQLException, ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.OpenDb();
        String sqll = "delete from ans_result";
        dbHelper.updateQuery(sqll);
        for (int i = 0; i < ansResultList.size(); i++) {
            AnsResult ansResult = ansResultList.get(i);
            String sql = "insert into  ans_result(ans_res_id, student_id, question1, question2, question3,question4,question5,question6,  question7, question8, question9, question10, question11, question12, question13, question14, question15, question16, question17, question18, question19, question20, question21, question22, question23, question24, total_grade) values"+
                    "("  + "'"+ansResult.getAnsResId()+"'"+","+"'"+ansResult.getStudentId()+"'"+","+"'"+ansResult.getQuestion1()+"'"+","+"'"+ansResult.getQuestion2()+"'"+","+"'"+ansResult.getQuestion3()+"'"+","+"'"+ansResult.getQuestion4()+"'"+","+"'"+ansResult.getQuestion5()+"'"+","+"'"+ansResult.getQuestion6()+"'"+","+"'"+ansResult.getQuestion7()+
                     "'"+"," +"'"+ ansResult.getQuestion8()+"'"+","+"'"+ansResult.getQuestion9()+"'"+","+"'"+ansResult.getQuestion10()+"'"+","+"'"+ansResult.getQuestion11()+"'"+","+"'"+ansResult.getQuestion12()+
                     "'"+"," +"'"+ ansResult.getQuestion13()+"'"+","+"'"+ansResult.getQuestion14()+"'"+","+"'"+ansResult.getQuestion15()+"'"+","+"'"+ansResult.getQuestion16()+"'"+","+"'"+ansResult.getQuestion17()+
                     "'"+"," +"'"+ ansResult.getQuestion18()+"'"+","+"'"+ansResult.getQuestion19()+"'"+","+"'"+ansResult.getQuestion20()+"'"+","+"'"+ansResult.getQuestion21()+"'"+","+"'"+ansResult.getQuestion22()+"'"+","+"'"+ansResult.getQuestion23()+"'"+","+"'"+ansResult.getQuestion24()+"'"+","+ansResult.getTotalGrade()+")";
            dbHelper.updateQuery(sql);
        }
        dbHelper.closeDb();

    }

    /**
     * 导入学生属性得分表
     * @param stuAttrGradeList
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void importStuAttrGrade(List<StuAttrGrade> stuAttrGradeList) throws SQLException, ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.OpenDb();
        String sqll = "delete from stu_attr_grade";
        dbHelper.updateQuery(sqll);
        for (int i = 0; i < stuAttrGradeList.size(); i++) {
            StuAttrGrade stuAttrGrade = stuAttrGradeList.get(i);
            String sql = "insert into stu_attr_grade(sag_id, student_id, attr1_grade, attr2_grade, attr3_grade, attr4_grade, attr5_grade, attr6_grade, attr7_grade) values "+
                    "("+  "'"+stuAttrGrade.getSagId()+"'"+","+"'"+stuAttrGrade.getStudentId()+"'"+","+"'"+stuAttrGrade.getAttr1Grade()+"'"+","+"'"+stuAttrGrade.getAttr2Grade()+"'"+","+"'"+stuAttrGrade.getAttr3Grade()+"'"+","+"'"+stuAttrGrade.getAttr4Grade()+ "'"+","+"'"
                    +stuAttrGrade.getAttr5Grade()+"'"+","+"'"+stuAttrGrade.getAttr6Grade()+"'"+","+"'"+stuAttrGrade.getAttr7Grade()+ "'"+")";
            dbHelper.updateQuery(sql);
        }
        dbHelper.closeDb();
    }


}
