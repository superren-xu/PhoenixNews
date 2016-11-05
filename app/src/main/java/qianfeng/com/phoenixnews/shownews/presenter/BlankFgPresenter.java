package qianfeng.com.phoenixnews.shownews.presenter;

import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.bean.LVItemBean;
import qianfeng.com.phoenixnews.fragment.IBlankFgView;
import qianfeng.com.phoenixnews.shownews.model.IBlankFgData;
import qianfeng.com.phoenixnews.shownews.model.IBlankFgDataImpl;
import qianfeng.com.phoenixnews.shownews.model.OnLoadDataListener;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class BlankFgPresenter {
    private IBlankFgView mIBlankFgView;
    private IBlankFgData mIBlankFgData;

    public BlankFgPresenter(IBlankFgView IBlankFgView) {
        mIBlankFgView = IBlankFgView;
        mIBlankFgData = new IBlankFgDataImpl();
    }

    public void loadData(int mode, String url, final int refreshMode) {
        mIBlankFgData.loadListViewData(mode,url, new OnLoadDataListener() {
            @Override
            public void onTabLayoutDataSuccess(List<Classify> list) {

            }

            @Override
            public void onTabLayoutDataFail() {

            }

            @Override
            public void onListViewDataSuccess(List<LVItemBean> list) {
                mIBlankFgView.initListView(list,refreshMode);
            }

            @Override
            public void onListViewDataFail() {
                mIBlankFgView.showError();
            }
        });
    }

    public void initData(int parseMode, String url) {
        mIBlankFgData.loadListViewData(parseMode, url, new OnLoadDataListener() {
            @Override
            public void onTabLayoutDataSuccess(List<Classify> list) {

            }

            @Override
            public void onTabLayoutDataFail() {

            }

            @Override
            public void onListViewDataSuccess(List<LVItemBean> list) {
                mIBlankFgView.setHeaderView(list);
            }

            @Override
            public void onListViewDataFail() {
                mIBlankFgView.showError();
            }
        });
    }
}
