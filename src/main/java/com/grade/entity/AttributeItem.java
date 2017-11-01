package com.grade.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/2/24.
 */
public class AttributeItem {
    private String mark;
    private String attrName;
    private String attrItem;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrItem() {
        return attrItem;
    }

    public void setAttrItem(String attrItem) {
        this.attrItem = attrItem;
    }

    public List<AttributeItem> getattriItem (){
        List<AttributeItem> attributeItemList = new ArrayList<AttributeItem>();
        AttributeItem attrItem1 = new AttributeItem();
        attrItem1.setMark("A1");
        attrItem1.setAttrName("辨音");

        AttributeItem attrItem2 = new AttributeItem();
        attrItem2.setMark("A2");
        attrItem2.setAttrName("词汇");


        AttributeItem attrItem3 = new AttributeItem();
        attrItem3.setMark("A3");
        attrItem3.setAttrName("句法");

        AttributeItem attrItem4 = new AttributeItem();
        attrItem4.setMark("A4");
        attrItem4.setAttrName("细节");

        AttributeItem attrItem5 = new AttributeItem();
        attrItem5.setMark("A5");
        attrItem5.setAttrName("主旨");

        AttributeItem attrItem6 = new AttributeItem();
        attrItem6.setMark("A6");
        attrItem6.setAttrName("推理");

        AttributeItem attrItem7 = new AttributeItem();
        attrItem7.setMark("A7");
        attrItem7.setAttrName("选择注意和记笔记");

        attributeItemList.add(attrItem1);
        attributeItemList.add(attrItem2);
        attributeItemList.add(attrItem3);
        attributeItemList.add(attrItem4);
        attributeItemList.add(attrItem5);
        attributeItemList.add(attrItem6);
        attributeItemList.add(attrItem7);
        return attributeItemList;
    }
}
