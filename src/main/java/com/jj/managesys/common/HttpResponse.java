/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author huangjunjie
 * @ClassName HttpResponse
 * @Description
 * @Date 2017/12/26.
 */
@Getter
@Setter
public class HttpResponse<T> {

    private int code;
    private String message;
    private T data;

    public HttpResponse() {
        this.code = 200;
        this.message = "操作成功！";
    }

    public HttpResponse(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }
}
