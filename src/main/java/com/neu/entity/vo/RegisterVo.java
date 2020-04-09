package com.neu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 *
 * @author 胡若琛
 * @since 2020-04-09
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

}
