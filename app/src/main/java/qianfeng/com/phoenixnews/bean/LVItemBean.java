package qianfeng.com.phoenixnews.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class LVItemBean implements Serializable {
    //itemview的图片
    private String thumbnail;
    //新闻标题
    private String title;
    //评论数
    private String commentsall;
    //发表时间
    private String updateTime;
    //正文内容
    private String commentsUrl;
    //区分item的布局类型
    private int type;
    //存放图片的数组
    private String[] imgs;
    //图片的总数
    private int slideCount;
    //头布局的标签
    private String tag;
    //存放头布局标题的数组
    private String[] titles;
    //图片喜欢人数
    private int likes;
    //图片的宽高
    private String width;
    //
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    private String height;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getSlideCount() {
        return slideCount;
    }

    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LVItemBean() {
    }

    public LVItemBean(String thumbnail, String title, String commentsall, String updateTime, String commentsUrl) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.commentsall = commentsall;
        this.updateTime = updateTime;
        this.commentsUrl = commentsUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentsall() {
        return commentsall;
    }

    public void setCommentsall(String commentsall) {
        this.commentsall = commentsall;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    @Override
    public String toString() {
        return "LVItemBean{" +
                "thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", commentsall='" + commentsall + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", type=" + type +
                ", imgs=" + Arrays.toString(imgs) +
                ", slideCount=" + slideCount +
                ", tag='" + tag + '\'' +
                ", titles=" + Arrays.toString(titles) +
                '}';
    }
}
