package qianfeng.com.phoenixnews.shownews.presenter;

import android.util.Log;

import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.bean.LVItemBean;
import qianfeng.com.phoenixnews.shownews.model.IMainData;
import qianfeng.com.phoenixnews.shownews.model.IMainDataImpl;
import qianfeng.com.phoenixnews.shownews.model.OnLoadDataListener;
import qianfeng.com.phoenixnews.shownews.view.IMainView;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class MainPresenter {
    private IMainData mIMainData;
    private IMainView mIMainView;

    public MainPresenter(IMainView iMainView) {
        mIMainView = iMainView;
        mIMainData = new IMainDataImpl();
    }

    public void loadTabLayoutData(String apiUrl) {
        mIMainData.loadTabLayoutData(apiUrl, new OnLoadDataListener() {
            @Override
            public void onTabLayoutDataSuccess(List<Classify> list) {
                Log.d("super-filter", "onTabLayoutDataSuccess: ");
                mIMainView.initTabLayoutData(list);
            }

            @Override
            public void onTabLayoutDataFail() {
                Log.d("super-filter", "onTabLayoutDataFail: ");
                mIMainView.showError();
            }

            @Override
            public void onListViewDataSuccess(List<LVItemBean> list) {

            }

            @Override
            public void onListViewDataFail() {

            }
        });
    }
}
