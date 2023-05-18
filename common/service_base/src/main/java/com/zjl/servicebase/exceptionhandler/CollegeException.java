package com.zjl.servicebase.exceptionhandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/5/18
 * @Time: 14:58
 * @project:college_parent
 */

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class CollegeException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息

}
