/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.test桥接模式;

/**
 * @author huangjunjie
 * @ClassName
 * @Description
 * @Date 2018/1/5.
 */
public class test {
    public static void main(String[] args) {
        Image image = new WindowImageImpl();
        AbstractImage jpgImage = new JpgImage();
        jpgImage.setImage(image);
        jpgImage.parseFile("hello");

    }
}
