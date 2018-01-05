/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.test桥接模式;

/**
 * @author huangjunjie
 * @ClassName WindowImageImpl
 * @Description
 * @Date 2018/1/5.
 */
public class WindowImageImpl implements Image {

    @Override
    public void doPaint(String name) {
        System.out.println("windows doPaint:" + name);
    }
}
