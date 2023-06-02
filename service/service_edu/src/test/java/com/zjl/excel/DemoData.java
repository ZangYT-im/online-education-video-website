package com.zjl.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/2
 * @Time: 16:20
 * @project:college_parent
 */


@Data
public class DemoData {

    //设置excel表头的名称
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
