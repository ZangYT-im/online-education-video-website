package com.zjl.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/2
 * @Time: 17:39
 * @project:college_parent
 */

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
