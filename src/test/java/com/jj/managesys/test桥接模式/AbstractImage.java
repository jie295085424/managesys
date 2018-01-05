/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.test桥接模式;

/**
 * @author huangjunjie
 * @ClassName AbstractImage
 * @Description
 * @Date 2018/1/5.
 */
public abstract class AbstractImage {

    protected Image image;

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract void parseFile(String fileName);
}
