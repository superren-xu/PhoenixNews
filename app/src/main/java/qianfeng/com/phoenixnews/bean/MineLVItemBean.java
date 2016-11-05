package qianfeng.com.phoenixnews.bean;

/**
 * Created by Administrator on 2016/11/5 0005.
 */

public class MineLVItemBean {
    private int leftImg;
    private int rightImg;
    private String name;

    public MineLVItemBean() {
    }

    public MineLVItemBean(int leftImg, int rightImg, String name) {
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.name = name;
    }

    public int getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(int leftImg) {
        this.leftImg = leftImg;
    }

    public int getRightImg() {
        return rightImg;
    }

    public void setRightImg(int rightImg) {
        this.rightImg = rightImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
