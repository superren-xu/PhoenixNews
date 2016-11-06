package qianfeng.com.phoenixnews.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.bean.FindLVItemBean;
import qianfeng.com.phoenixnews.bean.LVItemBean;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class ParseJson {
    public static List<Classify> parseJson2Classify(String json) {
        List<Classify> list = new ArrayList<>();
        try {
            JSONArray defaultChannel = new JSONObject(json).optJSONArray("defaultChannel");
            for (int i = 0; i < defaultChannel.length(); i++) {
                JSONObject jsonObject = defaultChannel.optJSONObject(i);
                String name = jsonObject.optString("name");
                String api = jsonObject.optString("api");
                list.add(new Classify(name, api));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJson2LVItemBean(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                if ("list".equals(jsonObject.optString("type"))) {
                    JSONArray item = jsonObject.optJSONArray("item");
                    for (int j = 0; j < item.length(); j++) {
                        JSONObject jsonObject1 = item.optJSONObject(j);
                        if ("topic2".equals(jsonObject1.optString("type"))) {
                            String thumbnail = jsonObject1.optString("thumbnail");
                            String title = jsonObject1.optString("title");
                            String commentsUrl = jsonObject1.optString("commentsUrl");
                            String commentsall = jsonObject1.optString("commentsall");
                            LVItemBean lvItemBean = new LVItemBean();
                            lvItemBean.setThumbnail(thumbnail);
                            lvItemBean.setCommentsUrl(commentsUrl);
                            lvItemBean.setTitle(title);
                            lvItemBean.setCommentsall(commentsall);
                            lvItemBean.setUpdateTime("专题");
                            lvItemBean.setTag(Constant.GENERAL);
                            lvItemBean.setType(Constant.PHOTO_TEXT);
                            list.add(lvItemBean);
                        } else if ("text_live".equals(jsonObject1.optString("type"))) {
                            String thumbnail = jsonObject1.optString("thumbnail");
                            String title = jsonObject1.optString("title");
                            String startTime = jsonObject1.optJSONObject("liveExt").optString("startTime");
                            String updateTime = "最新";
                            if (startTime != null && startTime.length() > 3) {
                                updateTime = startTime.substring(startTime.indexOf(" "), startTime.length() - 3);
                            }
                            LVItemBean lvItemBean = new LVItemBean();
                            lvItemBean.setThumbnail(thumbnail);
                            lvItemBean.setTitle(title);
                            lvItemBean.setUpdateTime(updateTime);
                            lvItemBean.setCommentsUrl("");
                            lvItemBean.setCommentsall("");
                            lvItemBean.setTag(Constant.GENERAL);
                            lvItemBean.setType(Constant.PHOTO_TEXT);
                            list.add(lvItemBean);
                        } else if (jsonObject1.has("style")) {
                            String thumbnail = jsonObject1.optString("thumbnail");
                            String title = jsonObject1.optString("title");
                            String commentsUrl = jsonObject1.optString("commentsUrl");
                            String commentsall = jsonObject1.optString("commentsall");
                            String time = jsonObject1.optString("updateTime");
                            String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                            int slideCount = jsonObject1.optJSONObject("style").optInt("slideCount");
                            JSONArray images = jsonObject1.optJSONObject("style").optJSONArray("images");
                            String[] imgs = new String[images.length()];
                            for (int k = 0; k < images.length(); k++) {
                                imgs[k] = images.optString(k);
                            }
                            LVItemBean lvItemBean = new LVItemBean();
                            lvItemBean.setThumbnail(thumbnail);
                            lvItemBean.setTitle(title);
                            lvItemBean.setCommentsUrl(commentsUrl);
                            lvItemBean.setCommentsall(commentsall);
                            lvItemBean.setUpdateTime(updateTime);
                            lvItemBean.setSlideCount(slideCount);
                            lvItemBean.setImgs(imgs);
                            lvItemBean.setTag(Constant.GENERAL);
                            lvItemBean.setType(Constant.PHOTO);
                            list.add(lvItemBean);
                        } else {
                            String thumbnail = jsonObject1.optString("thumbnail");
                            String title = jsonObject1.optString("title");
                            String commentsUrl = jsonObject1.optString("commentsUrl");
                            String commentsall = jsonObject1.optString("commentsall");
                            String time = jsonObject1.optString("updateTime");
                            String updateTime = "最近";
                            if (time != null && time.length() > 0) {
                                updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                            }
                            LVItemBean lvItemBean = new LVItemBean();
                            lvItemBean.setThumbnail(thumbnail);
                            lvItemBean.setTitle(title);
                            lvItemBean.setCommentsUrl(commentsUrl);
                            lvItemBean.setCommentsall(commentsall);
                            lvItemBean.setUpdateTime(updateTime);
                            lvItemBean.setTag(Constant.GENERAL);
                            lvItemBean.setType(Constant.PHOTO_TEXT);
                            list.add(lvItemBean);
                        }
                    }
                } else if ("focus".equals(jsonObject.optString("type"))) {
                    JSONArray item = jsonObject.optJSONArray("item");
                    String[] imgs = new String[item.length()];
                    String[] titles = new String[item.length()];
                    for (int j = 0; j < item.length(); j++) {
                        JSONObject jsonObject1 = item.optJSONObject(j);
                        imgs[j] = jsonObject1.optString("thumbnail");
                        titles[j] = jsonObject1.optString("title");
                    }
                    LVItemBean lvItemBean = new LVItemBean();
                    lvItemBean.setImgs(imgs);
                    lvItemBean.setTitles(titles);
                    lvItemBean.setTag(Constant.HEAD);
                    list.add(0, lvItemBean);
                } else if ("top".equals(jsonObject.optString("type"))) {
                    JSONArray item = jsonObject.optJSONArray("item");
                    for (int j = 0; j < item.length(); j++) {
                        JSONObject jsonObject1 = item.optJSONObject(j);
                        String thumbnail = jsonObject1.optString("thumbnail");
                        String title = jsonObject1.optString("title");
                        String commentsUrl = jsonObject1.optString("commentsUrl");
                        String commentsall = jsonObject1.optString("commentsall");
                        String time = jsonObject1.optString("updateTime");
                        String updateTime = "最新";
                        if (time != null && time.length() > 0) {
                            updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        }
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setThumbnail(thumbnail);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setTitle(title);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.PHOTO_TEXT);
                        list.add(lvItemBean);
                    }
                } else if ("tylive".equals(jsonObject.optString("type"))) {
                    continue;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromRecommend(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray item = new JSONObject(json).optJSONArray("item");
            for (int i = 0; i < item.length(); i++) {
                JSONObject jsonObject = item.optJSONObject(i);
                if (jsonObject.has("adDescription")) {
                    continue;
                } else if (jsonObject.has("flag")) {
                    continue;
                } else if (jsonObject.has("style")) {
                    if ("".equals(jsonObject.optString("thumbnail"))) {
                        String title = jsonObject.optString("title");
                        String time = jsonObject.optString("updateTime");
                        String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setTitle(title);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.TEXT);
                        list.add(lvItemBean);
                    } else if ("normal".equals(jsonObject.optJSONObject("style").optString("normal"))) {
                        String thumbnail = jsonObject.optString("thumbnail");
                        String title = jsonObject.optString("title");
                        String time = jsonObject.optString("updateTime");
                        String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setThumbnail(thumbnail);
                        lvItemBean.setTitle(title);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.PHOTO_TEXT);
                        list.add(lvItemBean);
                    } else if ("slides".equals(jsonObject.optJSONObject("style").optString("slides"))) {
                        String thumbnail = jsonObject.optString("thumbnail");
                        String title = jsonObject.optString("title");
                        String time = jsonObject.optString("updateTime");
                        String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        JSONArray array = jsonObject.optJSONObject("style").optJSONArray("images");
                        String[] imgs = new String[array.length()];
                        for (int j = 0; j < array.length(); j++) {
                            imgs[i] = array.optString(i);
                        }
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setThumbnail(thumbnail);
                        lvItemBean.setTitle(title);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setImgs(imgs);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.PHOTO);
                        list.add(lvItemBean);
                    }
                } else {
                    if ("web".equals(jsonObject.optString("type"))) {
                        continue;
                    }
                    String thumbnail = jsonObject.optString("thumbnail");
                    String title = jsonObject.optString("title");
                    String time = jsonObject.optString("updateTime");
                    String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                    String commentsUrl = jsonObject.optString("commentsUrl");
                    String commentsall = jsonObject.optString("commentsall");
                    LVItemBean lvItemBean = new LVItemBean();
                    lvItemBean.setThumbnail(thumbnail);
                    lvItemBean.setTitle(title);
                    lvItemBean.setUpdateTime(updateTime);
                    lvItemBean.setCommentsUrl(commentsUrl);
                    lvItemBean.setCommentsall(commentsall);
                    lvItemBean.setTag(Constant.GENERAL);
                    lvItemBean.setType(Constant.PHOTO_TEXT);
                    list.add(lvItemBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromLocal(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray item = new JSONObject(json).optJSONObject("data").optJSONObject("list").optJSONArray("item");
            for (int i = 0; i < item.length(); i++) {
                JSONObject jsonObject = item.optJSONObject(i);
                if (jsonObject.has("style")) {
                    String title = jsonObject.optString("title");
                    String time = jsonObject.optString("updateTime");
                    String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                    String commentsUrl = jsonObject.optString("commentsUrl");
                    String commentsall = jsonObject.optString("commentsall");
                    JSONArray array = jsonObject.optJSONObject("style").optJSONArray("images");
                    String[] imgs = new String[array.length()];
                    for (int j = 0; j < array.length(); j++) {
                        imgs[i] = array.optString(i);
                    }
                    LVItemBean lvItemBean = new LVItemBean();
                    lvItemBean.setTitle(title);
                    lvItemBean.setUpdateTime(updateTime);
                    lvItemBean.setCommentsUrl(commentsUrl);
                    lvItemBean.setCommentsall(commentsall);
                    lvItemBean.setImgs(imgs);
                    lvItemBean.setTag(Constant.GENERAL);
                    lvItemBean.setType(Constant.PHOTO);
                    list.add(lvItemBean);
                } else {
                    if ("".equals(jsonObject.optString("thumbnail"))) {
                        String title = jsonObject.optString("title");
                        String time = jsonObject.optString("updateTime");
                        String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setTitle(title);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.TEXT);
                        list.add(lvItemBean);
                    } else {
                        String thumbnail = jsonObject.optString("thumbnail");
                        String title = jsonObject.optString("title");
                        String time = jsonObject.optString("updateTime");
                        String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        LVItemBean lvItemBean = new LVItemBean();
                        lvItemBean.setThumbnail(thumbnail);
                        lvItemBean.setTitle(title);
                        lvItemBean.setUpdateTime(updateTime);
                        lvItemBean.setCommentsUrl(commentsUrl);
                        lvItemBean.setCommentsall(commentsall);
                        lvItemBean.setTag(Constant.GENERAL);
                        lvItemBean.setType(Constant.PHOTO_TEXT);
                        list.add(lvItemBean);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromPheonixNum(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("focus")) {
                JSONArray focus = jsonObject.optJSONArray("focus");
                for (int i = 0; i < focus.length(); i++) {
                    JSONObject jsonObject1 = focus.optJSONObject(i);
                    JSONArray item = jsonObject1.optJSONArray("item");
                    String[] imgs = new String[item.length()];
                    String[] titles = new String[item.length()];
                    for (int j = 0; j < item.length(); j++) {
                        JSONObject jsonObject2 = item.optJSONObject(j);
                        String thumbnail = jsonObject2.optString("thumbnail");
                        String title = jsonObject2.optString("title");
                        imgs[i] = thumbnail;
                        titles[i] = title;
                    }
                    LVItemBean lvItemBean = new LVItemBean();
                    lvItemBean.setImgs(imgs);
                    lvItemBean.setTitles(titles);
                    lvItemBean.setTag(Constant.HEAD);
                    list.add(lvItemBean);
                }
            }
            JSONArray array = jsonObject.optJSONArray("list");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject1 = array.optJSONObject(i);
                String thumbnail = jsonObject1.optString("thumb");
                String title = jsonObject1.optString("name");
                String time = jsonObject1.optString("createtime");
                String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                String commentsUrl = jsonObject1.optJSONArray("links").optJSONObject(0).optString("url");
                int commentsall = jsonObject1.optInt("commentsall");
                LVItemBean lvItemBean = new LVItemBean();
                lvItemBean.setThumbnail(thumbnail);
                lvItemBean.setTitle(title);
                lvItemBean.setUpdateTime(updateTime);
                lvItemBean.setCommentsUrl(commentsUrl);
                lvItemBean.setCommentsall(String.valueOf(commentsall));
                lvItemBean.setTag(Constant.GENERAL);
                lvItemBean.setType(Constant.PHOTO_TEXT);
                list.add(lvItemBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromPheonixVideo(String json) {
        List<LVItemBean> list = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            if (jsonObject.has("header")) {
                JSONArray header = jsonObject.optJSONArray("header");
                String[] imgs = new String[header.length()];
                String[] titles = new String[header.length()];
                for (int i = 0; i < header.length(); i++) {
                    JSONObject jsonObject1 = header.optJSONObject(i);
                    String title = jsonObject1.optString("title");
                    String image = jsonObject1.optString("image");
                    imgs[i] = image;
                    titles[i] = title;
                }
                LVItemBean lvItemBean = new LVItemBean();
                lvItemBean.setImgs(imgs);
                lvItemBean.setTitles(titles);
                lvItemBean.setTag(Constant.HEAD);
                list.add(lvItemBean);
            }
            JSONArray bodyList = jsonObject.optJSONArray("bodyList");
            for (int i = 0; i < bodyList.length(); i++) {
                JSONObject jsonObject1 = bodyList.optJSONObject(i);
                String title = jsonObject1.optString("title");
                String image = jsonObject1.optString("image");
                JSONObject memberItem = jsonObject1.optJSONObject("memberItem");
                String commentUrl = memberItem.optString("shareUrl");
                String commentsAll = memberItem.optString("commentsAll");
                String time = memberItem.optString("createDate");
                String updateTime = time.substring(time.indexOf(" "), time.length() - 3);
                LVItemBean lvItemBean = new LVItemBean();
                lvItemBean.setThumbnail(image);
                lvItemBean.setTitle(title);
                lvItemBean.setUpdateTime(updateTime);
                lvItemBean.setCommentsUrl(commentUrl);
                lvItemBean.setCommentsall(commentsAll);
                lvItemBean.setTag(Constant.GENERAL);
                lvItemBean.setType(Constant.PHOTO_TEXT);
                list.add(lvItemBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromBeauty(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray body = new JSONObject(json).optJSONArray("body");
            for (int i = 0; i < body.length(); i++) {
                JSONObject jsonObject = body.optJSONObject(i);
                String thumbnail = jsonObject.optString("thumbnail");
                String commentsUrl = jsonObject.optString("commentsUrl");
                int commentsall = jsonObject.optInt("commentsall");
                int likes = jsonObject.optInt("likes");
                JSONObject jsonObject1 = jsonObject.optJSONArray("img").optJSONObject(0).optJSONObject("size");
                String width = jsonObject1.optString("width");
                String height = jsonObject1.optString("height");
                LVItemBean lvItemBean = new LVItemBean();
                lvItemBean.setThumbnail(thumbnail);
                lvItemBean.setCommentsUrl(commentsUrl);
                lvItemBean.setCommentsall(String.valueOf(commentsall));
                lvItemBean.setLikes(likes);
                lvItemBean.setWidth(width);
                lvItemBean.setHeight(height);
                lvItemBean.setTag(Constant.GENERAL);
                lvItemBean.setType(Constant.IMAGE_LL);
                list.add(lvItemBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromJokes(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray body = new JSONObject(json).optJSONArray("body");
            for (int i = 0; i < body.length(); i++) {
                JSONObject jsonObject = body.optJSONObject(i);
                if ("".equals(jsonObject.optString("content"))) {
                    String thumbnail = jsonObject.optString("thumbnail");
                    String commentsUrl = jsonObject.optString("commentsUrl");
                    int likes = jsonObject.optInt("likes");
                    int commentsall = jsonObject.optInt("commentsall");
                    JSONObject jsonObject1 = jsonObject.optJSONArray("img").optJSONObject(0).optJSONObject("size");
                    String width = jsonObject1.optString("width");
                    String height = jsonObject1.optString("height");
                    LVItemBean lvItemBean = new LVItemBean();
                    lvItemBean.setThumbnail(thumbnail);
                    lvItemBean.setCommentsUrl(commentsUrl);
                    lvItemBean.setCommentsall(String.valueOf(commentsall));
                    lvItemBean.setLikes(likes);
                    lvItemBean.setWidth(width);
                    lvItemBean.setHeight(height);
                    lvItemBean.setTag(Constant.GENERAL);
                    lvItemBean.setType(Constant.IMAGE_LL);
                    list.add(lvItemBean);
                } else {
//                    String content = jsonObject.optString("content");
//                    String commentsUrl = jsonObject.optString("commentsUrl");
//                    int commentsall = jsonObject.optInt("commentsall");
//                    int likes = jsonObject.optInt("likes");
//                    LVItemBean lvItemBean = new LVItemBean();
//                    lvItemBean.setContent(content);
//                    lvItemBean.setCommentsUrl(commentsUrl);
//                    lvItemBean.setCommentsall(String.valueOf(commentsall));
//                    lvItemBean.setLikes(likes);
//                    lvItemBean.setType(Constant.TEXTVIEW_LL);
//                    list.add(lvItemBean);
                    continue;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LVItemBean> parseJsonFromPet(String json) {
        List<LVItemBean> list = new ArrayList<>();
        try {
            JSONArray body = new JSONObject(json).optJSONArray("body");
            for (int i = 0; i < body.length(); i++) {
                JSONObject jsonObject = body.optJSONObject(i);
                String thumbnail = jsonObject.optString("thumbnail");
                String commentsUrl = jsonObject.optString("commentsUrl");
                int commentsall = jsonObject.optInt("commentsall");
                int likes = jsonObject.optInt("likes");
                JSONObject jsonObject1 = jsonObject.optJSONArray("img").optJSONObject(0).optJSONObject("size");
                String width = jsonObject1.optString("width");
                String height = jsonObject1.optString("height");
                LVItemBean lvItemBean = new LVItemBean();
                lvItemBean.setThumbnail(thumbnail);
                lvItemBean.setCommentsUrl(commentsUrl);
                lvItemBean.setCommentsall(String.valueOf(commentsall));
                lvItemBean.setLikes(likes);
                lvItemBean.setWidth(width);
                lvItemBean.setHeight(height);
                lvItemBean.setTag(Constant.GENERAL);
                lvItemBean.setType(Constant.IMAGE_LL);
                list.add(lvItemBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<FindLVItemBean> parseJson2FindLVItem(String json) {
        List<FindLVItemBean> list = new ArrayList<>();
        try {
            JSONArray item = new JSONObject(json).optJSONArray("item");
            for (int i = 0; i < item.length(); i++) {
                JSONObject jsonObject = item.optJSONObject(i);
                if (jsonObject.has("style")) {
                    if ("slides".equals(jsonObject.optJSONObject("style").optString("type"))) {
                        String title = jsonObject.optString("title");
                        String name = jsonObject.optJSONObject("recommendChannel").optString("name");
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        JSONObject style = jsonObject.optJSONObject("style");
                        JSONArray images = style.optJSONArray("images");
                        String[] imgs = new String[images.length()];
                        for (int j = 0; j < images.length(); j++) {
                            imgs[j] = images.optString(j);
                        }
                        int slideCount = style.optInt("slideCount");
                        FindLVItemBean findLVItemBean = new FindLVItemBean();
                        findLVItemBean.setTitle(title);
                        findLVItemBean.setName(name);
                        findLVItemBean.setCommentsUrl(commentsUrl);
                        findLVItemBean.setCommentsall(commentsall);
                        findLVItemBean.setImgs(imgs);
                        findLVItemBean.setSlideCount(slideCount);
                        findLVItemBean.setType(Constant.FIND_PHOTO);
                        list.add(findLVItemBean);
                    } else {
                        String thumbnail = jsonObject.optString("thumbnail");
                        String title = jsonObject.optString("title");
                        String commentsUrl = jsonObject.optString("commentsUrl");
                        String commentsall = jsonObject.optString("commentsall");
                        String name = jsonObject.optJSONObject("recommendChannel").optString("name");
                        FindLVItemBean findLVItemBean = new FindLVItemBean();
                        findLVItemBean.setThumbnail(thumbnail);
                        findLVItemBean.setTitle(title);
                        findLVItemBean.setCommentsUrl(commentsUrl);
                        findLVItemBean.setCommentsall(commentsall);
                        findLVItemBean.setName(name);
                        findLVItemBean.setType(Constant.FIND_PHOTO_TEXT);
                        list.add(findLVItemBean);
                    }
                } else {
                    String thumbnail = jsonObject.optString("thumbnail");
                    String title = jsonObject.optString("title");
                    String commentsUrl = jsonObject.optString("commentsUrl");
                    String commentsall = jsonObject.optString("commentsall");
                    FindLVItemBean findLVItemBean = new FindLVItemBean();
                    findLVItemBean.setThumbnail(thumbnail);
                    findLVItemBean.setTitle(title);
                    findLVItemBean.setCommentsUrl(commentsUrl);
                    findLVItemBean.setCommentsall(commentsall);
                    findLVItemBean.setName("自定义");
                    findLVItemBean.setType(Constant.FIND_PHOTO_TEXT);
                    list.add(findLVItemBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
