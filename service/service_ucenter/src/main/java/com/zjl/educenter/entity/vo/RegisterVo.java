package com.zjl.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @User: zangj
 * @Author: zjl
 * @Date: 2023/6/16
 * @Time: 10:09
 * @project:college_parent
 */

@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")

    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
