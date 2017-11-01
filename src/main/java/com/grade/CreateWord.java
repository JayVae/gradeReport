package com.grade;

/**
 * Created by dell on 2017/2/25.
 */
/**
 * Created by dell on 2017/2/24.
 */


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grade.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

public class CreateWord {
    private static Configuration configuration;

    public CreateWord() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public void createWord(Map<String,Object> dataMap) throws IOException, TemplateException, InvalidFormatException {
        configuration.setDirectoryForTemplateLoading(new File("E:\\gradeReport\\teacherDong")); // FTL文件所存在的位置
        Template template = configuration.getTemplate("314.ftl");
        File outFile = new File("E:\\gradeReport\\teacherDong\\temp\\"+dataMap.get("stuno")+dataMap.get("name")+".doc");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        template.process(dataMap, out);
        out.flush();
        out.close();
    }

    public static Map<String,Object> getData(Student student, String totgrade, String ave, String pos,
                                             List<String> correct, List<String> answer,List<String> typegrades,
                                             Map<Integer,List<String>> attr,
                                             StudentSuggestion stuSuggestion,String image) throws IOException {
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name",student.getStuName());
        dataMap.put("stuno",student.getStuExamNo());
        dataMap.put("totgrade",totgrade);
        dataMap.put("ave",ave);
        dataMap.put("pos",pos);
        for (int i = 0; i < correct.size(); i++) {
            dataMap.put("a"+(i+1),correct.get(i));
        }
        for (int i = 0; i < answer.size(); i++) {
            dataMap.put("b"+(i+1),answer.get(i));
        }
        for (int i = 0; i < typegrades.size(); i++) {
            dataMap.put("t"+(i+1),typegrades.get(i));
        }
        dataMap.put("img",image);
        for (int i = 0; i < attr.size(); i++) {
            String master = "";
            List<String> masterList = attr.get(i+1);
            for (String s : masterList) {
                master = master+s+" ";
            }
            dataMap.put("tt"+(i+1),master);
            
        }
        dataMap.put("sug1",stuSuggestion.getA1());
        dataMap.put("sug2",stuSuggestion.getA2());
        dataMap.put("sug3",stuSuggestion.getA3());
        dataMap.put("sug4",stuSuggestion.getA4());
        dataMap.put("sug5",stuSuggestion.getA5());
        dataMap.put("sug6",stuSuggestion.getA6());
        dataMap.put("sug7",stuSuggestion.getA7());
        return dataMap;
    }

    public static String getImageStr(Student student) {
                String imgFile = "E:\\gradeReport\\teacherDong\\images\\"+student.getStuName()+student.getStudentId()+".jpg";
                InputStream in = null;
                byte[] data = null;
                try {
                        in = new FileInputStream(imgFile);
                        data = new byte[in.available()];
                        in.read(data);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(data);
            }


}


