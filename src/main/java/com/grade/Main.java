package com.grade;

import com.grade.entity.*;
import freemarker.template.TemplateException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/2/27.
 */
public class Main {
    public static void main(String[] args) throws IOException, TemplateException, InvalidFormatException, SQLException {
        CreateWord test = new CreateWord();
        excalSheet sheet = new excalSheet();
        List<Student> students = sheet.getStudent();//获取学生
        QueryData queryData = new QueryData();
        String totgrade = "";
        List<AnsResult> ansResults = sheet.getAnsResult();
        List<DetailAns> details = sheet.getDetailAns();
        String ave = queryData.getAverageGrade(ansResults);//
        List<String> gradeRank = queryData.getTotalRank();//
        String pos = "";
        List<String> correct = queryData.getCorAnswer();//
        List<String> correct1 = new ArrayList<String>();
        for (String s : correct) {
            if (s.equals("1")){
                correct1.add(" ");
            }else {
                correct1.add(s);
            }
        }
        List<String> answer = new ArrayList<String>();
        List<String> pattern = new ArrayList<String>();
        Map<Integer,List<String>> map = new HashMap<Integer, List<String>>();
        StudentSuggestion stuSugg = new StudentSuggestion();
        List<Float> classAttr = queryData.getAttrAve();//
        List<Float> gradeAttr = new ArrayList<Float>();
        AttributeItem item = new AttributeItem();
        List<AttributeItem> items = item.getattriItem();//
        Map<String,Object> dataMap = new HashMap<String, Object>();
        for (Student student : students) {
            totgrade = queryData.getMyGrade(student);
            pos = queryData.getRank(student,gradeRank);
            answer = queryData.getMyAnswer1(student,ansResults,details);
//            pattern =queryData.getPatternGrade(student);
            pattern = queryData.getRevised_PatternGrade(student);
            map = queryData.getAttributeClassify(student,items);//循环放到dataMap
//            stuSugg = queryData.getStudentSuggestion(student);
            stuSugg = queryData.getRevisedStudentSuggestion(student);
            gradeAttr =queryData.getStuAtti(student);
            StuGraph.createJPG(student,StuGraph.getDataSet(gradeAttr,classAttr,items));
            dataMap = CreateWord.getData(student,totgrade,ave,pos,correct1,answer,pattern,map,stuSugg,
                    CreateWord.getImageStr(student));
            test.createWord(dataMap);
            System.out.println(student.getStudentId()+student.getStuName()+student.getStuExamNo());

//            break;
        }
    }
}
