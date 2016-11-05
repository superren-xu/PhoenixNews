package qianfeng.com.phoenixnews.shownews.model;

import android.os.Handler;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import qianfeng.com.phoenixnews.bean.LVItemBean;
import qianfeng.com.phoenixnews.utils.HttpUtil;
import qianfeng.com.phoenixnews.utils.ParseJson;
import qianfeng.com.phoenixnews.utils.Constant;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class IBlankFgDataImpl implements IBlankFgData {

    private Handler mHandler = new Handler();
    private static List<LVItemBean> list;
    @Override
    public void loadListViewData(final int mode, String url, final OnLoadDataListener onLoadDataListener) {
        Request request = new Request.Builder().url(url).build();
        HttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onLoadDataListener != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onLoadDataListener.onListViewDataFail();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    switch (mode) {
                        case Constant.GEN_PARSE:
                            list=ParseJson.parseJson2LVItemBean(json);
                            break;
                        case Constant.RECOMMEND:
                            list = ParseJson.parseJsonFromRecommend(json);
                            break;
                        case Constant.BEAUTY:
                            list = ParseJson.parseJsonFromBeauty(json);
                            break;
                        case Constant.JOKES:
                            list = ParseJson.parseJsonFromJokes(json);
                            break;
                        case Constant.LOCAL:
                            list = ParseJson.parseJsonFromLocal(json);
                            break;
                        case Constant.PET:
                            list = ParseJson.parseJsonFromPet(json);
                            break;
                        case Constant.PHEONIXNUM:
                            list = ParseJson.parseJsonFromPheonixNum(json);
                            break;
                        case Constant.PHEONIXVIDEO:
                            list = ParseJson.parseJsonFromPheonixVideo(json);
                            break;
                    }
                    if (onLoadDataListener != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onLoadDataListener.onListViewDataSuccess(list);
                            }
                        });
                    }
                }
            }
        });
    }
}
