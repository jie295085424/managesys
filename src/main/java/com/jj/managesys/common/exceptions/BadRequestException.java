/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.common.exceptions;

import com.jj.managesys.common.enums.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;


/**
 * @author huangjunjie
 * @ClassName BadRequestException
 * @Description
 * @Date 2017/12/28.
 */

@Getter
@Setter
public class BadRequestException extends Exception {

    private int code;
    private String Message;

    public BadRequestException(ResponseCodeEnum codeEnum) {

        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.Message = codeEnum.getMessage();
    }
}
