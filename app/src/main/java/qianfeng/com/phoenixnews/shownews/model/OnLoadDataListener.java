package qianfeng.com.phoenixnews.shownews.model;

import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.bean.LVItemBean;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public interface OnLoadDataListener {
    void onTabLayoutDataSuccess(List<Classify> list);

    void onTabLayoutDataFail();

    void onListViewDataSuccess(List<LVItemBean> list);

    void onListViewDataFail();
}
