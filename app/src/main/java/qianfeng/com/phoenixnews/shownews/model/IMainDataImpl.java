package qianfeng.com.phoenixnews.shownews.model;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.utils.HttpUtil;
import qianfeng.com.phoenixnews.utils.ParseJson;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class IMainDataImpl implements IMainData {
    private Handler mHandler = new Handler();
    @Override
    public void loadTabLayoutData(String apiUrl, final OnLoadDataListener onLoadDataListener) {
        Request request = new Request.Builder().url(apiUrl).build();
        HttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onLoadDataListener != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onLoadDataListener.onTabLayoutDataFail();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    final List<Classify> list = ParseJson.parseJson2Classify(string);
                    final List<Classify> list1 = HttpUtil.mosaicUrl(list);
                    if (onLoadDataListener != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onLoadDataListener.onTabLayoutDataSuccess(list1);
                            }
                        });
                    }
                }
            }
        });
    }
}
