package qianfeng.com.phoenixnews.shownews.model;

import android.os.Handler;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import qianfeng.com.phoenixnews.bean.FindLVItemBean;
import qianfeng.com.phoenixnews.utils.Constant;
import qianfeng.com.phoenixnews.utils.HttpUtil;
import qianfeng.com.phoenixnews.utils.ParseJson;

/**
 * Created by superren on 2016/11/6.
 */

public class IFindDataImpl implements IFindData {
    Handler mHandler = new Handler();
    @Override
    public void downloadData(String url, final OnFindActvityDataLiatener onFindActvityDataLiatener) {
        Request request = new Request.Builder().url(url).build();
        HttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onFindActvityDataLiatener != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onFindActvityDataLiatener.onFail();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    final List<FindLVItemBean> list = ParseJson.parseJson2FindLVItem(json);
                    if (onFindActvityDataLiatener != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onFindActvityDataLiatener.onSuccess(list);
                            }
                        });
                    }
                }
            }
        });
    }
}
