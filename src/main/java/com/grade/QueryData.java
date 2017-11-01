package com.grade;

import com.grade.entity.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Administrator on 2017/2/25.
 */
public class QueryData {
    private static final DbHelper dbHelper;
    private static List<AnsResult> ansResultList;
    private static Answer correctAnswer;
    private static List<DetailAns> detailAnsesList;
    private static List<StuAttrGrade> stuAttrGradesList;
    private static List<Student> studentList;
    private static List<Integer> goodAttrList ;//存掌握较好的属性序号
    private static List<Integer> generalAttrList ;//存掌握一般的属性序号
    private static List<Integer> badAttrList ;//存未掌握的属性序号

    static {
        dbHelper = new DbHelper();
        try {
            dbHelper.OpenDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public QueryData() throws IOException, InvalidFormatException {

    }


    /**
     * 得到平均值
     * @return
     */
    public  String getAverageGrade(List<AnsResult> ansResultList){
        float ave ;
        float sum = 0;

        for (int i = 0; i < ansResultList.size(); i++) {
             sum += ansResultList.get(i).getTotalGrade();
        }
        ave = sum / ansResultList.size();
        return String.format("%.1f",ave);
    }

    /**
     * 得到学生得分
     * @param student
     * @return
     * @throws SQLException
     */
    public String getMyGrade(Student student) throws SQLException {
        String studentId = student.getStudentId();
        String sql = "select total_grade from ans_result where student_id = " +"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        String result = null;
        while(resultSet.next()){
            result = resultSet.getString(1);
        }
        resultSet.close();
        //dbHelper.closeDb();
        return  result;
    }

    /**
     * 给出学生排名前百分数
     * @param student
     * @param gradeRankList
     * @return
     */
    public String getRank(Student student, List<String> gradeRankList){
        String studentId = student.getStudentId();
        int position = gradeRankList.indexOf(studentId) +1;
        float percent = (float) position/(float)gradeRankList.size();
        return String.format("%.2f",percent*100);
    }

    /**
     * 得到按分数排名的学生成绩序列
     * @return
     */
    public List<String> getTotalRank() throws SQLException {
        String sql = "select student_id from ans_result order by total_grade desc ";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        String resultPre ;
        List<String> gradeRankList = new ArrayList<String>();
        while(resultSet.next()){
            resultPre = resultSet.getString(1);
            gradeRankList.add(resultPre);
        }
        resultSet.close();
        //dbHelper.closeDb();

        return gradeRankList;
    }

    /**
     * 得到我的作答
     * @param student
     *
     * @return
     */
    public  List<String> getMyAnswer1(Student student,List<AnsResult> ansResultList, List<DetailAns> detailAnsesList) throws SQLException {
        List<String> myResult = new ArrayList<String>();
        String studentId = student.getStudentId();
        AnsResult ansResult = null;
        DetailAns detailAns = null;
        /*for (int i = 0; i < QueryData.ansResultList.size(); i++) {
            String studentId1 = QueryData.ansResultList.get(i).getStudentId();
            if (studentId1.equals(studentId)){
                ansResult = QueryData.ansResultList.get(i);
                break;
            }
        }*/
        String sql = "select ans_res_id from ans_result where student_id = "+"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        String index = null;
        while (resultSet.next()){
            index = resultSet.getString(1);
        }
        resultSet.close();
        ansResult = ansResultList.get(Integer.valueOf(index)-1);
        /*for (int i = 0; i < detailAnsesList.size(); i++) {
            String studentId1 = detailAnsesList.get(i).getStudentId();
            if (studentId1.equals(studentId)){
                detailAns = detailAnsesList.get(i);
                break;
            }
        }*/
        String sql1 = "select dans_id from detail_ans where student_id = "+"'"+studentId+"'";
        ResultSet resultSet1 = dbHelper.executeQuery(sql1);
        String index1 = null;
        while (resultSet1.next()){
            index1 = resultSet1.getString(1);
        }
        resultSet1.close();
        detailAns = detailAnsesList.get(Integer.valueOf(index1)-1);


        myResult.add(ansResult.getQuestion1().equals("1") ? "√":detailAns.getQuestion1());
        myResult.add(ansResult.getQuestion2().equals("1") ? "√":detailAns.getQuestion2());
        myResult.add(ansResult.getQuestion3().equals("1") ? "√":detailAns.getQuestion3());
        myResult.add(ansResult.getQuestion4().equals("1") ? "√":detailAns.getQuestion4());
        myResult.add(ansResult.getQuestion5().equals("1") ? "√":detailAns.getQuestion5());
        myResult.add(ansResult.getQuestion6().equals("1") ? "√":detailAns.getQuestion6());
        myResult.add(ansResult.getQuestion7().equals("1") ? "√":detailAns.getQuestion7());
        myResult.add(ansResult.getQuestion8().equals("1") ? "√":detailAns.getQuestion8());
        myResult.add(ansResult.getQuestion9().equals("1") ? "√":detailAns.getQuestion9());
        myResult.add(ansResult.getQuestion10().equals("1") ? "√":detailAns.getQuestion10());
        myResult.add(ansResult.getQuestion11().equals("1") ? "√":detailAns.getQuestion11());
        myResult.add(ansResult.getQuestion12().equals("1") ? "√":detailAns.getQuestion12());
        myResult.add(ansResult.getQuestion13().equals("1") ? "√":detailAns.getQuestion13());
        myResult.add(ansResult.getQuestion14().equals("1") ? "√":detailAns.getQuestion14());
        myResult.add(ansResult.getQuestion15().equals("1") ? "√":detailAns.getQuestion15());
        myResult.add(ansResult.getQuestion16().equals("1") ? "√":detailAns.getQuestion16());
        myResult.add(ansResult.getQuestion17().equals("1") ? "√":detailAns.getQuestion17());
        myResult.add(ansResult.getQuestion18().equals("1") ? "√":detailAns.getQuestion18());
        myResult.add(ansResult.getQuestion19().equals("1") ? "√":detailAns.getQuestion19());
        myResult.add(detailAns.getQuestion20());
        myResult.add(detailAns.getQuestion21());
        myResult.add(detailAns.getQuestion22());
        myResult.add(ansResult.getQuestion23().equals("1") ? "√":detailAns.getQuestion23());
        myResult.add(ansResult.getQuestion24().equals("1") ? "√":detailAns.getQuestion24());
        //dbHelper.closeDb();
        return myResult;
    }

    /**
     * 得到正确答案
     *
     * @return
     * @throws SQLException
     */
    public List<String> getCorAnswer () throws SQLException {
        String sql = "select * from answer";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        List<String> corAnsweList = new ArrayList<String>();
        while (resultSet.next()){
            for (int i = 1; i < 25; i++) {
                corAnsweList.add( resultSet.getString(i+1));
            }
        }
        resultSet.close();
        //dbHelper.closeDb();
        return corAnsweList;
    }

    /**
     * 得到每部分得分
     * @param student
     * @return
     * @throws SQLException
     */
    public List<String> getPatternGrade(Student student) throws SQLException {
        List<String> resultList = new ArrayList<String>();
        //得到第一个题型的题目编号
        String sql1 = "select ques_id from questionattr where type_id = 1";
        ResultSet resultSet1 = dbHelper.executeQuery(sql1);
        List<String> resultList1 = new ArrayList<String>();
        while (resultSet1.next()){
            resultList1.add(resultSet1.getString(1));
        }
        resultSet1.close();

        //得到第二个题型的题目编号
        String sql2 = "select ques_id from questionattr where type_id = 2";
        ResultSet resultSet2 = dbHelper.executeQuery(sql2);
        List<String> resultList2 = new ArrayList<String>();
        while (resultSet2.next()){
            resultList2.add(resultSet2.getString(1));
        }
        resultSet2.close();
        //得到第三个题型的题目编号
        String sql3 = "select ques_id from questionattr where type_id = 3";
        ResultSet resultSet3 = dbHelper.executeQuery(sql3);
        List<String> resultList3 = new ArrayList<String>();
        while (resultSet3.next()){
            resultList3.add(resultSet3.getString(1));
        }
        resultSet3.close();
        //得到第四个题型的题目编号
        String sql4 = "select ques_id from questionattr where type_id =4";
        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
        List<String> resultList4 = new ArrayList<String>();
        while (resultSet4.next()){
            resultList4.add(resultSet4.getString(1));
        }
        resultSet4.close();
        //得到第五个题型的题目编号
        String sql5 = "select ques_id from questionattr where type_id =5";
        ResultSet resultSet5 = dbHelper.executeQuery(sql5);
        List<String> resultList5 = new ArrayList<String>();
        while (resultSet5.next()){
            resultList5.add(resultSet5.getString(1));
        }
        resultSet5.close();

        String studentId = student.getStudentId();
        String sql = "select * from ans_result where student_id = "+"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        String sqll = "select * from detail_ans where student_id = "+"'"+studentId+"'";
        ResultSet resultSet6 = dbHelper.executeQuery(sqll);


        while (resultSet.next()){
            int resulttemp1 = 0;
            for (int i = 0; i < resultList1.size(); i++) {
                int temp = Integer.valueOf(resultList1.get(i))+2;
                resulttemp1 += Integer.valueOf(resultSet.getString(temp));
            }
            resultList.add(String.valueOf(resulttemp1));

            int resulttemp2 = 0;
            for (int i = 0; i < resultList2.size(); i++) {
                resulttemp2 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList2.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp2));

            int resulttemp3 = 0;
            for (int i = 0; i < resultList3.size(); i++) {
                resulttemp3 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList3.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp3));

            /*int resulttemp4 = 0;
            for (int i = 0; i < resultList4.size(); i++) {
                resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp4));*/


//            int resulttemp4 = 0;
//            for (int i = 0; i < resultList4.size(); i++) {
//                if (resultList4.get(i).equals("20") || resultList4.get(i).equals("21")){
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2))*2;
//                }else if (resultList4.get(i).equals("22")){
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2))*3;
//                }else {
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2));
//                }
//            }
            float resulttemppp = 0;
            while (resultSet6.next()){
                float resulttemp4 = 0;
                for (int i = 0; i < resultList4.size(); i++) {
                    int temp1 = Integer.valueOf(resultList4.get(i))+2;
                    resulttemp4 += Float.valueOf(resultSet6.getString(temp1));
                }
                resulttemppp = resulttemp4;
            }
            resultList.add(String.valueOf(resulttemppp));

            int resulttemp5 = 0;
            for (int i = 0; i < resultList5.size(); i++) {
                resulttemp5 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList5.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp5));

        }
        resultSet.close();
        //dbHelper.closeDb();

        return resultList;
    }

    /**
     * 得到每部分得分修正
     * @param student
     * @return
     * @throws SQLException
     */
    public List<String> getRevised_PatternGrade(Student student) throws SQLException {
        List<String> resultList = new ArrayList<String>();
        //得到第一个题型的题目编号
        String sql1 = "select ques_id from revised_questionattr where type_id = 1";
        ResultSet resultSet1 = dbHelper.executeQuery(sql1);
        List<String> resultList1 = new ArrayList<String>();
        while (resultSet1.next()){
            resultList1.add(resultSet1.getString(1));
        }
        resultSet1.close();

        //得到第二个题型的题目编号
        String sql2 = "select ques_id from revised_questionattr where type_id = 2";
        ResultSet resultSet2 = dbHelper.executeQuery(sql2);
        List<String> resultList2 = new ArrayList<String>();
        while (resultSet2.next()){
            resultList2.add(resultSet2.getString(1));
        }
        resultSet2.close();
        //得到第三个题型的题目编号
        String sql3 = "select ques_id from revised_questionattr where type_id = 3";
        ResultSet resultSet3 = dbHelper.executeQuery(sql3);
        List<String> resultList3 = new ArrayList<String>();
        while (resultSet3.next()){
            resultList3.add(resultSet3.getString(1));
        }
        resultSet3.close();
        //得到第四个题型的题目编号
        String sql4 = "select ques_id from revised_questionattr where type_id =4";
        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
        List<String> resultList4 = new ArrayList<String>();
        while (resultSet4.next()){
            resultList4.add(resultSet4.getString(1));
        }
        resultSet4.close();
        //得到第五个题型的题目编号
        String sql5 = "select ques_id from revised_questionattr where type_id =5";
        ResultSet resultSet5 = dbHelper.executeQuery(sql5);
        List<String> resultList5 = new ArrayList<String>();
        while (resultSet5.next()){
            resultList5.add(resultSet5.getString(1));
        }
        resultSet5.close();

        String studentId = student.getStudentId();
        String sql = "select * from ans_result where student_id = "+"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        String sqll = "select * from detail_ans where student_id = "+"'"+studentId+"'";
        ResultSet resultSet6 = dbHelper.executeQuery(sqll);


        while (resultSet.next()){
            int resulttemp1 = 0;
            for (int i = 0; i < resultList1.size(); i++) {
                int temp = Integer.valueOf(resultList1.get(i))+2;
                resulttemp1 += Integer.valueOf(resultSet.getString(temp));
            }
            resultList.add(String.valueOf(resulttemp1));

            int resulttemp2 = 0;
            for (int i = 0; i < resultList2.size(); i++) {
                resulttemp2 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList2.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp2));

            int resulttemp3 = 0;
            for (int i = 0; i < resultList3.size(); i++) {
                resulttemp3 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList3.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp3));

            /*int resulttemp4 = 0;
            for (int i = 0; i < resultList4.size(); i++) {
                resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp4));*/


//            int resulttemp4 = 0;
//            for (int i = 0; i < resultList4.size(); i++) {
//                if (resultList4.get(i).equals("20") || resultList4.get(i).equals("21")){
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2))*2;
//                }else if (resultList4.get(i).equals("22")){
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2))*3;
//                }else {
//                    resulttemp4 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList4.get(i))+2));
//                }
//            }
            float resulttemppp = 0;
            while (resultSet6.next()){
                float resulttemp4 = 0;
                for (int i = 0; i < resultList4.size(); i++) {
                    int temp1 = Integer.valueOf(resultList4.get(i))+2;
                    resulttemp4 += Float.valueOf(resultSet6.getString(temp1));
                }
                resulttemppp = resulttemp4;
            }
            resultList.add(String.valueOf(resulttemppp));

            int resulttemp5 = 0;
            for (int i = 0; i < resultList5.size(); i++) {
                resulttemp5 += Integer.valueOf(resultSet.getString(Integer.valueOf(resultList5.get(i))+2));
            }
            resultList.add(String.valueOf(resulttemp5));

        }
        resultSet.close();
        //dbHelper.closeDb();

        return resultList;
    }


    /**
     * 得到学生属性掌握情况
     * @param student
     * @param attrItemList 属性名称及说明的列表
     * @return
     * @throws SQLException
     */
    public Map<Integer,List<String>> getAttributeClassify(Student student, List<AttributeItem> attrItemList) throws SQLException {
        String studentId = student.getStudentId();
        String sql = "select * from stu_attr_grade where student_id = " +"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        getAttrCateList(resultSet);//得到属性三种分类的编号列表
        List<String> AttrList1 = new ArrayList<String>();//存掌握较好的属性
        List<String> AttrList2 = new ArrayList<String>();//存掌握一般的属性
        List<String> AttrList3 = new ArrayList<String>();//存未掌握的属性序号
        if (goodAttrList.size()>0){
            for (int i = 0; i < goodAttrList.size(); i++) {
                int tempNum = goodAttrList.get(i);
                AttributeItem attr = attrItemList.get(tempNum);
                StringBuffer st = new StringBuffer(attr.getMark()+" ("+attr.getAttrName()+")");
                if (i != goodAttrList.size()-1){
                    st.append(", ");
                }
                AttrList1.add(String.valueOf(st));
            }
        }else{
            AttrList1.add("    ");
        }

        if (generalAttrList.size()>0){
            for (int i = 0; i < generalAttrList.size(); i++) {
                int tempNum = generalAttrList.get(i);
                AttributeItem attr = attrItemList.get(tempNum);
                StringBuffer st = new StringBuffer(attr.getMark()+" ("+attr.getAttrName()+")");
                if (i != generalAttrList.size()-1){
                    st.append(", ");
                }
                AttrList2.add(String.valueOf(st));
            }
        }else{
            AttrList2.add("    ");
        }
        if (badAttrList.size() >0){
            for (int i = 0; i < badAttrList.size(); i++) {
                int tempNum = badAttrList.get(i);
                AttributeItem attr = attrItemList.get(tempNum);
                StringBuffer st = new StringBuffer(attr.getMark()+" ("+attr.getAttrName()+")");
                if (i != badAttrList.size()-1){
                    st.append(", ");
                }
                AttrList3.add(String.valueOf(st));
            }
        }else {
            AttrList3.add("    ");
        }
        Map<Integer,List<String>> AttributeMaster = new HashMap<Integer, List<String>>();
        AttributeMaster.put(1, AttrList1);
        AttributeMaster.put(2, AttrList2);
        AttributeMaster.put(3, AttrList3);
        //dbHelper.closeDb();
        return AttributeMaster;
    }

    /**
     * 得到学生的建议
     * @param student
     * @return
     * @throws SQLException
     */
    public StudentSuggestion getStudentSuggestion(Student student) throws SQLException {
        String studentId = student.getStudentId();
        String sql = "select * from stu_attr_grade where student_id = " +"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        getAttrCateList(resultSet);

        String sql1 = "select * from public_attr_suggestion  order by suggest_id";
        ResultSet resultSet1 = dbHelper.executeQuery(sql1);

        StudentSuggestion stuSuggeestion = new StudentSuggestion();
        int count = 0;
        List<String> publicSuggList = new ArrayList<String>();
        while (resultSet1.next()){
            publicSuggList.add(resultSet1.getString(3));
        }
        resultSet1.close();
        for (int i =  0; i < goodAttrList.size(); i++) {
            int tempNo = goodAttrList.get(i);
            switch (tempNo){
                case 0:
                    stuSuggeestion.setA1(publicSuggList.get(0));
                    break;
                case 1:
                    stuSuggeestion.setA2(publicSuggList.get(3));
                    break;
                case 2:
                    stuSuggeestion.setA3(publicSuggList.get(6));
                    break;
                case 3:
                    stuSuggeestion.setA4(publicSuggList.get(9));
                    break;
                case 4:
                    stuSuggeestion.setA5(publicSuggList.get(12));
                    break;
                case 5:
                    stuSuggeestion.setA6(publicSuggList.get(15));
                    break;
                case 6:
                    stuSuggeestion.setA7(publicSuggList.get(18));
            }
        }

        String sql2 = "select question1,question2,question3,question4,question5,question6,question7,question8,question9,question10,question11,question13,question14,question12,question15,question16,question17,question18,question19,question20,question21,question22,question23,question24  from ans_result where student_id = " +"'"+studentId+"'";
        ResultSet resultSet2 = dbHelper.executeQuery(sql2);
        List<Integer> errorList = new ArrayList<Integer>();//错的题目
        while (resultSet2.next()){

            for (int i = 1; i < 25; i++) {
                String columName = resultSet2.getMetaData().getColumnName(i);
                int index = Integer.valueOf(columName.replace("question", ""));
                int errorTemp = Integer.valueOf(resultSet2.getString(i));
                if (errorTemp == 0){
                    errorList.add(index);
                }
            }
        }
        resultSet2.close();

        String sql3 = "select * from questionattr";
        ResultSet resultSet3 = dbHelper.executeQuery(sql3);
        Map<Integer,Integer> attr1Map = new HashMap<Integer, Integer>();//存每个属性的题号和分类
        Map<Integer,Integer> attr2Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr3Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr4Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr5Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr6Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr7Map = new HashMap<Integer, Integer>();

        while (resultSet3.next()){
            if (Integer.valueOf(resultSet3.getString(3) )!= 0){
                attr1Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(3)));
            }
            if (Integer.valueOf(resultSet3.getString(4) )!= 0){
                attr2Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(4)));
            }
            if (Integer.valueOf(resultSet3.getString(5))!=0){
                attr3Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(5)));
            }
            if (Integer.valueOf(resultSet3.getString(6))!=0){
                attr4Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(6)));
            }
            if (Integer.valueOf(resultSet3.getString(7))!=0){
                attr5Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(7)));
            }
            if (Integer.valueOf(resultSet3.getString(8))!=0){
                attr6Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(8)));
            }
            if (Integer.valueOf(resultSet3.getString(9))!=0){
                attr7Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(9)));
            }
        }
        resultSet3.close();
        //求每个属性所包含的错题类别
        Set<Integer> attr1Set = new HashSet<Integer>();
        Set attr1KeySet = attr1Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr1KeySet.contains(errorList.get(i))){
                attr1Set.add(attr1Map.get(errorList.get(i)));
            }
        }

        Set<Integer> attr2Set = new HashSet<Integer>();
        Set attr2KeySet = attr2Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr2KeySet.contains(errorList.get(i))){
                attr2Set.add(attr2Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr3Set = new HashSet<Integer>();
        Set attr3KeySet = attr3Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr3KeySet.contains(errorList.get(i))){
                attr3Set.add(attr3Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr4Set = new HashSet<Integer>();
        Set attr4KeySet = attr4Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr4KeySet.contains(errorList.get(i))){
                attr4Set.add(attr4Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr5Set = new HashSet<Integer>();
        Set attr5KeySet = attr5Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr5KeySet.contains(errorList.get(i))){
                attr5Set.add(attr5Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr6Set = new HashSet<Integer>();
        Set attr6KeySet = attr6Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr6KeySet.contains(errorList.get(i))){
                attr6Set.add(attr6Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr7Set = new HashSet<Integer>();
        Set attr7KeySet = attr7Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr7KeySet.contains(errorList.get(i))){
                attr7Set.add(attr7Map.get(errorList.get(i)));
            }
        }



        for (int i =  0; i < generalAttrList.size(); i++) {
            int tempNo = generalAttrList.get(i);
            switch (tempNo){
                case 0:
                    StringBuffer str = new StringBuffer();
                    str.append(publicSuggList.get(1));
                    for (Integer attr: attr1Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+2+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA1(String.valueOf(str));
                    break;
                case 1:
                    StringBuffer str1 = new StringBuffer();
                    str1.append(publicSuggList.get(4));
                    for (Integer attr: attr2Set) {
                        String sql5 = "select * from sub_suggestion where suggest_id = " +"'"+5+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet5 = dbHelper.executeQuery(sql5);
                        while (resultSet5.next()){
                            str1.append(resultSet5.getString(4));
                        }
                        resultSet5.close();
                    }
                    stuSuggeestion.setA2(String.valueOf(str1));
                    break;
                case 2:
                    StringBuffer str2 = new StringBuffer();
                    str2.append(publicSuggList.get(7));
                    for (Integer attr : attr3Set) {
                        String sql6 = "select * from sub_suggestion where suggest_id = " +"'"+8+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str2.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA3(String.valueOf(str2));
                    break;
                case 3:
                    StringBuffer str3 = new StringBuffer();
                    str3.append(publicSuggList.get(10));
                    for (Integer attr : attr4Set) {
                        String sql6 = "select * from sub_suggestion where suggest_id = " +"'"+11+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str3.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA4(String.valueOf(str3));
                    break;
                case 4:
                    StringBuffer str4 = new StringBuffer();
                    str4.append(publicSuggList.get(13));
                    for (Integer attr : attr5Set) {
                        String sql6 = "select * from sub_suggestion where suggest_id = " +"'"+14+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str4.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA5(String.valueOf(str4));
                    break;
                case 5:
                    StringBuffer str5 = new StringBuffer();
                    str5.append(publicSuggList.get(16));
                    for (Integer attr : attr6Set) {
                        String sql6 = "select * from sub_suggestion where suggest_id = " +"'"+17+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str5.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA6(String.valueOf(str5));
                    break;
                case 6:
                    StringBuffer str6 = new StringBuffer();
                    str6.append(publicSuggList.get(19));
                    for (Integer attr : attr7Set) {
                        String sql6 = "select * from sub_suggestion where suggest_id = " +"'"+20+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str6.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA7(String.valueOf(str6));
            }
        }
        for (int i =  0; i < badAttrList.size(); i++) {
            int tempNo = badAttrList.get(i);
            switch (tempNo){
                case 0:
                    StringBuffer str1 = new StringBuffer();
                    str1.append(publicSuggList.get(2));
                    for (Integer attr: attr1Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+3+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str1.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA1(String.valueOf(str1));
                    break;
                case 1:
                    StringBuffer str2 = new StringBuffer();
                    str2.append(publicSuggList.get(5));
                    for (Integer attr: attr2Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+6+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str2.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA2(String.valueOf(str2));
                    break;
                case 2:
                    StringBuffer str3 = new StringBuffer();
                    str3.append(publicSuggList.get(8));
                    for (Integer attr: attr3Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+9+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str3.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA3(String.valueOf(str3));
                    break;
                case 3:
                    StringBuffer str4 = new StringBuffer();
                    str4.append(publicSuggList.get(11));
                    for (Integer attr: attr4Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+12+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str4.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA4(String.valueOf(str4));
                    break;
                case 4:
                    StringBuffer str5 = new StringBuffer();
                    str5.append(publicSuggList.get(14));
                    for (Integer attr: attr5Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+15+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str5.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA5(String.valueOf(str5));
                    break;
                case 5:
                    StringBuffer str6 = new StringBuffer();
                    str6.append(publicSuggList.get(17));
                    for (Integer attr: attr6Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+18+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str6.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA6(String.valueOf(str6));
                    break;
                case 6:
                    StringBuffer str7 = new StringBuffer();
                    str7.append(publicSuggList.get(20));
                    for (Integer attr: attr7Set) {
                        String sql4 = "select * from sub_suggestion where suggest_id = " +"'"+21+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str7.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA7(String.valueOf(str7));
            }
        }
        //dbHelper.closeDb();
        return stuSuggeestion;
    }

    /**
     * 得到学生的建议修正
     * @param student
     * @return
     * @throws SQLException
     */
    public StudentSuggestion getRevisedStudentSuggestion(Student student) throws SQLException {
        String studentId = student.getStudentId();
        String sql = "select * from stu_attr_grade where student_id = " +"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        getAttrCateList(resultSet);

        String sql1 = "select * from revised_public  order by suggest_id ";
        ResultSet resultSet1 = dbHelper.executeQuery(sql1);

        StudentSuggestion stuSuggeestion = new StudentSuggestion();
        int count = 0;
        List<String> publicSuggList = new ArrayList<String>();
        while (resultSet1.next()){
            publicSuggList.add(resultSet1.getString(3));
        }
        resultSet1.close();
        for (int i =  0; i < goodAttrList.size(); i++) {
            int tempNo = goodAttrList.get(i);
            switch (tempNo){
                case 0:
                    stuSuggeestion.setA1(publicSuggList.get(0));
                    break;
                case 1:
                    stuSuggeestion.setA2(publicSuggList.get(3));
                    break;
                case 2:
                    stuSuggeestion.setA3(publicSuggList.get(6));
                    break;
                case 3:
                    stuSuggeestion.setA4(publicSuggList.get(9));
                    break;
                case 4:
                    stuSuggeestion.setA5(publicSuggList.get(12));
                    break;
                case 5:
                    stuSuggeestion.setA6(publicSuggList.get(15));
                    break;
                case 6:
                    stuSuggeestion.setA7(publicSuggList.get(18));
            }
        }

        String sql2 = "select question1,question2,question3,question4,question5,question6,question7,question8,question9,question10,question11,question13,question14,question12,question15,question16,question17,question18,question19,question20,question21,question22,question23,question24  from ans_result where student_id = " +"'"+studentId+"'";
        ResultSet resultSet2 = dbHelper.executeQuery(sql2);
        List<Integer> errorList = new ArrayList<Integer>();//错的题目
        while (resultSet2.next()){

            for (int i = 1; i < 25; i++) {
                String columName = resultSet2.getMetaData().getColumnName(i);
                int index = Integer.valueOf(columName.replace("question", ""));
                int errorTemp = Integer.valueOf(resultSet2.getString(i));
                if (errorTemp == 0){
                    errorList.add(index);
                }
            }
        }
        resultSet2.close();

        String sql3 = "select * from revised_questionattr";
        ResultSet resultSet3 = dbHelper.executeQuery(sql3);
        Map<Integer,Integer> attr1Map = new HashMap<Integer, Integer>();//存每个属性的题号和分类
        Map<Integer,Integer> attr2Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr3Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr4Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr5Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr6Map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> attr7Map = new HashMap<Integer, Integer>();

        while (resultSet3.next()){
            if (Integer.valueOf(resultSet3.getString(3) )!= 0){
                attr1Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(3)));
            }
            if (Integer.valueOf(resultSet3.getString(4) )!= 0){
                attr2Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(4)));
            }
            if (Integer.valueOf(resultSet3.getString(5))!=0){
                attr3Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(5)));
            }
            if (Integer.valueOf(resultSet3.getString(6))!=0){
                attr4Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(6)));
            }
            if (Integer.valueOf(resultSet3.getString(7))!=0){
                attr5Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(7)));
            }
            if (Integer.valueOf(resultSet3.getString(8))!=0){
                attr6Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(8)));
            }
            if (Integer.valueOf(resultSet3.getString(9))!=0){
                attr7Map.put(Integer.valueOf(resultSet3.getString(1)),Integer.valueOf(resultSet3.getString(9)));
            }
        }
        resultSet3.close();
        //求每个属性所包含的错题类别
        Set<Integer> attr1Set = new HashSet<Integer>();
        Set attr1KeySet = attr1Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr1KeySet.contains(errorList.get(i))){
                attr1Set.add(attr1Map.get(errorList.get(i)));
            }
        }

        Set<Integer> attr2Set = new HashSet<Integer>();
        Set attr2KeySet = attr2Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr2KeySet.contains(errorList.get(i))){
                attr2Set.add(attr2Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr3Set = new HashSet<Integer>();
        Set attr3KeySet = attr3Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr3KeySet.contains(errorList.get(i))){
                attr3Set.add(attr3Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr4Set = new HashSet<Integer>();
        Set attr4KeySet = attr4Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr4KeySet.contains(errorList.get(i))){
                attr4Set.add(attr4Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr5Set = new HashSet<Integer>();
        Set attr5KeySet = attr5Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr5KeySet.contains(errorList.get(i))){
                attr5Set.add(attr5Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr6Set = new HashSet<Integer>();
        Set attr6KeySet = attr6Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr6KeySet.contains(errorList.get(i))){
                attr6Set.add(attr6Map.get(errorList.get(i)));
            }
        }
        Set<Integer> attr7Set = new HashSet<Integer>();
        Set attr7KeySet = attr7Map.keySet();
        for (int i = 0; i < errorList.size(); i++) {
            if (attr7KeySet.contains(errorList.get(i))){
                attr7Set.add(attr7Map.get(errorList.get(i)));
            }
        }



        for (int i =  0; i < generalAttrList.size(); i++) {
            int tempNo = generalAttrList.get(i);
            switch (tempNo){
                case 0:
                    StringBuffer str = new StringBuffer();
                    str.append(publicSuggList.get(1));
                    for (Integer attr: attr1Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+2+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA1(String.valueOf(str));
                    break;
                case 1:
                    StringBuffer str1 = new StringBuffer();
                    str1.append(publicSuggList.get(4));
                    for (Integer attr: attr2Set) {
                        String sql5 = "select * from revised_sub where suggest_id = " +"'"+5+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet5 = dbHelper.executeQuery(sql5);
                        while (resultSet5.next()){
                            str1.append(resultSet5.getString(4));
                        }
                        resultSet5.close();
                    }
                    stuSuggeestion.setA2(String.valueOf(str1));
                    break;
                case 2:
                    StringBuffer str2 = new StringBuffer();
                    str2.append(publicSuggList.get(7));
                    for (Integer attr : attr3Set) {
                        String sql6 = "select * from revised_sub where suggest_id = " +"'"+8+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str2.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA3(String.valueOf(str2));
                    break;
                case 3:
                    StringBuffer str3 = new StringBuffer();
                    str3.append(publicSuggList.get(10));
                    for (Integer attr : attr4Set) {
                        String sql6 = "select * from revised_sub where suggest_id = " +"'"+11+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str3.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA4(String.valueOf(str3));
                    break;
                case 4:
                    StringBuffer str4 = new StringBuffer();
                    str4.append(publicSuggList.get(13));
                    for (Integer attr : attr5Set) {
                        String sql6 = "select * from revised_sub where suggest_id = " +"'"+14+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str4.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA5(String.valueOf(str4));
                    break;
                case 5:
                    StringBuffer str5 = new StringBuffer();
                    str5.append(publicSuggList.get(16));
                    for (Integer attr : attr6Set) {
                        String sql6 = "select * from revised_sub where suggest_id = " +"'"+17+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str5.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA6(String.valueOf(str5));
                    break;
                case 6:
                    StringBuffer str6 = new StringBuffer();
                    str6.append(publicSuggList.get(19));
                    for (Integer attr : attr7Set) {
                        String sql6 = "select * from revised_sub where suggest_id = " +"'"+20+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet6 = dbHelper.executeQuery(sql6);
                        while (resultSet6.next()){
                            str6.append(resultSet6.getString(4));
                        }
                        resultSet6.close();
                    }
                    stuSuggeestion.setA7(String.valueOf(str6));
            }
        }
        for (int i =  0; i < badAttrList.size(); i++) {
            int tempNo = badAttrList.get(i);
            switch (tempNo){
                case 0:
                    StringBuffer str1 = new StringBuffer();
                    str1.append(publicSuggList.get(2));
                    for (Integer attr: attr1Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+3+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str1.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA1(String.valueOf(str1));
                    break;
                case 1:
                    StringBuffer str2 = new StringBuffer();
                    str2.append(publicSuggList.get(5));
                    for (Integer attr: attr2Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+6+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str2.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA2(String.valueOf(str2));
                    break;
                case 2:
                    StringBuffer str3 = new StringBuffer();
                    str3.append(publicSuggList.get(8));
                    for (Integer attr: attr3Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+9+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str3.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA3(String.valueOf(str3));
                    break;
                case 3:
                    StringBuffer str4 = new StringBuffer();
                    str4.append(publicSuggList.get(11));
                    for (Integer attr: attr4Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+12+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str4.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA4(String.valueOf(str4));
                    break;
                case 4:
                    StringBuffer str5 = new StringBuffer();
                    str5.append(publicSuggList.get(14));
                    for (Integer attr: attr5Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+15+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str5.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA5(String.valueOf(str5));
                    break;
                case 5:
                    StringBuffer str6 = new StringBuffer();
                    str6.append(publicSuggList.get(17));
                    for (Integer attr: attr6Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+18+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str6.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA6(String.valueOf(str6));
                    break;
                case 6:
                    StringBuffer str7 = new StringBuffer();
                    str7.append(publicSuggList.get(20));
                    for (Integer attr: attr7Set) {
                        String sql4 = "select * from revised_sub where suggest_id = " +"'"+21+"'" +" and ss_no = " +"'"+attr+"'";
                        ResultSet resultSet4 = dbHelper.executeQuery(sql4);
                        while (resultSet4.next()){
                            str7.append(resultSet4.getString(4));
                        }
                        resultSet4.close();
                    }
                    stuSuggeestion.setA7(String.valueOf(str7));
            }
        }
        //dbHelper.closeDb();
        return stuSuggeestion;
    }


    /**
     * 得到每个属性的平均值
     * @return
     * @throws SQLException
     */
    public List<Float> getAttrAve () throws SQLException {
        List<Float> attrAveList = new ArrayList<Float>();
        String sql = "select * from stu_attr_grade";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        float[] sum = new float[7];
        int count = 0;
        while (resultSet.next()){
            for (int i = 0; i < sum.length; i++) {
                sum[i] += Float.valueOf(resultSet.getString(i+3));
            }
            count++;
        }

        for (int i = 0; i < 7; i++) {
            attrAveList.add(sum[i]/count);
        }
        resultSet.close();
        //dbHelper.closeDb();
        return attrAveList;
    }

    /**
     * 得到学生属性得分
     * @param student
     * @return
     */
    public List<Float> getStuAtti( Student student) throws SQLException {
        List<Float> stuAttrList = new ArrayList<Float>();
        String studentId = student.getStudentId();
        String sql = "select * from stu_attr_grade where student_id = " +"'"+studentId+"'";
        ResultSet resultSet = dbHelper.executeQuery(sql);
        while (resultSet.next()){
            for (int i = 3; i < 10; i++) {
                stuAttrList.add(Float.valueOf(resultSet.getString(i)));
            }
        }
        resultSet.close();
        //dbHelper.closeDb();
        return stuAttrList;
    }

    /**
     * 得到属性三种分类的编号列表
     * @param resultSet
     * @throws SQLException
     */
    private void getAttrCateList(ResultSet resultSet) throws SQLException {
        goodAttrList = new ArrayList<Integer>();//存掌握较好的属性序号
        generalAttrList = new ArrayList<Integer>();//存掌握一般的属性序号
        badAttrList = new ArrayList<Integer>();//存未掌握的属性序号

        while (resultSet.next()){
            for (int i = 0; i <7 ; i++) {
                float  attempt= Float.valueOf(resultSet.getString(i+3));
                if (attempt>= 0.8){
                    goodAttrList.add(i);
                }else if (attempt>=0.5){
                    generalAttrList.add(i);
                }else{
                    badAttrList.add(i);
                }
            }
        }
        resultSet.close();
    }

}

