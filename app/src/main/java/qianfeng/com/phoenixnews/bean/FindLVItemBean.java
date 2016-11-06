package qianfeng.com.phoenixnews.bean;

/**
 * Created by superren on 2016/11/6.
 */

public class FindLVItemBean {
    //封面
    private String thumbnail;
    //新闻标题
    private String title;
    //推荐频道
    private String name;
    //分享数
    private String commentsall;
    //图片数组
    private String[] imgs;
    //图片数量
    private int slideCount;
    //item的类型
    private int type;
    //内容链接
    private String commentsUrl;

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public FindLVItemBean() {
    }

    public FindLVItemBean(String thumbnail, String title, String name, String commentsall, String[] imgs, int slideCount, int type) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.name = name;
        this.commentsall = commentsall;
        this.imgs = imgs;
        this.slideCount = slideCount;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentsall() {
        return commentsall;
    }

    public void setCommentsall(String commentsall) {
        this.commentsall = commentsall;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public int getSlideCount() {
        return slideCount;
    }

    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
