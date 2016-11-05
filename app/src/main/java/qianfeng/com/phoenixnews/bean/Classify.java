package qianfeng.com.phoenixnews.bean;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class Classify {
    private String name;
    private String api;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Classify() {
    }

    public Classify(String name, String api) {
        this.name = name;
        this.api = api;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "name='" + name + '\'' +
                ", api='" + api + '\'' +
                '}';
    }
}
