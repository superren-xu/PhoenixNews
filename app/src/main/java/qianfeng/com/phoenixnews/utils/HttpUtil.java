package qianfeng.com.phoenixnews.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import qianfeng.com.phoenixnews.bean.Classify;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class HttpUtil {

    public static OkHttpClient getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).build();
        return okHttpClient;
    }

    //拼接URL
    public static List<Classify> mosaicUrl(List<Classify> list) {
        List<Classify> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder builder = new StringBuilder();
            Classify classify = list.get(i);
            switch (classify.getName()) {
                case "头条":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "推荐":
                    builder.append(classify.getApi()).append("?userId=357246052275532&count=6&gv=5.3.1&av=5.3.1&uid=357246052275532&deviceid=357246052275532&proid=ifengnews&os=android_17&df=androidphone&vt=5&screen=1080x1776&publishid=2006&nw=wifi");
                    classify.setTag(Constant.GEN);
                    break;
                case "娱乐":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "本地":
                    builder.append(classify.getApi()).append("?province=%s&city=%s&page=%d");
                    classify.setTag(Constant.PROVINCE_CITY);
                    break;
                case "财经":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "凤凰号":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "凤凰卫视":
                    builder.append(classify.getApi()).append("useType=androidPhone&channelId=000000-0&callBy=news&pageSize=%d&positionId=7316529");
                    classify.setTag(Constant.NUMBERSIZE);
                    break;
                case "社会":
                    builder.append(classify.getApi()).append("&gv=5.3.1&av=5.3.1&uid=357246052275532&deviceid=357246052275532&proid=ifengnews&os=android_17&df=androidphone&vt=5&screen=1080x1776&publishid=2006&nw=wifi&action=%s");
                    classify.setTag(Constant.UP_DOWN);
                    break;
                case "科技":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "美女":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "军事":
                    builder.append(classify.getApi()).append("&gv=5.3.1&av=5.3.1&uid=357246052275532&deviceid=357246052275532&proid=ifengnews&os=android_17&df=androidphone&vt=5&screen=1080x1776&publishid=2006&nw=wifi&action=%s");
                    classify.setTag(Constant.UP_DOWN);
                    break;
                case "体育":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "历史":
                    builder.append(classify.getApi()).append("&gv=5.3.1&av=5.3.1&uid=357246052275532&deviceid=357246052275532&proid=ifengnews&os=android_17&df=androidphone&vt=5&screen=1080x1776&publishid=2006&nw=wifi&action=%s");
                    classify.setTag(Constant.UP_DOWN);
                    break;
                case "汽车":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "时尚":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "房产":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "FUN来了":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "段子":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
                case "萌物":
                    builder.append(classify.getApi()).append("&page=%d");
                    classify.setTag(Constant.PAGENUMBER);
                    break;
            }
            classify.setApi(builder.toString());
            list1.add(classify);
        }
        return list1;
    }
}
