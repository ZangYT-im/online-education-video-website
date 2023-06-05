package com.zjl.excel;

import com.alibaba.excel.EasyExcel;
import com.zjl.eduservice.entity.EduSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/2
 * @Time: 16:22
 * @project:college_parent
 */

public class TestEasyExcel {

    public static void main(String[] args) {
        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String fileName = "d://write.xlsx";


        //2 调用easyexcel里面的方法实现写操作
        //第二个参数：实体类的class
        EasyExcel.write(fileName, EduSubject.class).sheet("学生列表").doWrite(getData());


//        //实现excel读操作
//        String fileName = "d://write.xlsx";
//
//        EasyExcel.read(fileName, DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private  static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("牛逼"+i);
            list.add(demoData);
        }
        return list;
    }
}
