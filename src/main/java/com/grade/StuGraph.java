package com.grade;

import com.grade.entity.AttributeItem;
import com.grade.entity.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by dell on 2017/2/27.
 */
public class StuGraph {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<Float> attrClass = new ArrayList<Float>();
        List<Float> attrGrade = new ArrayList<Float>();
        AttributeItem attributeItem = new AttributeItem();
        List<AttributeItem> items = attributeItem.getattriItem();
        attrClass.add(0.70f);
        attrClass.add(0.68f);
        attrClass.add(0.80f);
        attrClass.add(0.72f);
        attrClass.add(0.62f);
        attrClass.add(0.70f);
        attrClass.add(0.58f);
        attrGrade.add(0.78f);
        attrGrade.add(0.92f);
        attrGrade.add(0.50f);
        attrGrade.add(0.88f);
        attrGrade.add(0.82f);
        attrGrade.add(0.40f);
        attrGrade.add(0.76f);
        CategoryDataset ds = getDataSet(attrGrade,attrClass,items);
        excalSheet sheet = new excalSheet();
        List<Student> students = sheet.getStudent();
        createJPG(students.get(0),ds);
    }

    public static CategoryDataset getDataSet(List<Float> attrGrade, List<Float> attrClass, List<AttributeItem> items) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i = 0; i < attrClass.size(); i++) {
            AttributeItem item = items.get(i);
//            if (i !=6) {
            ds.addValue(attrClass.get(i), "群体平均掌握概率分布(上)", item.getMark() + ":" + item.getAttrName());
            ds.addValue(attrGrade.get(i), "我的属性掌握概率分布(下)", item.getMark() + ":" + item.getAttrName());
            /*}else{
                String attrName = item.getAttrName();
                StringBuffer sb = new StringBuffer();
                sb.append(attrName.substring(0,5)).append("\n").append(attrName.substring(5));
                ds.addValue(attrClass.get(i), "群体平均掌握概率分布(上)", item.getMark() + ":" + sb.toString());
                ds.addValue(attrGrade.get(i), "我的属性掌握概率分布(下)", item.getMark() + ":" + sb.toString());
                System.out.println(sb.toString());
            }*/
        }
        return ds;
    }
    public static void createJPG(Student student,CategoryDataset ds) throws IOException{
        JFreeChart chart = ChartFactory.createBarChart("属性掌握概率及群体比较",
                "",
                "",
                ds, PlotOrientation.HORIZONTAL,
                true,false,false);
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0,Color.LIGHT_GRAY);
        renderer.setSeriesPaint(1,Color.GRAY);
        renderer.setDrawBarOutline(true);
        renderer.setItemMargin(0.0);
        renderer.setBarPainter(new StandardBarPainter());
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
//        plot.setForegroundAlpha(1.0f);

        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();

        CategoryAxis domainAxis = categoryplot.getDomainAxis();

        /*------设置X轴坐标上的文字-----------*/
//        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 13));
        /*------设置X轴的标题文字------------*/
        domainAxis.setLabelFont(new Font("楷体", Font.PLAIN, 12));

        /*------设置Y轴坐标上的文字-----------*/
        numberaxis.setTickLabelFont(new Font("楷体", Font.PLAIN, 12));

        /*------设置Y轴的标题文字------------*/
        numberaxis.setLabelFont(new Font("楷体", Font.PLAIN, 12));


        /*------这句代码解决了底部汉字乱码的问题-----------*/
//        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        chart.getTitle().setVisible(false);
//        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        /*******这句代码解决了标题汉字乱码的问题********/
//        chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 12));
        chart.getLegend().setVisible(false);
//        chart.getTitle().setPosition(RectangleEdge.TOP);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("E:\\gradeReport\\teacherDong\\images\\"+student.getStuName()+student.getStudentId()+".jpg");
            ChartUtilities.writeChartAsJPEG(out, 1.0f, chart, 480, 289, null);
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
